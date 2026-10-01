package vn.hcmute.service.impl;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import vn.hcmute.dao.IUserDao_24110367;
import vn.hcmute.dao.impl.UserDaoImpl_24110367;
import vn.hcmute.entity.User_24110367;
import vn.hcmute.service.IUserService_24110367;

public class UserServiceImpl_24110367 implements IUserService_24110367 {
    
    private IUserDao_24110367 userDao = new UserDaoImpl_24110367();

    @Override
    public void register(User_24110367 user) {
        userDao.insert(user);
    }

    @Override
    public User_24110367 login(String email, String password) {
        User_24110367 user = userDao.findByEmail(email);
        // Kiểm tra mật khẩu (thực tế nên dùng mã hóa MD5/Bcrypt, nhưng bài tập tạm so sánh text)
        if (user != null && user.getPasswd().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean sendOTP(String toEmail, String otp) {
        // Cấu hình tài khoản gửi Email (Tạo App Password trên Gmail để sử dụng)
        final String fromEmail = "quangtruongn57@gmail.com"; 
        final String password = "ogaj zwwp kfdb qyks"; 
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Mã OTP xác nhận đăng ký BookStoreWeb");
            message.setText("Mã OTP của bạn là: " + otp);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}