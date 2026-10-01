<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử đặt hàng</title>
    <style>
        .history-container { max-width: 900px; margin: auto; padding: 20px; }
        .filter-form { margin-bottom: 20px; padding: 15px; background: #ecf0f1; border-radius: 5px; }
        .filter-form select, .filter-form button { padding: 8px; font-size: 16px; }
        .btn-filter { background: #3498db; color: white; border: none; cursor: pointer; border-radius: 4px; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; text-align: center; }
        th, td { padding: 12px; border: 1px solid #bdc3c7; }
        th { background: #2c3e50; color: white; }
        .status-badge { padding: 5px 10px; border-radius: 15px; font-weight: bold; font-size: 14px; background: #f1c40f; color: #333;}
    </style>
</head>
<body>
    <div class="history-container">
        <h2>📦 Lịch sử đặt hàng của bạn</h2>

        <!-- Bộ lọc trạng thái -->
        <div class="filter-form">
            <form action="${pageContext.request.contextPath}/order-history" method="get">
                <label for="status"><b>Lọc theo trạng thái:</b></label>
                <select name="status" id="status">
                    <option value="Tất cả" ${currentStatus == 'Tất cả' ? 'selected' : ''}>-- Tất cả đơn hàng --</option>
                    <option value="đơn hàng mới" ${currentStatus == 'đơn hàng mới' ? 'selected' : ''}>Đơn hàng mới</option>
                    <option value="đã xác nhận" ${currentStatus == 'đã xác nhận' ? 'selected' : ''}>Đã xác nhận</option>
                    <option value="chuẩn bị hàng" ${currentStatus == 'chuẩn bị hàng' ? 'selected' : ''}>Chuẩn bị hàng</option>
                    <option value="vận chuyển" ${currentStatus == 'vận chuyển' ? 'selected' : ''}>Vận chuyển</option>
                    <option value="giao hàng" ${currentStatus == 'giao hàng' ? 'selected' : ''}>Giao hàng</option>
                    <option value="đã giao" ${currentStatus == 'đã giao' ? 'selected' : ''}>Đã giao</option>
                    <option value="đơn hàng hủy" ${currentStatus == 'đơn hàng hủy' ? 'selected' : ''}>Đơn hàng hủy</option>
                    <option value="đơn hàng hoàn" ${currentStatus == 'đơn hàng hoàn' ? 'selected' : ''}>Đơn hàng hoàn</option>
                </select>
                <button type="submit" class="btn-filter">Lọc Đơn Hàng</button>
            </form>
        </div>

        <!-- Bảng danh sách đơn hàng -->
        <c:if test="${empty listOrders}">
            <p style="color: red; font-size: 18px;">Không tìm thấy đơn hàng nào ở trạng thái này!</p>
        </c:if>
        
        <c:if test="${not empty listOrders}">
            <table>
                <tr>
                    <th>Mã Đơn (ID)</th>
                    <th>Ngày đặt</th>
                    <th>Trạng thái</th>
                    <th>Tổng tiền</th>
                </tr>
                <c:forEach items="${listOrders}" var="order">
                    <tr>
                        <td><b>#${order.orderId}</b></td>
                        <td>${order.orderDate}</td>
                        <td><span class="status-badge">${order.status}</span></td>
                        <td style="color: red; font-weight: bold;">${order.totalAmount} $</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        
        <br>
        <a href="${pageContext.request.contextPath}/home" style="padding: 10px 15px; background: #27ae60; color: white; text-decoration: none; border-radius: 5px;">Tiếp tục mua sắm</a>
    </div>
</body>
</html>