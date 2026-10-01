<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${book != null ? 'Cập nhật Sách' : 'Thêm Sách'}</title>
    <style>
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; font-weight: bold; margin-bottom: 5px; }
        .form-group input, .form-group textarea { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn-submit { background-color: #2c3e50; color: white; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h2>${book != null ? 'Cập nhật Thông tin Sách' : 'Thêm Sách Mới'}</h2>
    
    <div style="max-width: 600px; background: #fff; padding: 20px; border: 1px solid #ccc;">
        <form action="${pageContext.request.contextPath}/admin/books" method="post">
            <c:if test="${book != null}">
                <input type="hidden" name="bookid" value="${book.bookid}" />
            </c:if>
            
            <div class="form-group">
                <label>Tiêu đề sách:</label>
                <input type="text" name="title" value="${book.title}" required />
            </div>
            
            <div class="form-group">
                <label>Mã ISBN:</label>
                <input type="number" name="isbn" value="${book.isbn}" required />
            </div>

            <div class="form-group">
                <label>Nhà Xuất Bản (Publisher):</label>
                <input type="text" name="publisher" value="${book.publisher}" />
            </div>

            <div class="form-group">
                <label>Ngày xuất bản (yyyy-MM-dd):</label>
                <input type="date" name="publishDate" value="${book.publishDate}" required />
            </div>

            <div class="form-group">
                <label>Giá tiền:</label>
                <input type="number" step="0.01" name="price" value="${book.price}" required />
            </div>
            
            <div class="form-group">
                <label>Số lượng (Quantity):</label>
                <input type="number" name="quantity" value="${book.quantity}" required />
            </div>
            
            <div class="form-group">
                <label>Link Ảnh Bìa (Cover Image):</label>
                <input type="text" name="coverImage" value="${book.coverImage}" />
            </div>

            <div class="form-group">
                <label>Mô tả (Description):</label>
                <textarea name="description" rows="4">${book.description}</textarea>
            </div>

            <button type="submit" class="btn-submit">Lưu Dữ Liệu</button>
            <a href="${pageContext.request.contextPath}/admin/books" style="margin-left: 15px;">Hủy bỏ</a>
        </form>
    </div>
</body>
</html>