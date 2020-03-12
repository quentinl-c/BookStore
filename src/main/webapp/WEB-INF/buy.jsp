<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  	 <%@include file="header.jsp"%>
    <title>Cart</title>
</head>
<body>
    <div class="grid-container">
        <div class="grid-x grid-padding-x">
            <div class="large-12 cell">
                <h2>Book Store</h2>

                <h3>Thank you <c:out value='${info.name}'></c:out> for your purchases ! :-)</h3>
                
                <p>Your order for an amount of <c:out value='${info.totalAmount}'></c:out> € will be delivered soon at the address : <c:out value='${info.address}'></c:out></p>
            </div>
        </div>
    </div>
</body>
</html>