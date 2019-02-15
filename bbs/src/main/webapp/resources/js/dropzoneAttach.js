/**
 * @author : scpark
 * @date : 2019.02.15
 */

Dropzone.options.dropzone = {
	success: function(file, response) {
		//alert(response);
		alert(JSON.stringify(response));
		file.upload.id = response.id;
		$("<input>", {type:'hidden', name:'attachments[]', value:response.id}).appendTo($('#register'));
	},
	error: function(file, response){
	     return false;
	}
}

