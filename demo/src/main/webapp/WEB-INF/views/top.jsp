<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Top Pictures</title>
</head>
<body>

<h2>Top N Pictures</h2>

<form method="post" action="/api/top">
    <label>Number of pictures:</label>
    <input type="number" name="n" min="1" required>
    <button type="submit">Show Top</button>
</form>

<hr>

<c:if test="${not empty topPictures}">
    <c:forEach var="item" items="${topPictures}">
        <div>
            <p>Author: ${item.picture.author.username}</p>
            <p>Filename: ${item.picture.filename}</p>
            <p>Total votes: ${item.totalVotes}</p>

            <img src="/api/pictures/image/${item.picture.id}" width="250">

            <hr>
        </div>
    </c:forEach>
</c:if>

<a href="/api/pictures">Back to pictures</a>

</body>
</html>