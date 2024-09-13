<%@ page import="entities.Account" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: daidu
  Date: 13/09/2024
  Time: 5:49 CH
  To change this template use File | Settings | File Templates.
--%>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    ArrayList<String> roleNames = (ArrayList<String>) session.getAttribute("roleName");
    ArrayList<Account> accounts = (ArrayList<Account>) session.getAttribute("accounts");
%>
<html>
<head>
    <title>Table account</title>
</head>
<body>
<table>
    <tr>
        <th>Account ID</th>
        <th>Phone</th>
        <th>Password</th>
        <th>Full Name</th>
        <th>Email</th>
    </tr>
    <% for(Account a : accounts) { %>
    <tr>
        <td><%= a.getAccountId() %></td>
        <td><%= a.getPhone() %></td>
        <td><%= a.getPassword() %></td>
        <td><%= a.getFullName() %></td>
        <td><%= a.getEmail() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
