package vn.hcmute.service.impl;

import java.util.List;
import vn.hcmute.dao.IOrderDao_24110367;
import vn.hcmute.dao.impl.OrderDaoImpl_24110367;
import vn.hcmute.entity.Order_24110367;
import vn.hcmute.service.IOrderService_24110367;

public class OrderServiceImpl_24110367 implements IOrderService_24110367 {
    private IOrderDao_24110367 orderDao = new OrderDaoImpl_24110367();

    @Override
    public List<Order_24110367> findByUserAndStatus(int userId, String status) {
        return orderDao.findByUserAndStatus(userId, status);
    }
}