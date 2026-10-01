package vn.hcmute.service;

import java.util.List;
import vn.hcmute.entity.Order_24110367;

public interface IOrderService_24110367 {
    List<Order_24110367> findByUserAndStatus(int userId, String status);
}