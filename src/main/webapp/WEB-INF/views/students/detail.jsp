<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container py-4">

  <!-- 헤더 + 액션 -->
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="h4 mb-0">학생 상세</h1>
    <a class="btn btn-outline-secondary btn-sm"
       href="${pageContext.request.contextPath}/students">← 목록으로</a>
  </div>

  <!-- 플래시 메시지 -->
  <c:if test="${not empty param.msg}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ${param.msg}
      <button type="button" class="close" data-dismiss="alert" aria-label="닫기">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
  </c:if>

  <!-- 기본 정보 -->
  <div class="card shadow-sm mb-3">
    <div class="card-header bg-white">
      <strong>기본 정보</strong>
    </div>
    <div class="card-body p-0">
      <table class="table table-sm table-bordered mb-0">
        <tbody>
        <tr>
          <th class="w-25">ID</th>
          <td>${student.id}</td>
        </tr>
        <tr>
          <th>학번</th>
          <td>${student.studentNo}</td>
        </tr>
        <tr>
          <th>이름</th>
          <td>${student.name}</td>
        </tr>
        <tr>
          <th>Email</th>
          <td>${student.email}</td>
        </tr>
        <tr>
          <th>학과</th>
          <td>${student.dept}</td>
        </tr>
        <tr>
          <th>생성일</th>
          <td>${student.createdAt}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>

  <!-- 수강 중인 강좌 -->
  <div class="card shadow-sm mb-3">
    <div class="card-header bg-white d-flex align-items-center">
      <strong>수강 중인 강좌</strong>
      <span class="badge badge-pill badge-primary ml-2">
        <c:out value="${fn:length(enrolledCourses)}" />
      </span>
    </div>

    <div class="card-body p-0">
      <c:choose>
        <c:when test="${empty enrolledCourses}">
          <div class="p-4 text-muted text-center mb-0">수강 중인 강좌가 없습니다.</div>
        </c:when>
        <c:otherwise>
          <table class="table table-hover table-sm mb-0">
            <thead class="thead-light">
            <tr>
              <th style="width:80px">ID</th>
              <th>코드</th>
              <th>강좌명</th>
              <th>담당</th>
              <th style="width:80px">학점</th>
              <th class="text-right" style="width:120px">관리</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${enrolledCourses}">
              <tr>
                <td>${c.id}</td>
                <td>[${c.code}]</td>
                <td>
                  <a class="font-weight-bold"
                     href="${pageContext.request.contextPath}/courses/${c.id}">
                      ${c.title}
                  </a>
                </td>
                <td>${c.professor}</td>
                <td>${c.credit}</td>
                <td class="text-right">
                  <form method="post"
                        action="${pageContext.request.contextPath}/enrollments/cancel"
                        class="d-inline"
                        onsubmit="return confirm('해당 강좌를 수강취소 하시겠습니까?');">
                    <input type="hidden" name="studentId" value="${student.id}">
                    <input type="hidden" name="courseId"  value="${c.id}">
                    <button class="btn btn-sm btn-outline-danger" type="submit">수강취소</button>
                  </form>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <div class="card shadow-sm">
    <div class="card-header bg-white">
      <strong>수강신청</strong>
    </div>
    <div class="card-body">
      <form class="form-inline" method="post" action="${pageContext.request.contextPath}/enrollments">
        <input type="hidden" name="studentId" value="${student.id}">
        <label class="sr-only" for="courseId">강좌 선택</label>
        <select id="courseId" class="form-control mr-2 mb-2" name="courseId" required>
          <c:forEach var="c" items="${availableCourses}">
            <option value="${c.id}">${c.title} (${c.code})</option>
          </c:forEach>
        </select>
        <button class="btn btn-success mb-2" type="submit">신청</button>
      </form>

      <c:if test="${empty availableCourses}">
        <small class="text-muted d-block mt-2">신청 가능한 강좌가 없습니다.</small>
      </c:if>
    </div>
  </div>

</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
