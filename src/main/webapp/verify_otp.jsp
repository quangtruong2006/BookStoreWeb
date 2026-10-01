<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác nhận OTP</title>
</head>
<body>
    <div style="width: 300px; margin: 0 auto; text-align: center;">
        <h2>Nhập Mã OTP</h2>
        <p>Mã OTP đã được gửi đến email: <b>${sessionScope.tempUser.email}</b></p>
        <p style="color: red;">${error}</p>
        <form action="${pageContext.request.contextPath}/verify-otp" method="post">
            <input type="text" name="otp" placeholder="Nhập 6 số OTP" required style="width: 100%; margin-bottom: 10px; padding: 5px;"/><br/>
            <button type="submit" style="width: 100%; padding: 10px;">Xác Nhận</button>
        </form>
    </div>
</body>
</html>