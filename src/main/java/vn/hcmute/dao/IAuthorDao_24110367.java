package vn.hcmute.dao; // Đổi thành vn.hcmute.service cho file IAuthorService
import java.util.List;
import vn.hcmute.entity.Author_24110367;

public interface IAuthorDao_24110367 {
    List<Author_24110367> findAll(int offset, int limit);
    int countAll();
    void insert(Author_24110367 author);
    void update(Author_24110367 author);
    void delete(int id);
    Author_24110367 findById(int id);
}