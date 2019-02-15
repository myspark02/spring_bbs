package yjc.wdb.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

public class UpdateNDeleteBoardInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object Handler) throws Exception {
		if(request.getMethod().equals("GET")) {
			// GET ����� update ��û�� �Խñ� ���� �� ��û�̸� writer �Ķ���Ͱ� 
			// ���޵��� �����Ƿ� ��Ʈ�ѷο��� ��û ����, 
			// POST ����� update ��û�� �Ʒ� �ڵ忡�� ���� ���� 
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
		FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
		outputFlashMap.put("msg", "�ش� �ۿ� ���� ����/���� ������ �����ϴ�");
		RequestContextUtils.saveOutputFlashMap("/bbs/listPage", request, response);
		response.sendRedirect("/bbs/listPage");
		return false;		
	}
}
