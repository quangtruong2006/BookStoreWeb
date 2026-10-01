package vn.hcmute.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import vn.hcmute.entity.Order_24110367;
import vn.hcmute.entity.User_24110367;
import vn.hcmute.service.IOrderService_24110367;
import vn.hcmute.service.impl.OrderServiceImpl_24110367;

@WebServlet(urlPatterns = {"/order-history"})
public class OrderHistoryServlet_24110367 extends HttpServlet {
    private IOrderService_24110367 orderService = new OrderServiceImpl_24110367();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession();
        User_24110367 user = (User_24110367) session.getAttribute("user");
        
        // Nếu chưa đăng nhập thì đẩy về trang login
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Lấy giá trị trạng thái từ thanh chọn bộ lọc (nếu có)
        String status = req.getParameter("status");
        if (status == null) status = "Tất cả";

        // Gọi Service lấy danh sách
        List<Order_24110367> listOrders = orderService.findByUserAndStatus(user.getId(), status);
        
        req.setAttribute("listOrders", listOrders);
        req.setAttribute("currentStatus", status);
        req.getRequestDispatcher("/order_history.jsp").forward(req, resp);
    }
}