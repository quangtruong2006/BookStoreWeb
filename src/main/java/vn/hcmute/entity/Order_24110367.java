package vn.hcmute.entity;

import java.util.Date;
import javax.persistence.*;

@Entity(name = "Orders") // Đặt là Orders để tránh trùng từ khóa hệ thống của SQL
@Table(name = "orders")
public class Order_24110367 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User_24110367 user;

    private Date orderDate;
    @Column(columnDefinition = "nvarchar(50)")
    private String status; // Lưu trạng thái: đơn hàng mới, đã xác nhận...
    private double totalAmount;

    public Order_24110367() {}

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public User_24110367 getUser() { return user; }
    public void setUser(User_24110367 user) { this.user = user; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}