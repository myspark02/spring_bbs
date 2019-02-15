package yjc.wdb.bbs.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.*;

public class CreateBoardInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object Handler) throws Exception {
		if(request.getMethod().equals("GET")) {
			// GET ����� create ��û�� �Խñ� ��� �� ��û�̹Ƿ� ��Ʈ�ѷ��� ��û ���� 
			return true;
		}	
		HttpSession session = request.getSession();
		Object uid = session.getAttribute("userId");
		String writer = request.getParameter("writer");
		if (uid == null) {
			// �α��� ���� ���� ������� ��û�̸� �Խñ� �ۼ��ڰ� "guest"���� �˻� 
			if (null != writer && writer.equals("guest")) 
				return true; // "guest"�̸� ��Ʈ�ѷ��� ��û ����	
		} else {
			// �α��� �� ������� ��û�̸� �Խñ� �ۼ��ڰ� �α��� ���̵�� �������� �˻�
			if (null!=writer && ((String)uid).equals(writer)) {
				return true; // �α��� ���̵�� �Խñ� �ۼ��ڰ� �����ϸ� ��Ʈ�ѷ��� ��û ����
			}
		}		
		// �� ���� ��쿡�� ��Ʈ�ѷ����� ��û�� �������� �ʴ´�. 
		return false;		
	}
}
