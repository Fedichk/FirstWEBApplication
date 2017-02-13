<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All reviews</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Date</th>
        <th>Name</th>
        <th>Review</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${reviews}" var="review">
        <tr>
            <td><c:out value="${review.date}"/></td>
            <td><c:out value="${review.authorName}"/></td>
            <td><c:out value="${review.grade}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>