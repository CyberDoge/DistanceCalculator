<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Calculate</title>
    <script href="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  </head>
  <body>
    <form action="" method="post">
      <input type="text" name="from" placeholder="from city">
      <br/>
      <input type="text" name="to" placeholder="to city">
      <br/>
      <select name="type" id="type">
        <option>Crowflight</option>
        <option>Distance Matrix</option>
      </select>
      <br/>
      <input type="submit" value="send" id="submit">
    </form>
    <br/>
  <p id="result"></p>
  </body>
</html>
