<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h2>Register</h2>

<p style="color:red;">${error}</p>

<form method="post" action="/api/users/register">
    <input type="text" name="username" placeholder="Username" required>
    <br><br>

    <input type="password" name="password" placeholder="Password" required>
    <br><br>

    <button type="submit">Register</button>
</form>

<a href="/api/users/login">Back to login</a>

</body>
</html>