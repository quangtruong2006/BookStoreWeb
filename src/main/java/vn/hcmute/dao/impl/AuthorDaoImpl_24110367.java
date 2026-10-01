package vn.hcmute.dao.impl;

import java.util.List;
import javax.persistence.*;
import vn.hcmute.dao.IAuthorDao_24110367;
import vn.hcmute.entity.Author_24110367;

public class AuthorDaoImpl_24110367 implements IAuthorDao_24110367 {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookStoreWeb");

    @Override
    public List<Author_24110367> findAll(int offset, int limit) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Author a ORDER BY a.authorId DESC", Author_24110367.class)
                     .setFirstResult(offset).setMaxResults(limit).getResultList();
        } finally { em.close(); }
    }

    @Override
    public int countAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return ((Long) em.createQuery("SELECT COUNT(a) FROM Author a").getSingleResult()).intValue();
        } finally { em.close(); }
    }

    @Override
    public void insert(Author_24110367 author) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try { trans.begin(); em.persist(author); trans.commit(); } 
        catch (Exception e) { trans.rollback(); throw e; } finally { em.close(); }
    }

    @Override
    public void update(Author_24110367 author) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try { trans.begin(); em.merge(author); trans.commit(); } 
        catch (Exception e) { trans.rollback(); throw e; } finally { em.close(); }
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try { 
            trans.begin(); 
            Author_24110367 author = em.find(Author_24110367.class, id);
            if(author != null) em.remove(author);
            trans.commit(); 
        } 
        catch (Exception e) { trans.rollback(); throw e; } finally { em.close(); }
    }

    @Override
    public Author_24110367 findById(int id) {
        EntityManager em = emf.createEntityManager();
        try { return em.find(Author_24110367.class, id); } finally { em.close(); }
    }
}