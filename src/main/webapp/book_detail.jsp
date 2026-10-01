<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Sách</title>
    <style>
        .detail-container { max-width: 800px; margin: auto; padding: 20px; border: 1px solid #ddd; background: #fff; border-radius: 5px; }
        .book-info { display: flex; gap: 20px; margin-bottom: 20px; }
        .book-info img { width: 250px; height: 350px; object-fit: cover; border: 1px solid #ccc; }
        .review-section { border-top: 2px solid #eee; padding-top: 20px; }
        .review-item { margin-bottom: 15px; padding-bottom: 10px; border-bottom: 1px dashed #ccc; }
        textarea { width: 100%; height: 80px; margin-bottom: 10px; padding: 10px; box-sizing: border-box; }
        button { padding: 10px 20px; background-color: #2c3e50; color: white; border: none; cursor: pointer; }
        .btn-cart { display: inline-block; margin-top: 10px; margin-bottom: 15px; padding: 10px 20px; background-color: #e67e22; color: white; text-decoration: none; border-radius: 5px; font-size: 16px; font-weight: bold; }
        .btn-cart:hover { background-color: #d35400; }
    </style>
</head>
<body>
    <div class="detail-container">
        <!-- Phần Thông tin sách -->
        <div class="book-info">
            <img src="${not empty book.coverImage ? book.coverImage : 'https://via.placeholder.com/250x350?text=No+Cover'}" alt="[cover_image]">
            <div>
                <h2>Tiêu đề: ${book.title}</h2>
                <p><b>Mã isbn:</b> ${book.isbn}</p>
                <p><b>Tác giả:</b> 
                    <c:forEach items="${book.authors}" var="author" varStatus="status">
                        ${author.authorName}${!status.last ? ', ' : ''}
                    </c:forEach>
                </p>
                <p><b>Publisher:</b> ${book.publisher}</p>
                <p><b>Publisher_date:</b> ${book.publishDate}</p>
                <p><b>Quantity:</b> ${book.quantity}</p>
                
                <!-- Nút Thêm vào giỏ hàng -->
                <a href="${pageContext.request.contextPath}/cart?action=add&bookId=${book.bookid}" class="btn-cart">
                   🛒 Thêm vào giỏ hàng
                </a>

                <p><b>Reviews (${listReviews.size()})</b></p>
            </div>
        </div>

        <!-- Phần Hiển thị Đánh giá -->
        <div class="review-section">
            <h3>Reviews</h3>
            <c:if test="${empty listReviews}">
                <p>Chưa có đánh giá nào.</p>
            </c:if>
            <c:forEach items="${listReviews}" var="rev">
                <div class="review-item">
                    <b>[${rev.user.fullname}]:</b> ${rev.reviewText} <i>(Đánh giá: ${rev.rating}/5 sao)</i>
                </div>
            </c:forEach>

            <!-- Form thêm Đánh giá -->
            <h3 style="margin-top: 30px;">Form thêm reviews</h3>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <form action="${pageContext.request.contextPath}/book-detail" method="post">
                        <input type="hidden" name="bookId" value="${book.bookid}" />
                        
                        <label for="rating">Điểm số (1-5):</label>
                        <input type="number" name="rating" id="rating" min="1" max="5" value="5" style="margin-bottom: 10px;" required/><br/>
                        
                        <textarea name="review_text" placeholder="Viết đánh giá của bạn vào đây..." required></textarea><br/>
                        
                        <button type="submit">[Submit]</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <p style="color: red; font-weight: bold;">Bạn cần đăng nhập để gửi đánh giá!</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>