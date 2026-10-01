package vn.hcmute.service;

import java.util.List;
import vn.hcmute.entity.Book_24110367;

public interface IBookService_24110367 {
	void insert(Book_24110367 book);
    void update(Book_24110367 book);
    void delete(int id);
	Book_24110367 findById(int id);
    List<Book_24110367> findAll(int offset, int limit);
    int countAllBooks();
}