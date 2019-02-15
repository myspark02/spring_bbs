<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<!DOCTYPE html>
<html>
<head><meta charset="utf-8"><title>회원가입</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>회원가입</h2>
  <p>회원가입을 위해 아래의 모든 정보를 작성해 주세요. </p>
  <form action="join" method="post">
    <div class="form-group">
      <label for="usr">Id:</label>
      <input type="text" class="form-control" id="usr" name="id" minlength=5 required>
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" name="password" minlength="8" required>
    </div>
     <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" name="name" required>
    </div>
     <div class="form-group">
      <label for="email">email:</label>
      <input type="email" class="form-control" id="email" name="email" 
      pattern=".+@yju.ac.kr" required>
    </div>      
     <div class="form-group">
      <label for="email">phone:</label>
      <input type="tel" id="phone" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}">
    </div>         
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>	
</body>
</html>
