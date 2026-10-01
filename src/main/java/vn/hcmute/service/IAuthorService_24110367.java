package vn.hcmute.service;

import java.util.List;
import vn.hcmute.entity.Author_24110367;

public interface IAuthorService_24110367 {
    List<Author_24110367> findAll(int offset, int limit);
    int countAll();
    void insert(Author_24110367 author);
    void update(Author_24110367 author);
    void delete(int id);
    Author_24110367 findById(int id);
}