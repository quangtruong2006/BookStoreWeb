<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Quản lý Tác giả</title></head>
<body>
    <h2>Danh Sách Tác Giả</h2>
    <a href="${pageContext.request.contextPath}/admin/authors?action=add" style="padding: 10px; background: #28a745; color: white; text-decoration: none;">+ Thêm Tác Giả</a>
    
    <table style="width: 100%; margin-top: 20px; border-collapse: collapse;" border="1">
        <tr style="background: #2c3e50; color: white;">
            <th>ID</th> <th>Tên Tác Giả</th> <th>Ngày Sinh</th> <th>Thao tác</th>
        </tr>
        <c:forEach items="${listAuthors}" var="a">
            <tr style="text-align: center;">
                <td>${a.authorId}</td> <td>${a.authorName}</td> <td>${a.dateOfBirth}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/authors?action=edit&id=${a.authorId}">Sửa</a> | 
                    <a href="${pageContext.request.contextPath}/admin/authors?action=delete&id=${a.authorId}" onclick="return confirm('Xóa tác giả này?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div style="margin-top: 20px; text-align: center;">
        <c:forEach begin="1" end="${endPage}" var="i">
            <a href="${pageContext.request.contextPath}/admin/authors?page=${i}" style="padding: 5px 10px; border: 1px solid #000; ${currentPage == i ? 'background:#2c3e50; color:white;' : ''}">${i}</a>
        </c:forEach>
    </div>
</body>
</html>