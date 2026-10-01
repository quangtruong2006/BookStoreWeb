<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
    <div style="width: 300px; margin: 0 auto; text-align: center;">
        <h2>Đăng Nhập</h2>
        <p style="color: green;">${param.message == 'register_success' ? 'Đăng ký thành công! Vui lòng đăng nhập.' : ''}</p>
        <p style="color: red;">${error}</p>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="email" name="email" placeholder="Email" required style="width: 100%; margin-bottom: 10px; padding: 5px;"/><br/>
            <input type="password" name="password" placeholder="Mật khẩu" required style="width: 100%; margin-bottom: 10px; padding: 5px;"/><br/>
            <button type="submit" style="width: 100%; padding: 10px;">Đăng Nhập</button>
        </form>
        <p>Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a></p>
    </div>
</body>
</html>