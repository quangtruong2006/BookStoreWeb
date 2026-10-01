package vn.hcmute.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import vn.hcmute.entity.Book_24110367;
import vn.hcmute.entity.Rating_24110367;
import vn.hcmute.entity.User_24110367;
import vn.hcmute.service.IBookService_24110367;
import vn.hcmute.service.impl.BookServiceImpl_24110367;
import vn.hcmute.service.IRatingService_24110367;
import vn.hcmute.service.impl.RatingServiceImpl_24110367;

@WebServlet(urlPatterns = {"/book-detail"})
public class BookDetailServlet_24110367 extends HttpServlet {
    private IBookService_24110367 bookService = new BookServiceImpl_24110367();
    private IRatingService_24110367 ratingService = new RatingServiceImpl_24110367();

    // Hiển thị giao diện chi tiết sách
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("id"));
        
        Book_24110367 book = bookService.findById(bookId);
        List<Rating_24110367> listReviews = ratingService.findByBookId(bookId);
        
        req.setAttribute("book", book);
        req.setAttribute("listReviews", listReviews);
        req.getRequestDispatcher("/book_detail.jsp").forward(req, resp);
    }

    // Xử lý khi User bấm nút [Submit] form thêm review
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User_24110367 user = (User_24110367) session.getAttribute("user");
        
        // Nếu chưa đăng nhập thì bắt quay ra trang đăng nhập
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        int score = Integer.parseInt(req.getParameter("rating"));
        String reviewText = req.getParameter("review_text");
        
        Book_24110367 book = bookService.findById(bookId);
        
        Rating_24110367 rating = new Rating_24110367();
        rating.setUser(user);
        rating.setBook(book);
        rating.setRating(score);
        rating.setReviewText(reviewText);
        
        ratingService.insert(rating);
        
        // Trở lại trang chi tiết sách để thấy review vừa thêm
        resp.sendRedirect(req.getContextPath() + "/book-detail?id=" + bookId);
    }
}