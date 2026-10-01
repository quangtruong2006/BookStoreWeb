package vn.hcmute.service.impl;

import java.util.List;
import vn.hcmute.dao.IBookDao_24110367;
import vn.hcmute.dao.impl.BookDaoImpl_24110367;
import vn.hcmute.entity.Book_24110367;
import vn.hcmute.service.IBookService_24110367;

public class BookServiceImpl_24110367 implements IBookService_24110367 {
    private IBookDao_24110367 bookDao = new BookDaoImpl_24110367();

    @Override
    public List<Book_24110367> findAll(int offset, int limit) {
        return bookDao.findAll(offset, limit);
    }
    @Override
    public void insert(Book_24110367 book) { bookDao.insert(book); }
    @Override
    public void update(Book_24110367 book) { bookDao.update(book); }
    @Override
    public void delete(int id) { bookDao.delete(id); }
    @Override
    public Book_24110367 findById(int id) {
        return bookDao.findById(id);
    }
    @Override
    public int countAllBooks() {
        return bookDao.countAllBooks();
    }
}