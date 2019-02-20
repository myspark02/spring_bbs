package yjc.wdb.bbs;

import java.io.*;
import java.util.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import yjc.wdb.bbs.bean.Pagination;
import yjc.wdb.bbs.bean.SearchCondition;
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
		@RequestParam(value="attachments[]", required=false) Integer[] attachments, 
		RedirectAttributes rttr) throws Exception{
		// 사용자 입력에 대한 정당성 검사에서 오류가 발생한 경우, 게시글 입력 폼을 결과로 반환
		if (br.hasErrors())  
			return "bbs/boardForm";
		service.create(board);
		if(attachments != null) { 
			service.assoicateAttachWithBoard(board.getBno(), attachments);
		}
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:listPage";
	}
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public String listPage(Model model, 
			@RequestParam(value="page", defaultValue="1") int currentPage, 
			@ModelAttribute("search") SearchCondition search) throws Exception  {
		Pagination p = new Pagination();
		//List<Board> list = service.listPage(currentPage, p.getNumOfArticlesPerPage());
		// Search 객체를 Pagination 객체의 프로퍼티로 추가하는 것이 더 바람직할 수 있으나, 그냥 따로 따로 구현함.
		search.setCurrentPage(currentPage);
		search.setNumOfRecordsPerPage(p.getNumOfArticlesPerPage());
		List<Board> list = service.searchBoard(search);
		
		model.addAttribute("list", list);
		// int totalCount = service.getTotalCount();
		int totalCount = service.getSearchTotalCount(search);
		p.setTotalCount(totalCount);
		p.setCurrentPage(currentPage);
		model.addAttribute("pagination", p);
		return "bbs/listPage";
	}

	@RequestMapping(value="read", method=RequestMethod.GET)
	public String read(@RequestParam(value="bno", defaultValue="-1") int bno, 
						Model model, 
						HttpServletRequest req, 
						@RequestParam(value="page", defaultValue="1") int page,
						@ModelAttribute("search") SearchCondition search) throws Exception{
		String userId = (String)req.getSession().getAttribute("userId");
		if (userId==null) userId = "guest";
		Board board = service.read(userId, bno);
		model.addAttribute("board", board);
		List<Attachment> list = service.getAttaches(bno);
		model.addAttribute("attachments", list);
		int count = service.countArticles(bno); 
		board.setReadcount(count);
		model.addAttribute("currentPage", page);
		System.out.println(board);
		return "bbs/read";
	}
	
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(@RequestParam(value="bno", defaultValue="-1") int bno, 
								Model model,
								@ModelAttribute("search") SearchCondition search) throws Exception {
		Board board = service.read4update(bno);
		model.addAttribute("board", board);
		List<Attachment> list = service.getAttaches(bno);
		if (list != null && list.size() > 0)
			model.addAttribute("attachments", list);		
		return "bbs/updateForm";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("board") Board board, BindingResult br,
			@RequestParam(value="attachments[]", required=false) Integer[] attachments, 
			@RequestParam(value="del_attachments[]", required=false) Integer[] del_attachments,
			HttpServletRequest req,
			SearchCondition search) throws Exception {
		// 사용자 입력에 대한 정당성 검사에서 오류가 발생한 경우, 게시글 수정 폼을 결과로 반환
		if (br.hasErrors())
			return "bbs/updateForm";
		service.update(board);
		if(attachments != null) { // 새로이 추가된 첨부파일의 id 배열
			service.assoicateAttachWithBoard(board.getBno(), attachments);
		}
		
		if(del_attachments != null) { // 삭제할 첨부파일들의 id 배열
			service.delAttaches(del_attachments, req);
		}
		return "redirect:read?bno=" + board.getBno() +
						"&filterBy="+search.getFilterBy() + 
						"&searchKey="+search.getSearchKey();
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam(value="bno", defaultValue="-1") int bno,
							HttpServletRequest request, 
							@RequestParam(value="page", defaultValue="1") int currentPage,
							SearchCondition search) throws Exception {
		List<Attachment> list = service.getAttaches(bno);
		if (list != null && list.size() > 0) {
			Integer[] attachments = new Integer[list.size()];
			for (int i = 0; i < list.size(); i++) {
				attachments[i] = list.get(i).getId();			
			}
			service.delAttaches(attachments, request);
		}	
		
		service.delete(bno);
		
		return "redirect:listPage?page=" + currentPage 
						+ "&filterBy=" + search.getFilterBy() 
						+ "&searchKey=" + search.getSearchKey();
	}
	
	@PostMapping("attachFiles") // @RequestMapping(value="attachFiles", method=RequestMethod.POST) 와 동일한 의미
	@ResponseBody  // View 페이지가 아닌 데이터를 클라이언트에게 반환하기 위해 사용하는 annotation
	public ResponseEntity<HashMap<String, Integer>> addAttach(MultipartFile file,
			HttpServletRequest req) throws Exception {
		ResponseEntity<HashMap<String, Integer>> entity = null;
		// ResponseEntity는 데이터와 Http 상태코드를 함께 Ajax 클라이언트에게 반환하기 위해 사용
		
		Attachment af = new Attachment();
		af.setFileName(file.getOriginalFilename());
		af.setBytes(file.getSize());
		af.setMime(file.getContentType());

		String path = req.getSession().getServletContext().getRealPath("resources/files");
		String uName = (String)req.getSession().getAttribute("userId");
		if (uName == null) path = path+"/guest";
		else path = path+"/"+uName;
		File folder = new File(path);
		if (folder.exists()==false) folder.mkdir();
		
		/* 파일이름 중복 고려 안함 */
		File dest = new File(folder, file.getOriginalFilename());
		
		HashMap<String, Integer> m = new HashMap<>();
		
		file.transferTo(dest); // 서버 임시폴더에 업로드된 파일을 지정된 곳으로 옮김
	
		System.out.println(dest.getAbsolutePath());
	
		service.addAttach(af);
		
		
		m.put("id", af.getId());
		entity = new ResponseEntity<>(m, HttpStatus.OK);
		
 		return entity;
	}
	
	@PostMapping("deleteFile/{id}") 
	@ResponseBody  // View 페이지가 아닌 데이터를 클라이언트에게 반환하기 위해 사용하는 annotation
	public ResponseEntity<Integer> deleteFile(@PathVariable int id, //URL에 포함된 id 값을 매개변수로 추출
			HttpServletRequest req) throws Exception {
		ResponseEntity<Integer> entity = null;
		// ResponseEntity는 데이터와 Http 상태코드를 함께 Ajax 클라이언트에게 반환하기 위해 사용
		
		Integer[] attachments = new Integer[1];
		attachments[0] = id;
		
		service.delAttaches(attachments, req); // 이미 구현된 서비스 메소드 이용
		
		entity = new ResponseEntity<>(id, HttpStatus.OK);
		
 		return entity;
	}
		
	
}
