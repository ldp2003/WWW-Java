<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>My very first page</title>
</head>
<body>
<form action="controller" method="get">
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"required>
    <input type="submit" name="action" value="Login">
    <p class="message">
        <%= request.getAttribute("message") == null ? "" : "Sai tài khoản hoặc mật khẩu" %>
    </p>
</form>
</body>
</html>