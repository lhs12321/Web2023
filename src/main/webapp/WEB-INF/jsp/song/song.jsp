<!DOCTYPE html>
<html>
<head>
    <title>음악 보기</title>
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
    <h3>음악 보기</h3>
    <button type="button" onclick="location.href='${sessionScope.CURRENT_SONG_LIST}'">음악 목록</button>
    <button type="button" onclick="location.href='./songEdit?songId=${song.songId}'">음악 수정</button>
    <button type="button" onclick="location.href='./deleteSong?songId=${song.songId}'">음악 삭제</button>
    <table class="list">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>가수</th>
        </tr>
        <tr>
            <td>${song.songId}</td>
            <td>${song.titleEncoded}</td>
            <td>${song.nameEncoded}</td>
        </tr>
    </table>
</main>
</body>
</html>