<!DOCTYPE html>
<html>
<head>
    <title>영화 보기</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/moviesong.css">
    <style>
        input[type='text'] {width:90%;}
        textarea {width:90%; height:200px;}
        p.title {border-top:1px solid gray;font-weight:bold;}
        p.info {border-bottom:1px solid gray;}
    </style>
    <script>
        window.onload = () => {
            document.querySelector('#btnDel').onclick = () => confirm('삭제하시겠습니까?');
        }
    </script>
</head>
<body>
<main>
    <h3>영화 보기</h3>
        <button type="button" onclick="location.href='${sessionScope.CURRENT_MOVIE_LIST}'">영화 목록</button>
        <button type="button" onclick="location.href='./movieEdit?movieId=${movie.movieId}'">영화 수정</button>
        <button type="button" onclick="location.href='./deleteMovie?movieId=${movie.movieId}'">영화 삭제</button>
    </p>
    <table class="list">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>감독 이름</th>
        </tr>
        <tr>
            <td>${movie.movieId}</td>
            <td>${movie.titleEncoded}</td>
            <td>${movie.directorEncoded}</td>
        </tr>
    </table>
</main>
</body>
</html>