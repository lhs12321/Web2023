<!DOCTYPE html>
<html>
<head>
    <title>음악정보 수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/moviesong.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        main {
            text-align: center;
            padding: 20px;
            background-color: #f1f1f1;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 400px; /* 추가된 부분 */
        }

        input[type='text'] {
            width: 95%; /* 변경된 부분 */
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
    </style>
</head>
<body>
<main>
    <h3>음악정보 수정</h3>
    <form action="./updateSong" method="post">
        <p><input type="text" name="title" value="${song.titleEncoded}"
                  placeholder="제목" required autofocus/></p>
        <p><input type="text" name="name" value="${song.nameEncoded}"
                  placeholder="가수" required autofocus/></p>
            <button type="submit">저장</button>
        </p>
        <input type="hidden" name="songId" value="${song.songId}"/>
    </form>
</main>
</body>
</html>