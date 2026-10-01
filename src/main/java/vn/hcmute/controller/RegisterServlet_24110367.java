package vn.hcmute.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.hcmute.entity.User_24110367;
import vn.hcmute.service.IUserService_24110367;
import vn.hcmute.service.impl.UserServiceImpl_24110367;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet_24110367 extends HttpServlet {
    private IUserService_24110367 userService = new UserServiceImpl_24110367();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");

        if (userService.checkExistEmail(email)) {
            req.setAttribute("error", "Email đã tồn tại!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }

        // Tạo OTP ngẫu nhiên 6 số
        String otp = String.format("%06d", new Random().nextInt(999999));
        
        // Gửi OTP qua mail
        boolean isSent = userService.sendOTP(email, otp);
        if (isSent) {
            // Lưu thông tin tạm vào session chờ xác thực
            User_24110367 tempUser = new User_24110367();
            tempUser.setEmail(email);
            tempUser.setFullname(fullname);
            tempUser.setPasswd(password);
            tempUser.setSignupDate(new Date());
            tempUser.setIsAdmin(false); // Mặc định người đăng ký là User

            HttpSession session = req.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("tempUser", tempUser);
            
            resp.sendRedirect(req.getContextPath() + "/verify-otp");
        } else {
            req.setAttribute("error", "Lỗi gửi Email OTP. Vui lòng thử lại!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}