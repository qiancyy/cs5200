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
<h1>Create House info</h1>
<form action="housecreate" method="post">
    <p>
        <label for="bed">bed</label>
        <input id="bed" name="bed" value="">
    </p>
    <p>
        <label for="bath">bath</label>
        <input id="bath" name="bath" value="">
    <p>
        <label for="price">price</label>
        <input id="price" name="price" value="">
    </p>
    <p>
        <label for="acreLot">acreLot</label>
        <input id="acreLot" name="acreLot" value="">
    </p>
    <p>
        <label for="fullAddress">fullAddress</label>
        <input id="fullAddress" name="fullAddress" value="">
    </p>
    <p>
        <label for="street">street</label>
        <input id="street" name="street" value="">
    </p>
    <p>
        <label for="city">city</label>
        <input id="city" name="city" value="">
    </p>
    <p>
        <label for="state">state</label>
        <input id="state" name="state" value="">
    </p>
    <p>
        <label for="zipCode">zipCode</label>
        <input id="zipCode" name="zipCode" value="">
    </p>
    <p>
        <label for="houseSize">houseSize</label>
        <input id="houseSize" name="houseSize" value="">
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