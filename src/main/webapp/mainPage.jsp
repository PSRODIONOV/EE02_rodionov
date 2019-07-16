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
        <h1>MAIN PAGE1</h1>
        <p>Name: ${user.login}  </p>
        <p>Address: ${user.address}</p>
        <p>Wallet_Score: ${user.wallet_score}</p>
        <p>Discount: ${user.discount}</p>
    </form>

    <form method = "get" action = "/flowershop/user/logout">
        <button type = "submit"> Logout </button>
    </form>

    <h2>CATALOG</h2>
    <form method="post" action="/flowershop/service/addToBasket">
        <table>
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Price</td>
            </tr>
            <input type="text" name="quantity" placeholder="quantity"></input>
            <c:forEach items = "${flowers}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.id_flower}</td>
                    <td>${iterator.name_flower}</td>
                    <td>${iterator.price}</td>
                    <td><button type="submit" name="id_flower" value="${iterator.id_flower}"  onClick="window.location.reload()"> Get</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <h2>BASKET</h2>
    <form method="post" action="/flowershop/service/removeFromBasket">
        <table>
            <tr>
                <td>Id</td>
                <td>Quantity</td>
            </tr>
            <c:forEach items = "${order.orderPositions}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.flowerDto.id_flower}</td>
                    <td>${iterator.quantity}</td>
                    <td><button type="submit" name="id_flower" value="${iterator.flowerDto.id_flower}"  onClick="window.location.reload()"> Remove </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <h2>TEST</h2>
    <form method="post" action="/flowershop/service/createOrder">
        <button type="submit" action="window.location.reload()">Create Order</button>
    </form>

    <h2>MY ORDERS</h2>
    <form>
        <table>
            <tr>
                <td>Id</td>
                <td>Total Price</td>
            </tr>
            <c:forEach items = "${orders}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.id_order}</td>
                    <td>${iterator.totalPrice}</td>
                    <td><button type="submit" name="id_order" value="${iterator.id_order}"  onClick="window.location.reload()"> To pay </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

</body>
</html>