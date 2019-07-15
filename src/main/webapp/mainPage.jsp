<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/>
</head>
<body>
    <form method = "get">
        <h1>MAIN PAGE1</h1>
        <p>Name: <%= request.getAttribute("name")%> ${user.login}  </p>
        <p>Address: <%= request.getAttribute("address")%></p>
        <p>Wallet_Score: <%= request.getAttribute("wallet_score")%></p>
        <p>Discount: <%= request.getAttribute("discount")%> % </p>
    </form>

    <form method = "get" action = "/flowershop/loginPage.jsp">
        <button type = "submit"> Logout </button>
    </form>

    <form method="post" action="/flowershop/service/addToBasket">
        <table>
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Price</td>
            </tr>
            <%
                int i = 1;
            %>
            <c:forEach items = "${flowers}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.id_flower}</td>
                    <td>${iterator.name_flower}</td>
                    <td>${iterator.price}</td>
                    <td><input type="text" name="quantity<%=i%>"></input></td>
                    <td><button  type="submit" name="button" value="<%=i%>"  onClick="window.location.reload()"> Get</button>
                    </td>
                </tr>
                <%
                    i++;
                %>
            </c:forEach>
        </table>
    </form>

    <form>
        <table>
            <tr>
                <td>Id</td>
                <td>Quantity</td>
            </tr>
            <c:forEach items = "${order.orderPositions}" var="iterator" varStatus="rowStatus">
                <tr>
                    <td>${iterator.id_flower}</td>
                    <td>${iterator.quantity}</td>
                </tr>
            </c:forEach>
        </table>
    </from>

</body>
</html>