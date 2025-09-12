<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><meta charset="UTF-8"><title>학생 상세</title></head>
<body>
<h3>학생 상세</h3>
<table border="1">
  <tr><th>ID</th><td>${student.id}</td></tr>
  <tr><th>학번</th><td>${student.studentNo}</td></tr>
  <tr><th>이름</th><td>${student.name}</td></tr>
  <tr><th>Email</th><td>${student.email}</td></tr>
  <tr><th>학과</th><td>${student.dept}</td></tr>
  <tr><th>생성일</th><td>${student.createdAt}</td></tr>
</table>

<h4 style="margin-top:16px;">수강 중인 강좌</h4>
<c:choose>
  <c:when test="${empty enrolledCourses}">
    <div>수강 중인 강좌가 없습니다.</div>
  </c:when>
  <c:otherwise>
    <ul>
      <c:forEach var="c" items="${enrolledCourses}">
        <li>
          <a href="${pageContext.request.contextPath}/courses/${c.id}">
            [${c.code}] ${c.title} / ${c.professor} (${c.credit}학점)
            <form method="post" action="${pageContext.request.contextPath}/enrollments/cancel" class="d-inline" onsubmit="return confirm('삭제할까요?');">
              <input type="hidden" name="studentId" value="${student.id}">
              <input type="hidden" name="courseId" value="${c.id}">
              <button class="btn btn-sm btn-outline-danger" type="submit">삭제</button>
            </form>
          </a>
        </li>
      </c:forEach>
    </ul>
  </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/students">목록</a>

<h5 class="mt-4">수강신청</h5>
<form class="form-inline" method="post" action="${pageContext.request.contextPath}/enrollments">
  <input type="hidden" name="studentId" value="${student.id}">
  <select class="form-control mr-2" name="courseId">
    <c:forEach var="c" items="${availableCourses}">
      <option value="${c.id}">${c.title} (${c.code})</option>
    </c:forEach>
  </select>
  <button class="btn btn-success" type="submit">신청</button>
</form>
</body>
</html>
