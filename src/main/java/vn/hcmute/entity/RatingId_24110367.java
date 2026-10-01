package vn.hcmute.entity;

import java.io.Serializable;
import java.util.Objects;

public class RatingId_24110367 implements Serializable {
    private int user; // Phải trùng tên với biến khai báo trong class Rating
    private int book; 
    
    public RatingId_24110367() {}

    public int getUser() { return user; }
    public void setUser(int user) { this.user = user; }
    public int getBook() { return book; }
    public void setBook(int book) { this.book = book; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId_24110367 ratingId = (RatingId_24110367) o;
        return user == ratingId.user && book == ratingId.book;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book);
    }
}