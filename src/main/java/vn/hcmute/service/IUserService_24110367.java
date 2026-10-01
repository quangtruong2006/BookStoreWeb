package vn.hcmute.service;

import vn.hcmute.entity.User_24110367;

public interface IUserService_24110367 {
    void register(User_24110367 user);
    User_24110367 login(String email, String password);
    boolean checkExistEmail(String email);
    boolean sendOTP(String toEmail, String otp);
}