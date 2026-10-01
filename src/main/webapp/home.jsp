<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ - Danh Sách Sách</title>
    <style>
        .book-container { display: flex; flex-wrap: wrap; gap: 20px; justify-content: center; margin-top: 20px; }
        .book-item { border: 1px solid #ccc; padding: 15px; width: 30%; box-sizing: border-box; background: #fff; border-radius: 5px; }
        .book-item img { width: 100%; height: 250px; object-fit: cover; border-bottom: 1px solid #eee; margin-bottom: 10px; }
        .book-item p { margin: 5px 0; font-size: 14px; }
        .pagination { margin-top: 30px; text-align: center; }
        .pagination a { margin: 0 5px; text-decoration: none; padding: 8px 12px; border: 1px solid #2c3e50; color: #2c3e50; border-radius: 3px; }
        .pagination a.active { background-color: #2c3e50; color: white; }
    </style>
</head>
<body>
    <h2 style="text-align: center; color: #2c3e50;">Danh Sách Sách</h2>
    
    <div class="book-container">
        <c:forEach items="${listBooks}" var="book">
            <div class="book-item">
                <img src="${not empty book.coverImage ? book.coverImage : 'https://via.placeholder.com/200x250?text=No+Cover'}" alt="[cover_image]">
                <p><b>Tiêu đề:</b> <a href="${pageContext.request.contextPath}/book-detail?id=${book.bookid}">${book.title}</a></p>
                <p><b>Mã isbn:</b> ${book.isbn}</p>
                <p><b>Tác giả:</b> 
                    <c:choose>
                        <c:when test="${not empty book.authors}">
                            <c:forEach items="${book.authors}" var="author" varStatus="status">
                                ${author.authorName}${!status.last ? ', ' : ''}
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            Chưa rõ
                        </c:otherwise>
                    </c:choose>
                </p>
                <p><b>Publisher:</b> ${book.publisher}</p>
                <p><b>Publisher_date:</b> ${book.publishDate}</p>
                <p><b>Quantity:</b> ${book.quantity}</p>
                <p><b>Review:</b> (10)</p>
            </div>
        </c:forEach>
    </div>

    <!-- Thanh phân trang -->
    <div class="pagination">
        <c:forEach begin="1" end="${endPage}" var="i">
            <a href="${pageContext.request.contextPath}/home?page=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
        </c:forEach>
    </div>
</body>
</html>