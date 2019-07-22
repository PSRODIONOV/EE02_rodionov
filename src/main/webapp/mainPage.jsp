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
        <h1>MAIN PAGE</h1>
        <p>Name: ${user.login}  </p>
        <p>Wallet_Score: ${user.wallet_score}</p>
        <p>Discount: ${user.discount}% </p>
    </form>

    <form method = "get" action = "/flowershop/user/logout">
        <button type = "submit"> Logout </button>
    </form>

    <h2>CATALOG</h2>
    <h3>Filter for search</h3>
        <form method="post" action="/flowershop/service/search">
            <input type="text" name="from" placeholder="from"></input>
            <input type="text" name="to" placeholder="to"></input>
            <input type="text" name="name" placeholder="name"></input>
            <button type="submit"> Search </button>
        </form>
    <form method="post" action="/flowershop/service/addToBasket">
        <table>
            <tr>
                <td>Name</td>
                <td>Price</td>
                <td>Quantity</td>
            </tr>
            <input type="text" name="quantity" placeholder="quantity"></input>
            <c:forEach items = "${flowers}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.name_flower}</td>
                    <td>${iterator.price}</td>
                    <td>${iterator.quantity}</td>
                    <td><button type="submit" name="id_flower" value="${iterator.id_flower}"> Get</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <h2>BASKET</h2>
    <form method="post" action="/flowershop/service/removeFromBasket">
        <table>
            <tr>
                <td>Name</td>
                <td>Quantity</td>
                <td>Price</td>
            </tr>
            <c:forEach items = "${order.orderPositions}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.flowerDto.name_flower}</td>
                    <td>${iterator.quantity}</td>
                    <td> ${iterator.price} </td>
                    <td>
                        <button type="submit"
                                name="id_flower"
                                value="${iterator.flowerDto.id_flower}"
                                > Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>Total Price: ${order.totalPrice} </p>
    </form>

    <form method="post" action="/flowershop/service/createOrder">
        <button type="submit"> Create Order </button>
    </form>

    <h2>MY ORDERS</h2>
    <form method="post" action="/flowershop/service/payorder">
        <table>
            <tr>
                <td>Id</td>
                <td>Description</td>
                <td>Total Price</td>
                <td>Status</td>
            </tr>
            <c:forEach items = "${orders}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.id_order}</td>
                    <td>
                    <c:forEach items = "${iterator.orderPositions}" var="it" varStatus="rowStatus">
                        <p>${it.quantity}x ${it.flowerDto.name_flower}</p>
                    </c:forEach>
                    </td>
                    <td>${iterator.totalPrice}</td>
                    <td>${iterator.status}</td>
                    <td>
                    <button type="submit"
                            name="id_order"
                            value="${iterator.id_order}"
                            > To pay </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

</body>
</html>