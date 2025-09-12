<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>

<div class="container py-4">

  <!-- 헤더 + 액션 -->
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="h4 mb-0">강좌 목록</h1>
    <a href="${pageContext.request.contextPath}/courses/new" class="btn btn-primary">강좌 등록</a>
  </div>

  <!-- 검색 -->
  <form class="form-inline mb-3" method="get">
    <label class="sr-only" for="q">검색어</label>
    <input id="q" type="text" name="q" value="${param.q}" class="form-control mr-2" placeholder="강좌명/강의코드 검색">
    <button class="btn btn-outline-secondary" type="submit">검색</button>
  </form>

  <!-- 빈 결과 -->
  <c:if test="${empty page.items}">
    <div class="alert alert-info mb-0">검색 결과가 없습니다.</div>
  </c:if>

  <!-- 목록 -->
  <c:if test="${not empty page.items}">
    <div class="card shadow-sm">
      <div class="card-body p-0">
        <table class="table table-hover table-sm mb-0">
          <thead class="thead-light">
          <tr>
            <th style="width:80px">#</th>
            <th style="width:140px">코드</th>
            <th>강좌명</th>
            <th style="width:160px">담당</th>
            <th style="width:80px">학점</th>
            <th class="text-right" style="width:180px">관리</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="c" items="${page.items}">
            <tr>
              <td>${c.id}</td>
              <td>[${c.code}]</td>
              <td>
                <a href="${pageContext.request.contextPath}/courses/${c.id}" class="font-weight-bold">
                    ${c.title}
                </a>
              </td>
              <td>${c.professor}</td>
              <td>${c.credit}</td>
              <td class="text-right">
                <a class="btn btn-sm btn-outline-primary"
                   href="${pageContext.request.contextPath}/courses/${c.id}/edit">수정</a>
                <form method="post"
                      action="${pageContext.request.contextPath}/courses/${c.id}/delete"
                      class="d-inline"
                      onsubmit="return confirm('삭제할까요?');">
                  <button class="btn btn-sm btn-outline-danger" type="submit">삭제</button>
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>

      <!-- 페이징 -->
      <c:if test="${page.totalPages > 1}">
        <div class="card-footer bg-white">
          <nav aria-label="Page navigation">
            <ul class="pagination mb-0">
              <li class="page-item ${page.number <= 1 ? 'disabled' : ''}">
                <a class="page-link" href="?page=${page.number - 1}&q=${param.q}" aria-label="이전">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>

              <c:forEach var="p" begin="1" end="${page.totalPages}">
                <li class="page-item ${p == page.number ? 'active' : ''}">
                  <a class="page-link" href="?page=${p}&q=${param.q}">${p}</a>
                </li>
              </c:forEach>

              <li class="page-item ${page.number >= page.totalPages ? 'disabled' : ''}">
                <a class="page-link" href="?page=${page.number + 1}&q=${param.q}" aria-label="다음">
                  <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </c:if>
    </div>
  </c:if>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
