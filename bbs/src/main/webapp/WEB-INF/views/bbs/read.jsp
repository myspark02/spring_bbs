<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시글 상세 내용</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="resources/css/reply.css">
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

	<div class="row">
		<div class="col-sm-5">
			<h3>User Comment</h3>
		</div>
	</div><!-- /row -->
	<div class="row" style="margin-bottom:30px;" id="addField">
		<div class="col-sm-5">
			<input type="text" name="content" id="addReply" class="form-control">
		</div>	
		<div>
			<a class="btn btn-success" onclick="addReply()">댓글 등록</a>
		</div>
	</div>
	
	<div id="replies">
	</div>		
	
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

	$(document).ready(function(){
		getReplies();
	});

	function getReplies() {
		var replies="";
		var userId = "${userId!=null?userId:'guest'}";
		$.getJSON("/bbs/reply/${board.bno}", function(data){			
			$(data).each(function() {
				//alert(this.replyer);
				replies += "<div class='row' id='"+this.rno+"'>";
				replies += "<div class='col-sm-5'>";
				replies += "<div class='panel panel-default'>";
				replies += "<div class='panel-heading'>";
				replies += "<strong>"+ this.replyer+"</strong> ";
				replies += "<span class='text-muted'>"+
							(new Date(this.moddate).toLocaleString('ko-KR', { timeZone: 'UTC' }))
									+"</span></div>";
				replies += "<div class='panel-body' id='rc"+this.rno+"'>"+this.content+"</div></div></div>";
				if(this.replyer == userId) {
					replies += "<div><button class='btn btn-warning' ";
					replies += "onclick='updateReply("+this.rno+")'>수정</button> ";
					replies += "<button class='btn btn-danger' onclick='deleteReply("+this.rno+")'>";
					replies += "삭제</button></div>";
				}
				replies += "</div>";			
			});
			$("#replies").html(replies);
		});
	}

	function deleteReply(rno) {
		var ans = confirm("Are you sure to delete?");
		if(ans==false) return;
		$.ajax({
			type:'delete',
			url: '/bbs/reply/'+rno,
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override": "DELETE"},
			success: function(result) {
				$("#"+rno).hide('slow');
			}, 
			error: function(result) {
				alert("삭제할 수 없습니다[권한 확인].")
			}
		});
	}

	function addReply() {
		var content = $("#addReply").val();
		if (content=='') {
			alert('글을 입력하세요!');
			return;
		}
		$.ajax({
			type:'POST',
			url: '/bbs/reply/',
			data: JSON.stringify({"replyer":"${userId!=null?userId:'guest'}", 
				"content": content, "bno":"${board.bno}"}),
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override": "POST"},
			success: function(result) {
				getReplies();
				 $("#addReply").val("");
			},
			error: function(result) {
				alert("추가할 수 없습니다.");
			}
		});
	}

	function updateReply(rno) {		
		var orgCont = $('#rc'+rno).html();
		$('#replyUpdateModal').modal('toggle');
		$('#updateReplyContent').val(orgCont);
		$('#replyIdToBeUpdated').val(rno);
	}

	function updateCommit() {
		var replyer = "${userId!=null?userId:'guest'}";
		var rno = $('#replyIdToBeUpdated').val();
		var content = $('#updateReplyContent').val();

		$.ajax({
			type:'PUT', 
			url: '/bbs/reply/'+rno, 
			headers: {
				'Content-Type': 'application/json', 
				'X-HTTP-Method-Override': 'PUT'
				}, 
			data : JSON.stringify({'content':content, 'replyer':replyer}), 
			dataType: 'text',
			success:function(result) {
				$('#rc'+rno).html(content);
			},
			error: function(result) {
				alert("수정할 수 없습니다[권한 확인]!");
			}
		});
		$('#replyUpdateModal').modal('hide');
	}
</script>

<!-- Modal -->
<div class="modal fade" id="replyUpdateModal" tabindex="-1" role="dialog" 
					aria-labelledby="replyUpdateModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyUpdateModalLabel">댓글 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <input type="hidden" name="rno" id="replyIdToBeUpdated">
      </div>
      <div class="modal-body">
        <input type="text" class="form-control" id="updateReplyContent">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" 
         onclick="updateCommit()" id="updateCommit">Save changes</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>