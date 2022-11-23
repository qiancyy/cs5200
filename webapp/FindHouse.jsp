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
<script type="text/javascript">
function next(page,bed,bath,city,zipcode,price){
	page = page+1;
	window.location.href = "findhouses?page="+page;
}
function prev(page){
	page = page-1;
	window.location.href = "findhouses?page="+page;
}
function disable(hasNext){
	const btn = document.getElementById('next');
	if(hasNext == true) btn.removeAttribute('disabled');
}
</script>
<body onload="disable(${hasNext})">
<form action="findhouses" method="post">
    <h1>Search for houses by bed, bath, city, zipCode, price</h1>
    <p>
        <label for="bed">bed</label>
        <input id="bed" name="bed" value="">
        <label for="bath">bath</label>
        <input id="bath" name="bath" value="">
        <label for="city">city</label>
        <input id="city" name="city" value="">
        <label for="zipCode">zipCode</label>
        <input id="zipCode" name="zipCode" value="">
        <label for="price">price</label>
        <input id="price" name="price" value="">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>


<h1>Matching Houses</h1>
<table border="1">
    <tr>
        <th>houseId</th>
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
        <th>soldDate</th>
        <th>seller</th>
    </tr>
    <c:forEach items="${houseList}" var="house">
        <tr>
            <td><c:out value="${house.getHouseId()}" /></td>
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
            <td><fmt:formatDate value="${house.getSoldDate()}"
                                pattern="yyyy-MM-dd" /></td>
            <td><a
                    href="seller?userId=<c:out value="${house.getSeller()}"/>">Seller</a></td>

        </tr>
    </c:forEach>
</table>
<span>
<button id ="prev" onclick="prev(${page})">Prev</button>
<button id= "next" onclick="next(${page})" disabled>Next</button>
<%-- <h5>${hasNext}</h5> --%>
</span>

</body>
</html>
