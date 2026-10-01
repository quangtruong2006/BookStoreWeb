<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - <decorator:title default="Trang quản trị" /></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; }
        .header { background-color: #2c3e50; color: white; padding: 20px; text-align: center; }
        .footer { background-color: #2c3e50; color: white; padding: 20px; text-align: center; margin-top: auto; }
        .menu a { margin: 0 15px; text-decoration: none; color: #18bc9c; font-weight: bold; }
        .content { min-height: 500px; padding: 20px; }
    </style>
    <decorator:head />
</head>
<body>
    <div class="header">
        <h2>Trang Quản Trị Hệ Thống</h2>
        <div class="menu">
            <a href="${pageContext.request.contextPath}/home">Về Trang Chủ (User)</a>
            <a href="${pageContext.request.contextPath}/admin/books">Quản lý Sách</a>
            <a href="${pageContext.request.contextPath}/admin/authors">Quản lý Tác giả</a>
            <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
        </div>
    </div>

    <div class="content">
        <decorator:body /> 
    </div>

    <div class="footer">
        <p>Họ tên: Nguyễn Quang Trường | MSSV: [24110367] | Mã đề: [01]</p>
    </div>
</body>
</html>