package yjc.wdb.bbs.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.*;

public class CreateBoardInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object Handler) throws Exception {
		if(request.getMethod().equals("GET")) {
			// GET 방식의 create 요청은 게시글 등록 폼 요청이므로 컨트롤러로 요청 전달 
			return true;
		}	
		HttpSession session = request.getSession();
		Object uid = session.getAttribute("userId");
		String writer = request.getParameter("writer");
		if (uid == null) {
			// 로그인 하지 않은 사용자의 요청이면 게시글 작성자가 "guest"인지 검사 
			if (null != writer && writer.equals("guest")) 
				return true; // "guest"이면 컨트롤러로 요청 전달	
		} else {
			// 로그인 한 사용자의 요청이면 게시글 작성자가 로그인 아이디와 동일한지 검사
			if (null!=writer && ((String)uid).equals(writer)) {
				return true; // 로그인 아이디와 게시글 작성자가 동일하면 컨트롤러로 요청 전달
			}
		}		
		// 그 외의 경우에는 컨트롤러에게 요청을 전달하지 않는다. 
		return false;		
	}
}
