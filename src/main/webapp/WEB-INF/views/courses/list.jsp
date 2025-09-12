<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>강좌 상세</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- Bootstrap 4.6 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container py-4">

    <!-- 헤더 -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1 class="h5 mb-0">수강자 목록</h1>
    </div>

    <!-- 수강자 카드 -->
    <div class="card shadow-sm">
        <div class="card-header bg-white d-flex align-items-center">
            <strong>수강자</strong>
            <span class="badge badge-pill badge-primary ml-2">
        <c:out value="${fn:length(CourseStudents)}" />
      </span>
        </div>

        <c:choose>
            <c:when test="${empty CourseStudents}">
                <div class="card-body text-muted text-center">
                    수강자가 존재하지 않습니다.
                </div>
            </c:when>
            <c:otherwise>
                <div class="card-body p-0">
                    <table class="table table-hover table-sm mb-0">
                        <thead class="thead-light">
                        <tr>
                            <th style="width:80px">ID</th>
                            <th style="width:140px">학번</th>
                            <th>이름</th>
                            <th>Email</th>
                            <th style="width:160px">학과</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="s" items="${CourseStudents}">
                            <tr>
                                <td>${s.id}</td>
                                <td>${s.studentNo}</td>
                                <td>
                                    <a class="font-weight-bold"
                                       href="${pageContext.request.contextPath}/students/${s.id}">
                                            ${s.name}
                                    </a>
                                </td>
                                <td>${s.email}</td>
                                <td>${s.dept}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</div>

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
