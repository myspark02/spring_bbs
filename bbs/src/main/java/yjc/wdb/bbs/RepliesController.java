package yjc.wdb.bbs;

import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yjc.wdb.bbs.bean.Reply;
import yjc.wdb.bbs.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class RepliesController {
	@Inject
	private ReplyService service;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody Reply reply) 
				throws Exception {
		ResponseEntity<String> result = null;
		try {
			service.create(reply);
			result = new ResponseEntity<String>("SUCESS", HttpStatus.OK);
		}catch(Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
		return result;		
	}
	
	@RequestMapping(value="/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<Reply>> 
			getReplyList(@PathVariable("bno") int bno) throws Exception {
		ResponseEntity<List<Reply>> result = null;
		try {
			List<Reply> replies = service.list(bno);
			result = new ResponseEntity<>(replies, HttpStatus.OK);
		}catch(Exception e) {
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return result;
	}
	
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> 
			update(@PathVariable("rno") int rno, @RequestBody Reply reply) throws Exception {
		ResponseEntity<String> result = null;
		try {
			reply.setRno(rno);
			service.update(reply);
			result = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return result;
	}	
	
	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno) throws Exception {
		ResponseEntity<String> result = null;
		try {			
			service.delete(rno);
			result = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			result = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return result;
	}
}
