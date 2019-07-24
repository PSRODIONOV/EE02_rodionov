<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
</head>
<body>
    <form>
        <h1> ADMIN MAIN PAGE</h1>
        <p>Name: ${user.login}  </p>
        <p>Role: ${user.role} </p>
    </form>

    <form method = "get" action = "/flowershop/user/logout">
        <button type = "submit"> Logout </button>
    </form>

    <h2>ALL ORDERS</h2>
    <form method="post" action="/flowershop/admin/closeorder">
        <table>
            <tr>
                <td>Id</td>
                <td>Description</td>
                <td>Total Price</td>
                <td>Status</td>
                <td>Date Create</td>
                <td>Date Close</td>
            </tr>
            <c:forEach items = "${orders}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.idOrder}</td>
                    <td>
                    <c:forEach items = "${iterator.orderPositions}" var="it" varStatus="rowStatus">
                        <p>${it.quantity}x ${it.flowerDto.nameFlower}</p>
                    </c:forEach>
                    </td>
                    <td>${iterator.totalPrice}</td>
                    <td>${iterator.status}</td>
                    <td>${iterator.dateCreate}</td>
                    <td>${iterator.dateClose}</td>
                    <td>
                    <c:if test = "${iterator.status eq 'paid'}">
                        <button type="submit"
                                name="idOrder"
                                value="${iterator.idOrder}"
                         > Close </button>
                    </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

</body>
</html>