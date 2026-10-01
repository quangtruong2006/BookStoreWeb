package vn.hcmute.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "Book")
@Table(name = "books")
public class Book_24110367 implements Serializable {
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "bookid"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private java.util.List<Author_24110367> authors;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookid;
    
    private Integer isbn;
    
    private String title;
    
    private String publisher;
    
    private Double price;
    
    @Column(columnDefinition = "text")
    private String description;
    
    @Column(name = "publish_date")
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    
    @Column(name = "cover_image")
    private String coverImage;
    
    private Integer quantity;

    public Book_24110367() {
    }

    // --- Tạo nhanh Getters và Setters ---
    public int getBookid() { return bookid; }
    public void setBookid(int bookid) { this.bookid = bookid; }

    public Integer getIsbn() { return isbn; }
    public void setIsbn(Integer isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public java.util.List<Author_24110367> getAuthors() { return authors; }
    public void setAuthors(java.util.List<Author_24110367> authors) { this.authors = authors; }
}