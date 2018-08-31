<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send file</title>
</head>
<body>
<form  enctype="multipart/form-data" action="upload" method="post">
    <p>Send xml file</p>
    <input type="file" name="file">
    <br/>
    <input type="submit">
</form>
<div id="result"></div>
</body>
</html>
