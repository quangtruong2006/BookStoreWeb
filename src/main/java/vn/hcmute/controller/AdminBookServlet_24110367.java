package vn.hcmute.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import vn.hcmute.entity.Book_24110367;
import vn.hcmute.service.IBookService_24110367;
import vn.hcmute.service.impl.BookServiceImpl_24110367;

@WebServlet(urlPatterns = {"/admin/books", "/admin/home"})
public class AdminBookServlet_24110367 extends HttpServlet {
    private IBookService_24110367 bookService = new BookServiceImpl_24110367();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                req.getRequestDispatcher("/admin/book_form.jsp").forward(req, resp);
                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Book_24110367 bookEdit = bookService.findById(idEdit);
                req.setAttribute("book", bookEdit);
                req.getRequestDispatcher("/admin/book_form.jsp").forward(req, resp);
                break;
            case "delete":
                int idDel = Integer.parseInt(req.getParameter("id"));
                bookService.delete(idDel);
                resp.sendRedirect(req.getContextPath() + "/admin/books");
                break;
            default:
                // Xử lý phân trang danh sách sách cho Admin (giống hệt trang Home)
                String indexPage = req.getParameter("page");
                if (indexPage == null) indexPage = "1";
                int page = Integer.parseInt(indexPage);
                int limit = 6;
                int offset = (page - 1) * limit;

                List<Book_24110367> listBooks = bookService.findAll(offset, limit);
                int count = bookService.countAllBooks();
                int endPage = count / limit;
                if (count % limit != 0) endPage++;

                req.setAttribute("listBooks", listBooks);
                req.setAttribute("endPage", endPage);
                req.setAttribute("currentPage", page);
                req.getRequestDispatcher("/admin/book_list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        Book_24110367 book = new Book_24110367();
        String idParam = req.getParameter("bookid");
        if (idParam != null && !idParam.isEmpty()) {
            book.setBookid(Integer.parseInt(idParam));
        }
        
        book.setIsbn(Integer.parseInt(req.getParameter("isbn")));
        book.setTitle(req.getParameter("title"));
        book.setPublisher(req.getParameter("publisher"));
        book.setPrice(Double.parseDouble(req.getParameter("price")));
        book.setDescription(req.getParameter("description"));
        book.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        book.setCoverImage(req.getParameter("coverImage"));
        
        // Xử lý Date (input type="date" trả về chuỗi yyyy-MM-dd)
        try {
            book.setPublishDate(java.sql.Date.valueOf(req.getParameter("publishDate")));
        } catch (Exception e) {
            book.setPublishDate(new java.util.Date()); // Mặc định ngày hiện tại nếu lỗi
        }

        if (book.getBookid() == 0) {
            bookService.insert(book); // Thêm mới
        } else {
            bookService.update(book); // Cập nhật
        }
        
        resp.sendRedirect(req.getContextPath() + "/admin/books");
    }
}