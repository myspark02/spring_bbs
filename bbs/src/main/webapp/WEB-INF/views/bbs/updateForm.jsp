<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 수정 폼</title>
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
<div  style="margin: 10% 15%">
	<form:form modelAttribute="board" action="update" method="post" id="register">
		<div class="form-group">
			<label>제목:</label> 
			<form:input path="title" id="title" name="title" class="form-control" />
			<form:errors path="title" cssClass="error"/><br><br>  				
		</div>				
		
		<div class="form-group">
			<label>내용:</label> 
			<form:textarea path="content" id="content" name="content" rows="5" 
			class="form-control"></form:textarea>
			<form:errors path="content" cssClass="error"/><br><br>
		</div>	
		
		<div class="form-group">
		<label>작성자:</label> 
		<input type="text" id="writer" name="writer" class="form-control" 
				value="${board.writer}" readonly>
		<!-- span>${board.writer}</span -->
			
		</div>		
		<div class="form-group">
			<label>등록일:</label> 
			<span>${board.regdate}</span>
		</div>		
		<div class="form-group">
			<label>변경일:</label> <span>${board.moddate}</span>
		</div>		
		<div class="form-group">
			<label>조회수:</label> <span>${board.readcount}</span>
		</div>
		<!-- button type="submit" class="btn btn-warning">Modify</button>		
		<button type="button" id="listPage" onclick="back()"
			class="btn btn-primary">Cancel</button -->			
			
		<div class="form-group">
			<c:if test="${attachments!=null}">							
				<label>첨부파일 삭제:</label>
					<c:forEach var="attach" items="${attachments}" >						
							<span class="label label-danger">${attach.fileName}</span>							
							<input type="checkbox" value="${attach.id}" 
												name="del_attachments[]">&nbsp;&nbsp;
					</c:forEach>
			</c:if>	
		</div>	
		
		<input type="hidden" name="filterBy" 
			value="${search.filterBy}">
		<input type="hidden" name="searchKey" 
			value="${search.searchKey}">						
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
			onclick="$('#register').submit()">Modify</button>		
		<button class="btn btn-primary" onclick="back()">Cancel</button>
	</div>
		
</div>		
<script>
	function back() {
		history.back();
	}
</script>
</body>
</html>
