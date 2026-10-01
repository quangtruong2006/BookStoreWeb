package vn.hcmute.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Rating")
@Table(name = "rating")
@IdClass(RatingId_24110367.class)
public class Rating_24110367 implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    private User_24110367 user;

    @Id
    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book_24110367 book;

    private Integer rating;

    @Column(name = "review_text", columnDefinition = "text")
    private String reviewText;

    public Rating_24110367() {}

    // Getters and Setters
    public User_24110367 getUser() { return user; }
    public void setUser(User_24110367 user) { this.user = user; }

    public Book_24110367 getBook() { return book; }
    public void setBook(Book_24110367 book) { this.book = book; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }
}