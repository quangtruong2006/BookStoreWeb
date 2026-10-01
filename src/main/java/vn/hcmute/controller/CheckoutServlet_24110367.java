package vn.hcmute.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import vn.hcmute.entity.*;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutServlet_24110367 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User_24110367 user = (User_24110367) session.getAttribute("user");
        List<CartItem_24110367> cart = (List<CartItem_24110367>) session.getAttribute("cart");

        // 1. Kiểm tra đăng nhập (Chưa đăng nhập thì đá về trang login)
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login?message=login_required");
            return;
        }
        
        // 2. Giỏ hàng trống thì không cho thanh toán
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookStoreWeb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            
            // 3. Tính tổng tiền của cả đơn
            double total = 0;
            for (CartItem_24110367 item : cart) {
                total += item.getTotalPrice();
            }

            // 4. Tạo hóa đơn mới (Order)
            Order_24110367 order = new Order_24110367();
            order.setUser(user);
            order.setOrderDate(new Date());
            order.setStatus("đơn hàng mới"); // Gắn mác theo đúng yêu cầu đề bài
            order.setTotalAmount(total);
            em.persist(order); // Lưu Order vào DB trước để lấy ID

            // 5. Quét giỏ hàng, lưu chi tiết (OrderDetail)
            for (CartItem_24110367 item : cart) {
                OrderDetail_24110367 detail = new OrderDetail_24110367();
                detail.setOrder(order);
                detail.setBook(item.getBook());
                detail.setQuantity(item.getQuantity());
                detail.setPrice(item.getBook().getPrice());
                em.persist(detail);
                
                // (Mở rộng thêm: Nếu muốn trừ số lượng sách tồn kho thì làm ở đây)
                /* 
                Book_24110367 b = item.getBook();
                b.setQuantity(b.getQuantity() - item.getQuantity());
                em.merge(b); 
                */
            }

            trans.commit();
            
            // 6. Mua xong rồi thì dọn sạch giỏ hàng trong Session
            session.removeAttribute("cart"); 
            
            // 7. Chuyển hướng sang trang Lịch sử đặt hàng
            resp.sendRedirect(req.getContextPath() + "/order-history");
            
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}