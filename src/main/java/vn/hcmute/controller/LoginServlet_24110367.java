package vn.hcmute.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.hcmute.entity.User_24110367;
import vn.hcmute.service.IUserService_24110367;
import vn.hcmute.service.impl.UserServiceImpl_24110367;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet_24110367 extends HttpServlet {
    private IUserService_24110367 userService = new UserServiceImpl_24110367();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User_24110367 user = userService.login(email, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user); // Lưu phiên đăng nhập
            
            // Cập nhật thời gian login cuối
            // Tạm thời bỏ qua bước update last_login để đơn giản hóa, tập trung routing
            
            if (user.getIsAdmin() != null && user.getIsAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/admin/home"); // Quản trị viên
            } else {
                resp.sendRedirect(req.getContextPath() + "/home"); // User bình thường
            }
        } else {
            req.setAttribute("error", "Email hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}