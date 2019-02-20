<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 상세 내용</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div  style="margin: 10% 15%">
	<div>
		<div class="form-group">
			<label>제목:</label> 
			<span>${board.title}</span>
		</div>				
		
		<div class="form-group">
			<label>내용:</label> 
			<textarea id="content" name="content" rows="5" 
				class="form-control" readonly>${board.content}</textarea>
		</div>	
		
		<div class="form-group">
		<label>작성자:</label> <span>${board.writer}</span>
			
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
		<div class="form-group">
			<c:if test="${attachments!=null}">							
				<label>첨부파일:</label>
				<ul>
					<c:forEach var="attach" items="${attachments}" >
						<li>
						<a href="resources/files/${board.writer}/${attach.fileName}">${attach.fileName}</a>
						</li>
					</c:forEach>
				</ul>
			</c:if>	
		</div>					
	</div>
	<form method="post" action="delete">
		<input type="hidden" name="bno" value="${board.bno}">
		<input type="hidden" name="writer" value="${board.writer}">		
		<input type="hidden" name="page" value="${currentPage}">
		<input type="hidden" name="filterBy" value="${search.filterBy}">
		<input type="hidden" name="searchKey" value="${search.searchKey}">
	</form>
	<div>
	<c:if test="${userId==board.writer}">
		<button type="submit" class="btn btn-warning" onclick="getUpdateForm(${board.bno})">Modify</button>	
		<button type="submit" class="btn btn-danger" onclick="deleteReq()">Remove</button>
	</c:if>	
		<button type="submit" id="listPage" class="btn btn-primary" onclick="go2List()">List</button>
	</div>	
</div>		
<script>
	function getUpdateForm(bno) {
		window.location.href="update?bno="+bno+"&${search.getSearchParam()}";
	}
	function deleteReq() {
		var r = confirm('Are you sure to delete?');
		if (r==true) {
			$("form").submit();
			return;
		}
	}
	function go2List() {
		window.location.href="listPage?page=${currentPage}&${search.getSearchParam()}";
	}
</script>
</body>
</html>