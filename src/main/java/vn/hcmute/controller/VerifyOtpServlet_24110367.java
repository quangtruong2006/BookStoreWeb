package vn.hcmute.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.hcmute.entity.User_24110367;
import vn.hcmute.service.IUserService_24110367;
import vn.hcmute.service.impl.UserServiceImpl_24110367;

@WebServlet(urlPatterns = {"/verify-otp"})
public class VerifyOtpServlet_24110367 extends HttpServlet {
    private IUserService_24110367 userService = new UserServiceImpl_24110367();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/verify_otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputOtp = req.getParameter("otp");
        HttpSession session = req.getSession();
        
        String sessionOtp = (String) session.getAttribute("otp");
        User_24110367 tempUser = (User_24110367) session.getAttribute("tempUser");

        if (sessionOtp != null && sessionOtp.equals(inputOtp)) {
            // Lưu user vào database
            userService.register(tempUser);
            // Xóa session tạm
            session.removeAttribute("otp");
            session.removeAttribute("tempUser");
            
            resp.sendRedirect(req.getContextPath() + "/login?message=register_success");
        } else {
            req.setAttribute("error", "Mã OTP không chính xác!");
            req.getRequestDispatcher("/verify_otp.jsp").forward(req, resp);
        }
    }
}