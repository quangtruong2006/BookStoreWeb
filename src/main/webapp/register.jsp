<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
</head>
<body>
    <div style="width: 300px; margin: 0 auto; text-align: center;">
        <h2>Đăng Ký</h2>
        <p style="color: red;">${error}</p>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <input type="text" name="fullname" placeholder="Họ và tên" required style="width: 100%; margin-bottom: 10px; padding: 5px;"/><br/>
            <input type="email" name="email" placeholder="Email" required style="width: 100%; margin-bottom: 10px; padding: 5px;"/><br/>
            <input type="password" name="password" placeholder="Mật khẩu" required style="width: 100%; margin-bottom: 10px; padding: 5px;"/><br/>
            <button type="submit" style="width: 100%; padding: 10px;">Đăng ký & Nhận OTP</button>
        </form>
        <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
    </div>
</body>
</html>