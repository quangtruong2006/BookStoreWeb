package vn.hcmute.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity(name = "User")
@Table(name = "users")
public class User_24110367 implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String email;
    
    @Column(columnDefinition = "nvarchar(50)")
    private String fullname;
    
    private Integer phone;
    
    private String passwd;
    
    @Column(name = "signup_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date signupDate;
    
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    
    @Column(name = "is_admin")
    private Boolean isAdmin;

    public User_24110367() {
    }

    // Getters và Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public Integer getPhone() { return phone; }
    public void setPhone(Integer phone) { this.phone = phone; }

    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }

    public Date getSignupDate() { return signupDate; }
    public void setSignupDate(Date signupDate) { this.signupDate = signupDate; }

    public Date getLastLogin() { return lastLogin; }
    public void setLastLogin(Date lastLogin) { this.lastLogin = lastLogin; }

    public Boolean getIsAdmin() { return isAdmin; }
    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }
}