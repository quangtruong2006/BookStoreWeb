package vn.hcmute.dao;

import java.util.List;
import vn.hcmute.entity.Order_24110367;

public interface IOrderDao_24110367 {
    // Hàm này lấy danh sách đơn hàng của 1 user, có kèm theo bộ lọc trạng thái
    List<Order_24110367> findByUserAndStatus(int userId, String status);
}