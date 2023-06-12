<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <title>음악 목록</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/moviesong.css">
    <style>
        input[type='number'] {
            width:50px;
            text-align:center;
        }
    </style>
</head>
<body>
<main>
    <h3>음악 목록</h3>
    <form action="./songList" method="get">
        <p>
            페이지 : <input type="number" name="page" min="1" value="${limit.page}"
                         required autofocus/>행 : <input type="number" name="count" min="5"
                                                        step="5" value="${limit.count}" required/>
            <button type="submit">검색</button>
        </p>
        <button type="button" onclick="location.href='./songForm'" class="button">음악 추가</button>
    </form>
    <table class="list">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>가수</th>
        </tr>
        <c:forEach var="song" items="${songList}">
            <tr>
                <td>${song.songId}</td>
                <td><a
                        href="./song?songId=${song.songId}">${song.titleEncoded}</a>
                </td>
                <td>${song.nameEncoded}</td>
            </tr>
        </c:forEach>
    </table>
</main>
</body>
</html>