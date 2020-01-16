<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="include.jsp" %>
<title>Insert title here</title>
</head>
<body>
<form action="login" method="post">
  <div class="form-group">
    <label for="labelLogin">Login</label>
    <input type="text" name="login" class="form-control" placeholder="Enter login" required="required">
  </div>
  <div class="form-group">
    <label for="labelPwd">Password</label>
    <input type="password" name="password" class="form-control" placeholder="Password" required="required">
  </div>
  <div><span>${error}</span></div>
  <button type="submit" class="btn btn-primary">Sign In</button>
</form>

</body>
</html>