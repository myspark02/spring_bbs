package yjc.wdb.bbs.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import yjc.wdb.bbs.bean.Reply;
import yjc.wdb.bbs.dao.ReplyDao;

public class UpdateNDeleteReplyInterceptor extends HandlerInterceptorAdapter {
	@Inject
	private ReplyDao dao;
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object Handler) throws Exception {
		String method=request.getMethod();
		if(method.equals("PUT") || method.equals("PATCH") || method.equals("DELETE")) {
			HttpSession session = request.getSession();
			String uid = (String)session.getAttribute("userId");
			if (uid == null) uid = "guest";
			String uri = request.getRequestURI();
			String rno = "-1";
			if(uri.lastIndexOf("/") != -1)
				rno = uri.substring(uri.lastIndexOf("/")+1);
			try {
				int rnum = Integer.parseInt(rno);
				Reply reply = dao.getReply(rnum);
				if(reply != null && reply.getReplyer().equalsIgnoreCase(uid)) 
					return true;
			}catch(Exception e) {}
			// 그 외의 경우에는 컨트롤러에게 요청을 전달하지 않는다.
			throw new Exception("권한이 없습니다.");			
		}	
		return true;
	}
}
