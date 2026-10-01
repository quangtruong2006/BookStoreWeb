package vn.hcmute.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import vn.hcmute.dao.IBookDao_24110367;
import vn.hcmute.entity.Book_24110367;

public class BookDaoImpl_24110367 implements IBookDao_24110367 {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookStoreWeb");

	@Override
	public List<Book_24110367> findAll(int offset, int limit) {
		EntityManager em = emf.createEntityManager();
		try {
			String jpql = "SELECT b FROM Book b ORDER BY b.bookid DESC";
			TypedQuery<Book_24110367> query = em.createQuery(jpql, Book_24110367.class);
			query.setFirstResult(offset); // Vị trí bắt đầu lấy
			query.setMaxResults(limit); // Số lượng lấy (6 cuốn/trang)
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Book_24110367 findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Book_24110367.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public int countAllBooks() {
		EntityManager em = emf.createEntityManager();
		try {
			String jpql = "SELECT COUNT(b) FROM Book b";
			Long count = (Long) em.createQuery(jpql).getSingleResult();
			return count.intValue();
		} finally {
			em.close();
		}
	}

	@Override
	public void insert(Book_24110367 book) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(book);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Book_24110367 book) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(book);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Book_24110367 book = em.find(Book_24110367.class, id);
			if (book != null)
				em.remove(book);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
}