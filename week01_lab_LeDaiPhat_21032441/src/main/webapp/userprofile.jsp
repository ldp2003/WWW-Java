<%--
  Created by IntelliJ IDEA.
  User: daidu
  Date: 13/09/2024
  Time: 3:42 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="entities.Account" %>
<%@ page import="java.util.ArrayList" %>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    ArrayList<String> roleNames = (ArrayList<String>) session.getAttribute("roleName");
%>
<html>
<head>
    <title>User profile</title>
</head>
<body>
<form action="controller" method="get">
<p>Account ID: </p>
<p id="account_id"><%= account.getAccountId()%></p><br>
<p>Fullname: </p>
<p id="fullname"><%= account.getFullName() %></p><br>
<p>Email: </p>
<p id="email"><%= account.getEmail() %></p><br>
<p>Phone: </p>
<p id="phone"><%= account.getPhone() %></p><br>
<p>Role: </p>
<p id = "roleName">
    <%
        for (String roleName : roleNames) { %>
            <p><%= roleName %></p>
    <%}%>
</p>
<input type="submit" name="action" value="Logout">
</form>
</body>
</html>
