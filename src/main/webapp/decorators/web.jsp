<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><decorator:title default="BookStore HUIT" /></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; }
        .header, .footer { background-color: #f1f1f1; padding: 20px; text-align: center; }
        .menu a { margin: 0 15px; text-decoration: none; color: #333; font-weight: bold; }
        .content { min-height: 400px; padding: 20px; }
    </style>
    <decorator:head />
</head>
<body>
    <div class="header">
        <h2>BookStore HUIT</h2>
        <div class="menu">
            <a href="${pageContext.request.contextPath}/home">Trang Chủ</a><a href="${pageContext.request.contextPath}/cart">Giỏ Hàng (${sessionScope.cart != null ? sessionScope.cart.size() : 0})</a><a href="${pageContext.request.contextPath}/order-history">Lịch sử đặt hàng</a>
            <a href="${pageContext.request.contextPath}/home">Sản phẩm</a>
            
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/logout">Đăng xuất (${sessionScope.user.fullname})</a>
                    <c:if test="${sessionScope.user.isAdmin == true}">
                        <a href="${pageContext.request.contextPath}/admin/home">Trang quản trị</a>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                </c:otherwise>
            </c:choose>
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