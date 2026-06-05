<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pictures</title>
</head>
<body>

<h2>All Pictures</h2>

<form method="post" action="/api/pictures/upload" enctype="multipart/form-data">
    <input type="file" name="file" required>
    <button type="submit">Upload</button>
</form>
<hr>
<a href="/api/top">Top Pictures</a>

<hr>

<c:forEach var="picture" items="${pictures}">
    <div>
        <p>Author: ${picture.author.username}</p>
        <p>Filename: ${picture.filename}</p>
        <p>Total votes: ${voteTotals[picture.id]}</p>

        <img src="/api/pictures/image/${picture.id}" width="250">

        <c:if test="${picture.author.id != currentUserId}">

            <form method="post" action="/api/votes/add">
                <input type="hidden" name="pictureId" value="${picture.id}">

                <input type="number" name="value" min="1" max="10" required>

                <button type="submit">Vote</button>
            </form>
        </c:if>

        <hr>
    </div>
</c:forEach>

</body>
</html>