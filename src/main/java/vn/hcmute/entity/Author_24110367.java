package vn.hcmute.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "Author")
@Table(name = "author")
public class Author_24110367 implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int authorId;
    
    @Column(name = "author_name")
    private String authorName;
    
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    public Author_24110367() {
    }

    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}