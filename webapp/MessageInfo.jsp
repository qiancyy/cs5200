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
<h1>Message info</h1>
<table border="1">
    <tr>
        <th>sendTime</th>
        <th>content</th>
        <th>fromID</th>
        <th>toID</th>
    </tr>
    <c:forEach items="${list}" var="message">
        <tr>
            <td><c:out value="${message.getSendTime()}" /></td>
            <td><c:out value="${message.getContent()}" /></td>
            <td><c:out value="${message.getFrom().getUserId()}" /></td>
            <td><c:out value="${message.getTo().getUserId()}" /></td>
        </tr>
        </tr>
    </c:forEach>
</table>
</body>
</html>
