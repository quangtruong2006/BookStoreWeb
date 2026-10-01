package vn.hcmute.service;

import java.util.List;
import vn.hcmute.entity.Rating_24110367;

public interface IRatingService_24110367 {
    void insert(Rating_24110367 rating);
    List<Rating_24110367> findByBookId(int bookId);
}