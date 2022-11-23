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


<h1>Matching Company</h1>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>description</th>

    </tr>
    <tr>
        <td>${company.getCompanyId()}</td>
        <td>${company.getCompanyName()}</td>
        <td>${company.getDescription()}</td>
    <tr>
</table>
</body>
</html>
