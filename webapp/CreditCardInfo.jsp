<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find Houses</title>
</head>
<body>
<h1>Owned credit cards</h1>
	<br/>
	<div id="credCardCreate"><a href="creditcardcreate?userId=" onclick="location.href=this.href+`${userId}`;return false;">Add credit card</a></div>
	<br/>
<table border="1">
    <tr>
        <th>CardNumber</th>
        <th>expirationDate</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${list}" var="creditCard">
        <tr>
            <td><c:out value="${creditCard.getCardNumber()}" /></td>
            <td><c:out value="${creditCard.getSimpleDate()}" /></td>
            <td><a
                    href="creditcardupdate?cardNumber=<c:out value="${creditCard.getCardNumber()}"/>">Update</a></td>
            <td><a
                    href="creditcarddelete?cardNumber=<c:out value="${creditCard.getCardNumber()}"/>">Delete</a></td>
        </tr>
        </tr>
    </c:forEach>
</table>
</body>
</html>
