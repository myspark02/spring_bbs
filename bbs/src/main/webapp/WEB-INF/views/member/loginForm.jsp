<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head><meta charset="utf-8"><title>로그인 폼</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <c:if test="${msg != null}">
	  <div style="background-color:orange">
	  	${msg}
	  </div>
  </c:if>
    
  <h2>로그인  폼</h2>
  <p>로그인을 위해 아이디와 암호를 입력해 주세요. </p>
  <form action="login" method="post">
    <div class="form-group">
      <label for="usr">Id:</label>
      <input type="text" class="form-control" id="usr" name="id" required>
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" name="password" required>
    </div>
        <button type="submit" class="btn btn-primary">Login</button>
  </form>
</div>	
</body>
</html>
