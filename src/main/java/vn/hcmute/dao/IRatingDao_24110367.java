package vn.hcmute.dao;
import java.util.List;
import vn.hcmute.entity.Rating_24110367;

public interface IRatingDao_24110367 {
    void insert(Rating_24110367 rating);
    List<Rating_24110367> findByBookId(int bookId);
}