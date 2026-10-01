package vn.hcmute.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import vn.hcmute.dao.IUserDao_24110367;
import vn.hcmute.entity.User_24110367;

public class UserDaoImpl_24110367 implements IUserDao_24110367 {
    // Khởi tạo EntityManagerFactory dựa trên tên persistence-unit trong persistence.xml
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookStoreWeb");

    @Override
    public void insert(User_24110367 user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            em.clear();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public User_24110367 findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User_24110367> query = em.createQuery(jpql, User_24110367.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Không tìm thấy
        } finally {
            em.close();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return findByEmail(email) != null;
    }

    @Override
    public void update(User_24110367 user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            em.clear();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}