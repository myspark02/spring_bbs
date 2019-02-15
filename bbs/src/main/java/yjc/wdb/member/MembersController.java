package yjc.wdb.member;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import yjc.wdb.member.bean.Member;
import yjc.wdb.member.service.MemberService;

@SessionAttributes({"userId", "userName"})
@Controller
public class MembersController {
	@Inject
	private MemberService service;
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public String joinForm() throws Exception {
		return "member/join";
	}	

	@RequestMapping(value="join", method=RequestMethod.POST)
	public String join(Member m) throws Exception {
		service.join(m);
		return "redirect:listPage";
	}	
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginForm() throws Exception {
		return "member/loginForm";
	}
	 
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@RequestParam(value="id") String id, 
			@RequestParam(value="password") String pw, 
			Model model) throws Exception {
		Member m = service.login(id, pw);		
		if (null != m) {
			model.addAttribute("userId", m.getId());
			model.addAttribute("userName", m.getName());
			return "redirect:listPage";
		} 
		model.addAttribute("msg", "로그인 아이디 또는 암호가 일치하지 않습니다.");
		return "member/loginForm";
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:listPage";
	}
}  


