<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
</head>
<body>
    <h2>REGISTRATION PAGE</h2>
    <form method = "post" action = "user/register">
        <input type = "text" name = "login" placeholder = "login"></input>
        <input type = "password" name = "password" placeholder = "password"></input>
         <input type = "text" name = "address" placeholder = "address"></input>
        <button type = "submit" >registration</button>
    </form>
</body>
</html>