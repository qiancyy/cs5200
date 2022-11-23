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
<h1>Searching history</h1>
<table border="1">
    <tr>
        <th>bed</th>
        <th>bath</th>
        <th>price</th>
        <th>acreLot</th>
        <th>fullAddress</th>
        <th>street</th>
        <th>city</th>
        <th>state</th>
        <th>zipCode</th>
        <th>houseSize</th>
    </tr>
    <c:forEach items="${list}" var="house">
        <tr>
            <td><c:out value="${house.getBed()}" /></td>
            <td><c:out value="${house.getBath()}" /></td>
            <td><c:out value="${house.getPrice()}" /></td>
            <td><c:out value="${house.getAcreLot()}" /></td>
            <td><c:out value="${house.getFullAddress()}" /></td>
            <td><c:out value="${house.getStreet()}" /></td>
            <td><c:out value="${house.getCity()}" /></td>
            <td><c:out value="${house.getState()}" /></td>
            <td><c:out value="${house.getZipCode()}" /></td>
            <td><c:out value="${house.getHouseSize()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
