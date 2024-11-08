<%--
  Created by IntelliJ IDEA.
  User: daidu
  Date: 13/09/2024
  Time: 3:26 CH
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
    <title>This is dashboard</title>
</head>
<body>
<form action="controller" method="post" id="form">
    <p>Account ID: </p>
    <input type="text" id="account_id" value="<%= account.getAccountId()%>" disabled    ><br>
    <p>Fullname: </p>
    <input type="text" id="fullname" value="<%= account.getFullName()%>" disabled><br>
    <p>Email: </p>
    <input type="text" id="email" value="<%= account.getEmail()%>" disabled><br>
    <p>Phone: </p>
    <input type="text" id="phone" value="<%= account.getPhone()%>" disabled><br>
    <p>Role: </p>
    <p id = "roleName">
            <%
        for (String roleName : roleNames) { %>
    <p><%= roleName %></p>
    <%}%>
    </p>
    <input type="submit" name="action" value="Logout">
    <input type="submit" name="action" value="Show all account">
    <input type="text" name="roleName" placeholder="Role name">
    <input type="submit" name="action" value="show accounts by role">
    <input type="button" onclick="enableInputs(event)" id="editButton" value="edit">
    <input type="hidden" name="action" id="action" value="save">
</form>
<script>
    function enableInputs(event) {
        var inputs = document.getElementsByTagName('input');
        var editButton = document.getElementById('editButton');
        var actionInput = document.getElementById('action');
        var form = document.getElementById('form');
        if (inputs[1].disabled) {
            for (var i = 1; i < 4; i++) {
                inputs[i].disabled = false;
            }
            editButton.value = "save";
            actionInput.value = "edit";
        } else {
            event.preventDefault();
            for (var i = 1; i < 4; i++) {
                inputs[i].disabled = true;
            }
            editButton.value = "edit";
            actionInput.value = "save";
            // AJAX request
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "controller", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                }
            }
            xhr.send("action=save&accountId=" + inputs[0].value + "&fullName=" + inputs[1].value + "&email=" + inputs[2].value + "&phone=" + inputs[3].value);
        }
    }
</script>
</body>
</html>