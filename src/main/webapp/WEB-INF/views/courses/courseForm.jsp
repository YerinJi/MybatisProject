<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container">
  <h3 class="mb-3">${mode == 'edit' ? '강좌 수정' : '강좌 등록'}</h3>

  <!-- 유효성 에러 -->
  <c:if test="${not empty errors}">
    <div class="alert alert-danger">
      <ul class="mb-0">
        <c:forEach var="e" items="${errors}">
          <li>${e}</li>
        </c:forEach>
      </ul>
    </div>
  </c:if>

  <form method="post" action="${action}">
    <!-- edit 시 id 전달이 필요하면 hidden으로 포함 -->
    <c:if test="${mode == 'edit'}">
      <input type="hidden" name="id" value="${course.id}">
    </c:if>

    <div class="form-row">
      <div class="form-group col-md-4">
        <label for="code">강좌 코드</label>
        <input id="code" name="code" value="${course.code}" class="form-control" required
               placeholder="예: CS101">
        <small class="form-text text-muted">영문/숫자 조합 권장 (예: CS101, MATH201)</small>
      </div>
      <div class="form-group col-md-8">
        <label for="title">강좌명</label>
        <input id="title" name="title" value="${course.title}" class="form-control" required
               placeholder="예: 자료구조">
      </div>
    </div>

    <div class="form-row">
      <div class="form-group col-md-6">
        <label for="professor">담당 교수</label>
        <input id="professor" name="professor" value="${course.professor}" class="form-control"
               placeholder="예: 홍길동">
      </div>
      <div class="form-group col-md-3">
        <label for="credit">학점</label>
        <input id="credit" name="credit" value="${course.credit}" type="number" min="0" max="6" step="1"
               class="form-control" required>
      </div>
      <div class="form-group col-md-3">
        <label for="capacity">정원</label>
        <input id="capacity" name="capacity" value="${course.capacity}" type="number" min="0" step="1"
               class="form-control">
      </div>
    </div>

    <div class="form-row">
      <div class="form-group col-md-6">
        <label for="room">강의실</label>
        <input id="room" name="room" value="${course.room}" class="form-control" placeholder="예: 공학관 301">
      </div>
      <div class="form-group col-md-6">
        <label for="schedule">시간표</label>
        <input id="schedule" name="schedule" value="${course.schedule}" class="form-control"
               placeholder="예: 월/수 10:30–12:00">
      </div>
    </div>

    <div class="text-right">
      <a href="${pageContext.request.contextPath}/courses" class="btn btn-secondary">취소</a>
      <button class="btn btn-primary" type="submit">${mode == 'edit' ? '수정' : '등록'}</button>
    </div>
  </form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
