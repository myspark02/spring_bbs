<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 등록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.error{color:red} 
</style>
<link rel="stylesheet" href="resources/dist/dropzone.css">
<script src="resources/dist/dropzone.js"></script>
<script src="resources/js/dropzoneAttach.js"></script>
</head>
<body>
<div class="container">
	<form:form action="create" modelAttribute="board" method="post" id="register" style="margin:5% 10%">
		<div class="form-group">
			작성자: <input class="form-control" type="text" name="writer" 
							value="${userId!=null?userId:'guest'}" readonly>
		</div>
		<div class="form-group">
			제목: <form:input path="title" class="form-control"  name="title"/>
			<form:errors path="title" cssClass="error"/><br><br>  
		</div>
		
		<div class="form-group">
			내용: <form:textarea path="content" class="form-control" name="content"></form:textarea>
			<form:errors path="content" cssClass="error"/><br><br>  
		</div>
		<div>	
			<!-- input  class="btn btn-default" type="submit">	
			<button class="btn btn-default" id="list">List</button -->
		</div>		
	</form:form>	
	
	
	<form action="attachFiles" 
	      class="dropzone needsclick dz-clickable" style="margin:0% 10%"
	      id="dropzone" method="post" enctype="multipart/form-data">
		<div class="dz-message needsclick">
			Drop files here or click to upload.
		</div>  	      
	</form>

	<div style="margin:20px 40%" >
		<button type="button" class="btn btn-primary"
			onclick="$('#register').submit()">등록</button>		
		<button class="btn btn-default" id="list">List</button>
	</div>
</div>
	
<script>
$("#list").on("click", function(){
	$("form").attr("action", "listPage");
	$("form").attr("method", "get");
	$("form").submit();
});
</script>			
</body>
</html>

