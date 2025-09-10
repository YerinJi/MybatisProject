<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><meta charset="UTF-8"><title>학생 상세</title></head>
<body>
<h3>학생 상세</h3>
<table border="1">
  <tr><th>ID</th><td>${student.id}</td></tr>
  <tr><th>학번</th><td>${student.student_no}</td></tr>
  <tr><th>이름</th><td>${student.name}</td></tr>
  <tr><th>Email</th><td>${student.email}</td></tr>
  <tr><th>학과</th><td>${student.dept}</td></tr>
  <tr><th>생성일</th><td>${student.created_at}</td></tr>
</table>

<a href="${pageContext.request.contextPath}/students">목록</a>
</body>
</html>
