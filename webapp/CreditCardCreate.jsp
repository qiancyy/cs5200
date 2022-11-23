<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Create a Credit Card</title>
</head>
<body>
<h1>Add Credit Card</h1>
<form action="creditcardcreate" method="post">
    <p>
        <label for="cardNumber">cardNumber</label>
        <input id="cardNumber" name="cardNumber" value="">
    </p>
    <p>
        <label for="expirationDate">expirationDate(yyyy-mm-dd)</label>
        <input id="expirationDate" name="expirationDate" value="">
    </p>
<%--    <p>--%>
<%--        <label for="lastname">LastName</label>--%>
<%--        <input id="lastname" name="lastname" value="">--%>
<%--    </p>--%>
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