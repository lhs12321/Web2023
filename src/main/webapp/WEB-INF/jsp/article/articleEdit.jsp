<!DOCTYPE html>
<html>
<head>
  <title>글 수정</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../../css/default.css">
  <style>
    input[type='text'] {width:90%;}
    textarea {width:90%; height:200px;}
  </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<main>
  <h3>글 수정</h3>
  <form action="./updateArticle" method="post">
    <p><input type="text" name="title" value="${article.titleEncoded}"
              placeholder="제목" required autofocus/></p>
    <p><textarea name="content" placeholder="내용"
                 required>${article.contentEncoded}</textarea></p>
    <p>
      <button type="submit">저장</button>
    </p>
    <input type="hidden" name="articleId" value="${article.articleId}"/>
  </form>
</main>
</body>
</html>