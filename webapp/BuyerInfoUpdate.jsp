<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update a User</title>
</head>
<body>
<h1>Update info</h1>
<form action="buyerupdate" method="post">
    <p>
        <label for="firstName">firstName</label>
        <input id="firstName" name="firstName" value="${buyer.getFirstName()}">
    </p>
    <p>
        <label for="middleName">middleName</label>
        <input id="middleName" name="middleName" value="${buyer.getMiddleName()}">
    <p>
        <label for="lastName">lastName</label>
        <input id="lastName" name="lastName" value="${buyer.getLastName()}">
    </p>
    <p>
        <label for="email">email</label>
        <input id="email" name="email" value="${buyer.getEmail()}">
    </p>
    <p>
        <label for="phoneNumber">phoneNumber</label>
        <input id="phoneNumber" name="phoneNumber" value="${buyer.getPhoneNumber()}">
    </p>
    <p>
        <label for="zip">zip</label>
        <input id="zip" name="zip" value="${buyer.getZip()}">
    </p>
    <p>
        <label for="address">address</label>
        <input id="address" name="address" value="${buyer.getAddress()}">
    </p>
    <p>
        <label for="DOB">DOB(yyyy-mm-dd)</label>
        <input id="DOB" name="DOB" value="${buyer.getSimpleDOB()}">
    </p>
    </p>
        <p>
        <label for="password">password</label>
        <input id="password" name="password" value="${buyer.getPassword()}">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>