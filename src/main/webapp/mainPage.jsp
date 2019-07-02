<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
</head>
<body>
    <form method = "get">
        <h1>MAIN PAGE1</h1>
        <p>Name: <%= request.getAttribute("name")%></p>
        <p>Address: <%= request.getAttribute("address")%></p>
        <p>Wallet_Score: <%= request.getAttribute("wallet_score")%></p>
        <p>Discount: <%= request.getAttribute("discount")%> % </p>
    </form>
    <form method = "get" action = "/flowershop/loginPage.jsp">
        <button type = "submit"> Logout
        <% session.removeAttribute ("user"); %>
         </button>
    </form>
</body>
</html>