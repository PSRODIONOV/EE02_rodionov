<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FlowerShop</title>
     <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
</head>
<body>

    <h2>REGISTRATION PAGE</h2>
    <form method = "post" action = "/flowershop/user/register">
        <input type = "text" name = "login" placeholder = "login" id="input2"></input>
        <input type = "password" name = "password" placeholder = "password"></input>
         <input type = "text" name = "address" placeholder = "address"></input>
        <button type = "submit" id="button">registration</button>
        <p id="err">${err}</p>
    </form>

<script type="text/javascript">
    var input2 = document.getElementById("input2");
    var err = document.getElementById("err");
   input2.addEventListener(
   "blur", ()=>{
        if(input2.value != ""){
            $.ajax(
            {
                type: "GET",
                dataType: "json",
                url: "http://localhost:8083/flowershop/rest/user/login/" + input2.value,
                success: function(data)
                {
                console.log(input2.value);
                console.log(data);

                   if(data == "false")
                   {
                        input2.className = "succes";
                        err.innerText = '<font color="green">Login is free</font>';
                        button.disabled = "";
                   }
                   else
                   {
                        input2.className = "error";
                        err.innerHtml = '<font color="red">Login is huyusy</font>';
                        button.disabled = "disabled";
                   }
                }

            });
             }
       } )
    </script>
</body>
</html>