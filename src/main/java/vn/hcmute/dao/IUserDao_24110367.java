package vn.hcmute.dao;

import vn.hcmute.entity.User_24110367;

public interface IUserDao_24110367 {
    void insert(User_24110367 user);
    User_24110367 findByEmail(String email);
    boolean checkExistEmail(String email);
    void update(User_24110367 user);
}