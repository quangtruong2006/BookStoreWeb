package vn.hcmute.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import vn.hcmute.entity.Author_24110367;
import vn.hcmute.service.IAuthorService_24110367;
import vn.hcmute.service.impl.AuthorServiceImpl_24110367;

@WebServlet(urlPatterns = {"/admin/authors"})
public class AdminAuthorServlet_24110367 extends HttpServlet {
    private IAuthorService_24110367 authorService = new AuthorServiceImpl_24110367();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                req.getRequestDispatcher("/admin/author_form.jsp").forward(req, resp);
                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("author", authorService.findById(idEdit));
                req.getRequestDispatcher("/admin/author_form.jsp").forward(req, resp);
                break;
            case "delete":
                int idDel = Integer.parseInt(req.getParameter("id"));
                authorService.delete(idDel);
                resp.sendRedirect(req.getContextPath() + "/admin/authors");
                break;
            default:
                String indexPage = req.getParameter("page");
                int page = (indexPage == null) ? 1 : Integer.parseInt(indexPage);
                int limit = 6;
                int offset = (page - 1) * limit;

                List<Author_24110367> listAuthors = authorService.findAll(offset, limit);
                int count = authorService.countAll();
                int endPage = (count % limit == 0) ? (count / limit) : (count / limit + 1);

                req.setAttribute("listAuthors", listAuthors);
                req.setAttribute("endPage", endPage);
                req.setAttribute("currentPage", page);
                req.getRequestDispatcher("/admin/author_list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        Author_24110367 author = new Author_24110367();
        String idParam = req.getParameter("authorId");
        if (idParam != null && !idParam.isEmpty()) {
            author.setAuthorId(Integer.parseInt(idParam));
        }
        
        author.setAuthorName(req.getParameter("authorName"));
        
        try {
            author.setDateOfBirth(java.sql.Date.valueOf(req.getParameter("dateOfBirth")));
        } catch (Exception e) {
            author.setDateOfBirth(new java.util.Date());
        }

        if (author.getAuthorId() == 0) authorService.insert(author);
        else authorService.update(author);
        
        resp.sendRedirect(req.getContextPath() + "/admin/authors");
    }
}