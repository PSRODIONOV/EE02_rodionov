<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
</head>
<body>
    <form>
        <h1>MAIN PAGE</h1>
        <p>Name: ${USER.login}  </p>
        <p>Wallet_Score: ${USER.walletScore}</p>
        <p>Discount: ${USER.discount}% </p>
    </form>

    <form method = "get" action = "/flowershop/user/logout">
        <button type = "submit"> Logout </button>
    </form>

    <h2>CATALOG</h2>
    <h3>Filter for search</h3>
        <form method="post" action="/flowershop/service/search">
            <div class="panel">
                <input type="text" name="from" placeholder="from"></input>
                <input type="text" name="to" placeholder="to"></input>
                <input type="text" name="name" placeholder="name"></input>
                <button type="submit"> Search </button>
            </div>
        </form>
    <form method="post" action="/flowershop/service/addToBasket">
        <div>
            <table>
                <tbody>
                    <tr>
                        <td>Name</td>
                        <td>Price</td>
                        <td>Quantity</td>
                    </tr>
                    <c:forEach items = "${FLOWERS}" var="iterator" varStatus="rowStatus">
                        <tr>
                            <td>${iterator.nameFlower}</td>
                            <td>${iterator.price}</td>
                            <td>${iterator.quantity}</td>
                            <td>
                                <c:choose>
                                    <c:when  test="${iterator.quantity > 0}">
                                        <button type="submit" name="idFlower" value="${iterator.idFlower}"> Get</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" name="idFlower" value="${iterator.idFlower}" disabled="disabled"> Get</button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="panel">
            <input type="text" name="quantity" placeholder="quantity"></input>
            <p id="err" name="err">${err}</p>
        </div>
    </form>

    <h2>BASKET1</h2>
    <form method="post" action="/flowershop/service/removeFromBasket">
        <table>
            <tr>
                <td>Name</td>
                <td>Quantity</td>
                <td>Price</td>
            </tr>
            <c:forEach items = "${BASKET.orderPositions}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.flowerDto.nameFlower}</td>
                    <td>${iterator.quantity}</td>
                    <td> <strike>${iterator.price}</strike> ${iterator.priceWithDiscount}</td>
                    <td>
                        <button type="submit"
                                name="idFlower"
                                value="${iterator.flowerDto.idFlower}"
                                > Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>Total Price: ${BASKET.totalPrice} </p>
    </form>

    <form method="post" action="/flowershop/service/createOrder">
          <c:choose>
            <c:when test="${BASKET.orderPositions.isEmpty()}">
              <button type="submit" id="buttoncreate" disabled="disabled"> Create Order </button>
            </c:when>
            <c:otherwise>
              <button type="submit" id="buttoncreate"> Create Order </button>
            </c:otherwise>
          </c:choose>
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
            <c:forEach items = "${ORDERS}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.idOrder}</td>
                    <td>
                    <c:forEach items = "${iterator.orderPositions}" var="it" varStatus="rowStatus">
                        <p>${it.quantity}x${it.flowerDto.nameFlower}</p>
                    </c:forEach>
                    </td>
                    <td>${iterator.totalPrice}</td>
                    <td>${iterator.status}</td>
                    <td>
                    <c:choose>
                       <c:when test="${iterator.status.toString() eq 'CREATED'}">
                           <button type="submit"
                                   name="idOrder"
                                   value="${iterator.idOrder}"
                           > To pay </button>
                       </c:when>
                       <c:otherwise>
                           <button type="submit"
                                   name="idOrder"
                                   value="${iterator.idOrder}"
                                   disabled="disabled"
                           > To pay </button>
                       </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

</body>
</html>