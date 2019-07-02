<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
</head>
<body>
    <form method = "get">
        <h1>TEST</h1>
        <p id = "message">Message: ${message}</p>
        message: <%= request.getAttribute("message") %>
    </form>
</body>
</html>