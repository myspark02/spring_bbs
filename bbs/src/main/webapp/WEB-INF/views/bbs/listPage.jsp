<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
  <c:if test="${msg != null}">
	  <div style="background-color:orange">
	  	${msg}
	  </div>
  </c:if>
<h1>게시글 리스트</h1>
<div class="row">
<c:choose>
	<c:when test="${userName != null}">
		<div class="col-xs-offset-8"><span>${userName}</span>님 환영합니다.
			<span><a href="logout" class="btn btn-warning">Logout</a></span>
		</div>
	</c:when>
	<c:otherwise>
		<div class="col-xs-offset-8"><span>guest</span>님 환영합니다.
			<span><a href="login" class="btn btn-danger">Login</a></span>
			<span><a href="join" class="btn btn-success">Sign up</a></span>
		</div>
	</c:otherwise>
</c:choose>	
</div>
<div class="row">
	<a href="create" class="btn btn-primary col-xs-offset-10" 
					style="margin-top:10px;margin-bottom:10px">Write</a>
</div>
<table class="table table-hover">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
		<th>조회수</th>
	</tr>
	<c:forEach items="${list}" var="board">
		<tr>
			<td>${board.bno}</td>
			<td>
				<a href="read?bno=${board.bno}&page=${pagination.currentPage}">
					${board.title}
				</a>
			</td>
			<td>${board.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" 
					value="${board.regdate}" /> </td>
			<td>${board.readcount}</td>
		</tr>
	</c:forEach>
</table>	
<div class="container">
<nav aria-label="Page navigation">
  <ul class="pagination">
    <c:if test="${pagination.prevLink==true}">
	    <li class="page-item">
	      <a class="page-link" href="listPage?page=${pagination.startPage-1}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	        <span class="sr-only">Previous</span>
	      </a>
	    </li>
	</c:if>
	<c:forEach var="i" begin="${pagination.startPage}" 
				end="${pagination.endPage}" step="1">
		 <li class="page-item  ${pagination.currentPage==i?'active':''}">
		 	<a class="page-link" 
		 				href="listPage?page=${i}">${i}</a>
		 </li>		
	</c:forEach>
    <c:if test="${pagination.nextLink==true}">
	    <li class="page-item">
	      <a class="page-link" href="listPage?page=${pagination.endPage+1}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	        <span class="sr-only">Next</span>
	      </a>
	    </li>
	 </c:if>   
  </ul>
</nav>
</div>
</body>
</html>
