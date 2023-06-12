<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
  <ul class="nav">
    <li><a href="${pageContext.request.contextPath}/">홈</a></li>
    <li><a href="../user/userList">회원</a></li>
    <li><a href="../article/articleList">게시글</a></li>
    <c:choose>
      <c:when test="${empty sessionScope.me_userId}">
        <li><a href="../user/signinForm">로그인</a></li>
      </c:when>
      <c:otherwise>
        <li><a href="../user/myInfo">${sessionScope.me_name}님</a></li>
        <li><a href="../user/signout">로그아웃</a></li>
      </c:otherwise>
    </c:choose>
    <li><a href="../movie/movieList">영화 목록</a></li>
    <li><a href="../song/songList">음악 목록</a></li>
  </ul>
</header>