package vn.hcmute.dao.impl;

import java.util.List;
import javax.persistence.*;
import vn.hcmute.dao.IRatingDao_24110367;
import vn.hcmute.entity.Rating_24110367;

public class RatingDaoImpl_24110367 implements IRatingDao_24110367 {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookStoreWeb");

    @Override
    public void insert(Rating_24110367 rating) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(rating); // Dùng merge để thêm hoặc ghi đè nếu user đã review
            trans.commit();
        } catch (Exception e) {
            trans.rollback(); throw e;
        } finally { em.close(); }
    }

    @Override
    public List<Rating_24110367> findByBookId(int bookId) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT r FROM Rating r WHERE r.book.bookid = :bookId ORDER BY r.rating DESC";
            TypedQuery<Rating_24110367> query = em.createQuery(jpql, Rating_24110367.class);
            query.setParameter("bookId", bookId);
            return query.getResultList();
        } finally { em.close(); }
    }
}