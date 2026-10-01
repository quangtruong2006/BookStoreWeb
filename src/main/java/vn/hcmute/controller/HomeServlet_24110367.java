package vn.hcmute.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.hcmute.entity.Book_24110367;
import vn.hcmute.service.IBookService_24110367;
import vn.hcmute.service.impl.BookServiceImpl_24110367;

@WebServlet(urlPatterns = {"/home"})
public class HomeServlet_24110367 extends HttpServlet {
    private IBookService_24110367 bookService = new BookServiceImpl_24110367();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy số trang hiện tại từ URL (Mặc định là trang 1)
        String indexPage = req.getParameter("page");
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
        
        // Cấu hình 6 sản phẩm / 1 trang
        int limit = 6;
        int offset = (page - 1) * limit;

        // Lấy danh sách sách và tính toán tổng số trang
        List<Book_24110367> listBooks = bookService.findAll(offset, limit);
        int count = bookService.countAllBooks();
        int endPage = count / limit;
        if (count % limit != 0) {
            endPage++;
        }

        // Đẩy dữ liệu sang JSP
        req.setAttribute("listBooks", listBooks);
        req.setAttribute("endPage", endPage);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}