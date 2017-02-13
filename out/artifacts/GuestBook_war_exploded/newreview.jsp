<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your review</title>
</head>
<form method="get" action="newreview">
    Enter your name, please: <input type="text" name="name"> <br>
    <br>Enter your comment, please: <textarea name="text"></textarea><br>
    <br><select name="grade">
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
</select><br>
    <br>
    <input type="submit">
</form>
</body>
</html>
