<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All reviews</title>
</head>
<body>
<center>
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
    <c:if test="${counter >1}">
        <c:url var="url" value="/allreviews">
            <c:param name="page" value="${counter - 1}"/>
        </c:url>
        <button><a href="${url}">back</a></button>
    </c:if>
    <c:forEach begin="0" end="${pages}" var="page">
        <c:url var="url" value="/allreviews">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <button><a href="${url}">${page + 1}</a></button>
    </c:forEach>
    <c:if test="${counter < pages + 1}">
        <c:url var="url" value="/allreviews">
            <c:param name="page" value="${counter + 1}"/>
        </c:url>
        <button><a href="${url}">next</a></button>
    </c:if>
</center>
</body>
</html>