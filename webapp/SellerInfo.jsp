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
<script type="text/javascript">
  function handleClick(houseId,userId)
  {
	  var a = document.getElementById(houseId);
	  a.href="houseupdate?houseId="+houseId+"&userId="+userId
	  return false;
  }
</script>

<h1>Seller Info</h1>
<table border="1">
    <tr>
        <th>firstName</th>
        <th>middleName</th>
        <th>lastName</th>
        <th>introduction</th>
        <th>email</th>
        <th>phoneNumber</th>
        <th>zip</th>
        <th>address</th>
        <th>company</th>
    </tr>
        <tr>
            <td>${seller.getFirstName()}</td>
            <td>${seller.getMiddleName()}</td>
            <td>${seller.getLastName()}</td>
            <td>${seller.getIntroduction()}</td>
            <td>${seller.getEmail()}</td>
            <td>${seller.getPhoneNumber()}</td>
            <td>${seller.getZip()}</td>
            <td>${seller.getAddress()}</td>
            <td>
                <a href="companyInfo?companyId=" onclick="location.href=this.href+`${seller.getCompany().getCompanyId()}`;return false;">
                    Company
                </a>
            </td>
    <tr>
     <h2>
        <a href="messageinfo?userId=" onclick="location.href=this.href+`${seller.getUserId()}`;return false;" >My Message</a>
    </h2>
    <h2>
        <a href="reviewinfo?isBuyer=false&userId=" onclick="location.href=this.href+`${seller.getUserId()}`;return false;" >My Review</a>
    </h2>
</table>
<br/>
	<br/>
	<div id="postHouse"><a href="housecreate?sellerId=${seller.getUserId()}">Post new House</a></div>
	<br/>
<br/>
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
    </tr>
    <c:forEach items="${list}" var="house">
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
                                
             <td>
                <%-- <a href="houseupdate?houseId=" onclick="location.href=this.href+`${house.getHouseId()}`;return false;"> --%>
                 <a id="${house.getHouseId()}" href="houseupdate?houseId=" onclick="handleClick(${house.getHouseId()},${seller.getUserId()})">
                    Update
                </a>
            </td>
             <td>
                 <a href="housedelete?houseId=" onclick="location.href=this.href+`${house.getHouseId()}`;return false;">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
