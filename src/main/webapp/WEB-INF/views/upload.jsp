<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 8/23/2023
  Time: 7:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload-image" method="post" enctype="multipart/form-data">
  <input type="file" name="mainimage">
  <input type="file" name="subimage" multiple>
  <button type="submit">Send</button>
</form>
</body>
</html>
