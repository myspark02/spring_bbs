/**
 * @author : scpark
 * @date : 2019.02.15
 */

Dropzone.options.dropzone = {
	addRemoveLinks: true, 
	removedfile: function(file) { // 삭제 요청시 실행할 함수
		var name = file.upload.filename; // 업로드된 파일 이름
		var fileid = file.upload.id;     // 업로드되어 서버에서 부여한 아이디 (id 칼럽 값)
		$.ajax({ 
			type: 'POST',	 // post 방식의 요청을 서버에 전송
			url: '/bbs/deleteFile/'+fileid, // 요청 url
			data: {filename: name},  // data
			success: function (data){ // 서버에서 삭제가 성공했을 때 호출될 콜백 함수
				console.log("File has been successfully removed!!");
				alert(data + 'has been successfully removed!!');
			},
			error: function(e) { // 서버에서 삭제 오류가 발생했을 때 호출될 콜백 함수
				console.log(e);
				alert(e);
			}});		
		var fileRef;
		return (fileRef = file.previewElement) != null ? 
				fileRef.parentNode.removeChild(file.previewElement) : void 0;
	},
	success: function(file, response) {
		file.upload.id = response.id;
		$("<input>", {type:'hidden', name:'attachments[]', value:response.id}).appendTo($('#register'));
	},
	error: function(file, response){
	     return false;
	}
}

