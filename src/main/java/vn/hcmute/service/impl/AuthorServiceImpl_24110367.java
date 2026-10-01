package vn.hcmute.service.impl;

import java.util.List;
import vn.hcmute.dao.IAuthorDao_24110367;
import vn.hcmute.dao.impl.AuthorDaoImpl_24110367;
import vn.hcmute.entity.Author_24110367;
import vn.hcmute.service.IAuthorService_24110367;

public class AuthorServiceImpl_24110367 implements IAuthorService_24110367 {
    private IAuthorDao_24110367 authorDao = new AuthorDaoImpl_24110367();

    @Override
    public List<Author_24110367> findAll(int offset, int limit) { return authorDao.findAll(offset, limit); }
    @Override
    public int countAll() { return authorDao.countAll(); }
    @Override
    public void insert(Author_24110367 author) { authorDao.insert(author); }
    @Override
    public void update(Author_24110367 author) { authorDao.update(author); }
    @Override
    public void delete(int id) { authorDao.delete(id); }
    @Override
    public Author_24110367 findById(int id) { return authorDao.findById(id); }
}