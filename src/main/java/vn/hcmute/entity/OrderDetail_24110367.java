package vn.hcmute.entity;

import javax.persistence.*;

@Entity(name = "OrderDetail")
@Table(name = "order_details")
public class OrderDetail_24110367 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order_24110367 order;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book_24110367 book;

    private int quantity;
    private double price;

    public OrderDetail_24110367() {}

    public int getDetailId() { return detailId; }
    public void setDetailId(int detailId) { this.detailId = detailId; }

    public Order_24110367 getOrder() { return order; }
    public void setOrder(Order_24110367 order) { this.order = order; }

    public Book_24110367 getBook() { return book; }
    public void setBook(Book_24110367 book) { this.book = book; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}