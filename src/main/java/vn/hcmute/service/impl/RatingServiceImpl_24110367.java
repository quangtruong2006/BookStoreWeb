package vn.hcmute.service.impl;

import java.util.List;
import vn.hcmute.dao.IRatingDao_24110367;
import vn.hcmute.dao.impl.RatingDaoImpl_24110367;
import vn.hcmute.entity.Rating_24110367;
import vn.hcmute.service.IRatingService_24110367;

public class RatingServiceImpl_24110367 implements IRatingService_24110367 {
    
    private IRatingDao_24110367 ratingDao = new RatingDaoImpl_24110367();

    @Override
    public void insert(Rating_24110367 rating) {
        ratingDao.insert(rating);
    }

    @Override
    public List<Rating_24110367> findByBookId(int bookId) {
        return ratingDao.findByBookId(bookId);
    }
}