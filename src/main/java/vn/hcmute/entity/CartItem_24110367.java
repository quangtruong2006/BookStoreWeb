package vn.hcmute.entity;

public class CartItem_24110367 {
    private Book_24110367 book;
    private int quantity;

    public CartItem_24110367() {}

    public CartItem_24110367(Book_24110367 book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book_24110367 getBook() { return book; }
    public void setBook(Book_24110367 book) { this.book = book; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Tính thành tiền của món này
    public double getTotalPrice() {
        return book.getPrice() * quantity;
    }
}