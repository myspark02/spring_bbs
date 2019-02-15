package yjc.wdb.bbs;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yjc.wdb.bbs.bean.Attachment;
import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.service.BoardService;

@Controller
@SessionAttributes("board")
public class BoardsController {

	@Inject
	private BoardService service;
	
	@RequestMapping(value="create", method=RequestMethod.GET) 
	public String getBoardForm(Model model) {
		Board board = new Board();
		model.addAttribute("board", board);		
		return "bbs/boardForm";
	}

	@RequestMapping(value="create", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute Board board, BindingResult br, 
					RedirectAttributes rttr) throws Exception{
		// 사용자 입력에 대한 정당성 검사에서 오류가 발생한 경우, 게시글 입력 폼을 결과로 반환
		if (br.hasErrors())  
			return "bbs/boardForm";
		service.create(board);
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:listPage";
	}
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public String listPage(Model model) throws Exception  {
		List<Board> list = service.listAll();
		model.addAttribute("list", list);
		return "bbs/listPage";
	}

	@RequestMapping(value="read", method=RequestMethod.GET)
	public String read(@RequestParam(value="bno", defaultValue="-1") int bno, 
						Model model) throws Exception{
		Board board = service.read(bno);
		model.addAttribute("board", board);
		System.out.println(board);
		return "bbs/read";
	}
	
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(@RequestParam(value="bno", defaultValue="-1") int bno, 
								Model model) throws Exception {
		Board board = service.read4update(bno);
		model.addAttribute("board", board);
		return "bbs/updateForm";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("board") Board board, BindingResult br) throws Exception {
		// 사용자 입력에 대한 정당성 검사에서 오류가 발생한 경우, 게시글 수정 폼을 결과로 반환
		if (br.hasErrors())
			return "bbs/updateForm";
		service.update(board);
		return "redirect:read?bno="+board.getBno();
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam(value="bno", defaultValue="-1") int bno) 
							throws Exception {
		service.delete(bno);
		return "redirect:listPage";
	}
	
	@PostMapping("attachFiles")
	@ResponseBody 
	public ResponseEntity<HashMap<String, Integer>> addAttach(MultipartFile file) throws Exception {
		ResponseEntity<HashMap<String, Integer>> entity = null;
		Attachment af = new Attachment();
		af.setFileName(file.getOriginalFilename());
		af.setBytes(file.getSize());
		af.setMime(file.getContentType());
		service.addAttach(af);
		HashMap<String, Integer> m = new HashMap<>();
		m.put("id", af.getId());
		entity = new ResponseEntity<>(m, HttpStatus.OK);
 		return entity;
	}
	
	
	
}
