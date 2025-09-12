<%--
  Created by IntelliJ IDEA.
  User: yerin
  Date: 2025-09-09
  Time: 오후 6:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h4 style="margin-top:16px;">수강자 목록</h4>

<c:choose>
    <c:when test="${empty CourseStudents}">
        <div>수강자가 존재하지 않습니다.</div>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="c" items="${CourseStudents}">
                <li>
                     [${c.studentNo}] ${c.name} / ${c.email} ${c.dept}
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
</body>
</html>
