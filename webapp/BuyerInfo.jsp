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


<h1>Buyer Info</h1>
<table border="1">
    <tr>
        <th>firstName</th>
        <th>middleName</th>
        <th>lastName</th>
        <th>email</th>
        <th>phoneNumber</th>
        <th>zip</th>
        <th>address</th>
        <th>DOB</th>
        <th>Update</th>
    </tr>
    <tr>
        <td>${buyer.getFirstName()}</td>
        <td>${buyer.getMiddleName()}</td>
        <td>${buyer.getLastName()}</td>
        <td>${buyer.getEmail()}</td>
        <td>${buyer.getPhoneNumber()}</td>
        <td>${buyer.getZip()}</td>
        <td>${buyer.getAddress()}</td>
        <td>${buyer.getSimpleDOB()}</td>
        <td><a href="buyerupdate?userId=" onclick="location.href=this.href+`${buyer.getUserId()}`;return false;">Update</a></td>


    <tr>
    <h2>
        <a href="creditcardinfo?userId=" onclick="location.href=this.href+`${buyer.getUserId()}`;return false;">Credit card</a>
    </h2>
    <h2>
        <a href="findhouses?page=1">Find house</a>
    </h2>
    <h2>
        <a href="searchinfo?userId=" onclick="location.href=this.href+`${buyer.getUserId()}`;return false;">Searching history</a>
    </h2>
    
     <h2>
        <a href="messageinfo?userId=" onclick="location.href=this.href+`${buyer.getUserId()}`;return false;" >My Message</a>
    </h2>
    <h2>
        <a href="reviewinfo?isBuyer=true&userId=" onclick="location.href=this.href+`${buyer.getUserId()}`;return false;" >My Review</a>
    </h2>
</table>
</body>
</html>
