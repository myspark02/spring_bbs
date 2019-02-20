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

<div class="container">
<div class="row" style="margin-top:20px;margin-bottom:20px">
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
<div class="row" style="margin-top:20px;margin-bottom:20px">
	<a href="create" class="btn btn-primary col-xs-offset-10" 
					style="margin-top:10px;margin-bottom:10px">  &nbsp; Write &nbsp; </a>
</div>

<div class="row" style="margin-top:20px;margin-bottom:20px">    
	<form action="listPage" id="searchForm">
       <div class="col-xs-8">
	    <div class="input-group">
               <div class="input-group-btn search-panel">
                   <button type="button" 
                   class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                   	<span id="search_concept">
                   		${search.filterBy!=null?search.filterBy:'Filter by'}
                   	</span> 
                   	<span class="caret"></span>
                   </button>
                   <ul class="dropdown-menu" role="menu">
                     <li><a href="#Title">Title</a></li>
                     <li><a href="#Content">Content</a></li>
                     <li><a href="#Writer">Writer</a></li>                    
                     <li class="divider"></li>
                     <li><a href="#all">Anything</a></li>
                   </ul>
               </div>
               <input type="hidden" name="filterBy" 
               			value="${search.filterBy!=null?search.filterBy:'Anything'}" id="filterBy">         
               <input type="text" class="form-control" name="searchKey" 
               				value="${search.searchKey!=null?search.searchKey:''}"
               					placeholder="Search term...">
               <span class="input-group-btn">
                   <button class="btn btn-default" id="search" type="button">
                   		<span class="glyphicon glyphicon-search"></span></button>
               </span>
           </div>
       </div>
      </form> 
</div>
<script>
$(document).ready(function(e){
    $('.search-panel .dropdown-menu').find('a').click(function(e) {
		e.preventDefault();
		var param = $(this).attr("href").replace("#","");
		var concept = $(this).text();
		$('.search-panel span#search_concept').text(concept);
		$('.input-group #filterBy').val(param);
	});
	$('#search').on('click', function(){
		$('#searchForm').submit();
	});
});
</script>	
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
				<a href="read?bno=${board.bno}&page=${pagination.currentPage}&${search.getSearchParam()}">
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

<nav aria-label="Page navigation">
  <ul class="pagination">
    <c:if test="${pagination.prevLink==true}">
	    <li class="page-item">
	      <a class="page-link" 
	      	href="listPage?page=${pagination.startPage-1}&${search.getSearchParam()}" 
	      		aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	        <span class="sr-only">Previous</span>
	      </a>
	    </li>
	</c:if>
	<c:forEach var="i" begin="${pagination.startPage}" 
				end="${pagination.endPage}" step="1">
		 <li class="page-item  ${pagination.currentPage==i?'active':''}">
		 	<a class="page-link" 
		 		href="listPage?page=${i}&${search.getSearchParam()}">${i}</a>
		 </li>		
	</c:forEach>
    <c:if test="${pagination.nextLink==true}">
	    <li class="page-item">
	      <a class="page-link" 
	      	href="listPage?page=${pagination.endPage+1}&${search.getSearchParam()}" 
	      		aria-label="Next">
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
