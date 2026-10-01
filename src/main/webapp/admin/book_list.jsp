<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Sách</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #2c3e50; color: white; }
        .btn { padding: 5px 10px; text-decoration: none; color: white; border-radius: 3px; }
        .btn-add { background-color: #28a745; display: inline-block; margin-bottom: 15px; padding: 10px 15px;}
        .btn-edit { background-color: #f39c12; }
        .btn-del { background-color: #e74c3c; }
        .pagination { margin-top: 20px; text-align: center; }
        .pagination a { padding: 5px 10px; margin: 0 2px; border: 1px solid #ccc; text-decoration: none; color: black; }
        .pagination a.active { background-color: #2c3e50; color: white; border-color: #2c3e50; }
    </style>
</head>
<body>
    <h2>Danh Sách Sách (Admin)</h2>
    <a href="${pageContext.request.contextPath}/admin/books?action=add" class="btn btn-add">+ Thêm Sách Mới</a>
    
    <table>
        <tr>
            <th>ID</th> <th>ISBN</th> <th>Tiêu đề</th> <th>Nhà xuất bản</th> <th>Giá</th> <th>Số lượng</th> <th>Thao tác</th>
        </tr>
        <c:forEach items="${listBooks}" var="b">
            <tr>
                <td>${b.bookid}</td>
                <td>${b.isbn}</td>
                <td>${b.title}</td>
                <td>${b.publisher}</td>
                <td>${b.price} $</td>
                <td>${b.quantity}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/books?action=edit&id=${b.bookid}" class="btn btn-edit">Sửa</a> | 
                    <a href="${pageContext.request.contextPath}/admin/books?action=delete&id=${b.bookid}" class="btn btn-del" onclick="return confirm('Bạn có chắc muốn xóa sách này?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="pagination">
        <c:forEach begin="1" end="${endPage}" var="i">
            <a href="${pageContext.request.contextPath}/admin/books?page=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
        </c:forEach>
    </div>
</body>
</html>