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

    </form>
    <script type="text/javascript">
            ()=>{
                     $.ajax(
                     {
                         type: "GET",
                         dataType: "json",
                         url: "http://localhost:8083/flowershop/rest/user/login/" ,
                         success: function(data)
                         {
                            document.append('<div> Hello </div>')
                         }
                     });
                }
             </script>
</body>
</html>