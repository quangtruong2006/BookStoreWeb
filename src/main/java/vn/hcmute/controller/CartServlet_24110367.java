package vn.hcmute.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import vn.hcmute.entity.Book_24110367;
import vn.hcmute.entity.CartItem_24110367;
import vn.hcmute.service.IBookService_24110367;
import vn.hcmute.service.impl.BookServiceImpl_24110367;

@WebServlet(urlPatterns = {"/cart"})
public class CartServlet_24110367 extends HttpServlet {
    private IBookService_24110367 bookService = new BookServiceImpl_24110367();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "view";

        HttpSession session = req.getSession();
        List<CartItem_24110367> cart = (List<CartItem_24110367>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        try {
            if ("add".equals(action)) {
                int bookId = Integer.parseInt(req.getParameter("bookId"));
                Book_24110367 book = bookService.findById(bookId);
                
                boolean exists = false;
                for (CartItem_24110367 item : cart) {
                    if (item.getBook().getBookid() == bookId) {
                        // Kiểm tra giới hạn tồn kho
                        if (item.getQuantity() < book.getQuantity()) {
                            item.setQuantity(item.getQuantity() + 1);
                        } else {
                            req.setAttribute("error", "Số lượng vượt quá tồn kho!");
                        }
                        exists = true;
                        break;
                    }
                }
                if (!exists && book.getQuantity() > 0) {
                    cart.add(new CartItem_24110367(book, 1));
                }
                session.setAttribute("cart", cart);
                resp.sendRedirect(req.getContextPath() + "/cart");
                return;

            } else if ("remove".equals(action)) {
                int bookId = Integer.parseInt(req.getParameter("bookId"));
                cart.removeIf(item -> item.getBook().getBookid() == bookId);
                session.setAttribute("cart", cart);
                resp.sendRedirect(req.getContextPath() + "/cart");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Action "view" - Tính tổng tiền
        double totalCart = 0;
        for (CartItem_24110367 item : cart) {
            totalCart += item.getTotalPrice();
        }
        req.setAttribute("totalCart", totalCart);
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Xử lý Cập nhật số lượng
        String action = req.getParameter("action");
        if ("update".equals(action)) {
            HttpSession session = req.getSession();
            List<CartItem_24110367> cart = (List<CartItem_24110367>) session.getAttribute("cart");
            
            if (cart != null) {
                int bookId = Integer.parseInt(req.getParameter("bookId"));
                int newQuantity = Integer.parseInt(req.getParameter("quantity"));
                
                Book_24110367 book = bookService.findById(bookId);
                for (CartItem_24110367 item : cart) {
                    if (item.getBook().getBookid() == bookId) {
                        // Kiểm tra số lượng hợp lệ (lớn hơn 0 và nhỏ hơn bằng tồn kho)
                        if (newQuantity > 0 && newQuantity <= book.getQuantity()) {
                            item.setQuantity(newQuantity);
                        }
                        break;
                    }
                }
                session.setAttribute("cart", cart);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}