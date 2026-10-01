<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng của bạn</title>
</head>
<body>
    <div style="max-width: 900px; margin: auto; padding: 20px;">
        <h2>🛒 Giỏ Hàng Của Bạn</h2>
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
        </c:if>

        <c:if test="${empty sessionScope.cart}">
            <p>Giỏ hàng của bạn đang trống.</p>
            <a href="${pageContext.request.contextPath}/home">Tiếp tục mua sắm</a>
        </c:if>

        <c:if test="${not empty sessionScope.cart}">
            <table border="1" style="width: 100%; text-align: center; border-collapse: collapse;">
                <tr style="background: #2c3e50; color: white;">
                    <th>Sản phẩm</th>
                    <th>Hình ảnh</th>
                    <th>Đơn giá</th>
                    <th>Số lượng (Còn lại)</th>
                    <th>Thành tiền</th>
                    <th>Thao tác</th>
                </tr>
                <c:forEach items="${sessionScope.cart}" var="item">
                    <tr>
                        <td>${item.book.title}</td>
                        <td><img src="${item.book.coverImage}" width="50" height="70"></td>
                        <td>${item.book.price} $</td>
                        <td>
                            <!-- Form cập nhật số lượng TỰ ĐỘNG -->
                            <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="bookId" value="${item.book.bookid}">
                                
                                <!-- Đã thêm onchange để tự động tính tiền khi click tăng/giảm hoặc gõ số -->
                                <input type="number" name="quantity" value="${item.quantity}" min="1" max="${item.book.quantity}" style="width: 50px;" onchange="this.form.submit()">
                                
                                <!-- Đã ẩn nút Sửa bằng thẻ noscript cho giao diện sạch đẹp hơn -->
                                <noscript><button type="submit" style="background:#f39c12; color:white; border:none; padding:5px;">Sửa</button></noscript>
                            </form>
                            <br><small>(Kho: ${item.book.quantity})</small>
                        </td>
                        <td>${item.totalPrice} $</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart?action=remove&bookId=${item.book.bookid}" 
                               style="color:red; font-weight:bold; text-decoration:none;"
                               onclick="return confirm('Xóa sản phẩm này?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            
            <h3 style="text-align: right; color: red;">Tổng cộng: ${totalCart} $</h3>
            
            <div style="text-align: right;">
                <a href="${pageContext.request.contextPath}/home" style="padding: 10px; background: #95a5a6; color: white; text-decoration:none;">Tiếp tục mua hàng</a>
                <a href="${pageContext.request.contextPath}/checkout" style="padding: 10px; background: #27ae60; color: white; text-decoration:none; margin-left: 10px;">Thanh Toán COD</a>
            </div>
        </c:if>
    </div>
</body>
</html>