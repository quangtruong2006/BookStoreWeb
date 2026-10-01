<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Cập nhật Tác giả</title></head>
<body>
    <h2>${author != null ? 'Sửa Tác Giả' : 'Thêm Tác Giả'}</h2>
    <form action="${pageContext.request.contextPath}/admin/authors" method="post" style="max-width: 400px; padding: 20px; border: 1px solid #ccc;">
        <c:if test="${author != null}">
            <input type="hidden" name="authorId" value="${author.authorId}" />
        </c:if>
        
        <p>Tên Tác Giả:</p>
        <input type="text" name="authorName" value="${author.authorName}" required style="width: 100%; padding: 5px;"/>
        
        <p>Ngày sinh (yyyy-MM-dd):</p>
        <input type="date" name="dateOfBirth" value="${author.dateOfBirth}" required style="width: 100%; padding: 5px;"/>
        
        <br><br>
        <button type="submit" style="padding: 10px 20px; background: #2c3e50; color: white; border: none;">Lưu</button>
        <a href="${pageContext.request.contextPath}/admin/authors" style="margin-left: 10px;">Hủy</a>
    </form>
</body>
</html>