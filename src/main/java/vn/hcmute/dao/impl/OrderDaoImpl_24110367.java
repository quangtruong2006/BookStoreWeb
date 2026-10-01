package vn.hcmute.dao.impl;

import java.util.List;
import javax.persistence.*;
import vn.hcmute.dao.IOrderDao_24110367;
import vn.hcmute.entity.Order_24110367;

public class OrderDaoImpl_24110367 implements IOrderDao_24110367 {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookStoreWeb");

    @Override
    public List<Order_24110367> findByUserAndStatus(int userId, String status) {
        EntityManager em = emf.createEntityManager();
        try {
            // Câu lệnh JPQL cơ bản lấy đơn hàng của user đang đăng nhập
            String jpql = "SELECT o FROM Orders o WHERE o.user.id = :userId";
            
            // Nếu user có chọn bộ lọc trạng thái (và khác "Tất cả") thì nối thêm điều kiện
            if (status != null && !status.trim().isEmpty() && !status.equals("Tất cả")) {
                jpql += " AND o.status = :status";
            }
            jpql += " ORDER BY o.orderId DESC"; // Đơn mới nhất xếp lên đầu
            
            TypedQuery<Order_24110367> query = em.createQuery(jpql, Order_24110367.class);
            query.setParameter("userId", userId);
            
            if (status != null && !status.trim().isEmpty() && !status.equals("Tất cả")) {
                query.setParameter("status", status);
            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}