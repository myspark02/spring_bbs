package yjc.wdb.bbs.service;

import java.io.File;
import java.util.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yjc.wdb.bbs.bean.Attachment;
import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDao dao;
	
	@Override
	public void create(Board vo) throws Exception {
		dao.create(vo);
	}

	@Transactional
	@Override
	public Board read(String userId, int bno) throws Exception {
		try { 
			// 이미 해당 레코드가 삽입되어 있으면 이 레코드는 삽입되지 않고 SQL Exception이 발생한다. 
			dao.addUserArticle(userId, bno);
			dao.incReadCnt(bno); // 위의 dao.addUserArticle() 메소드에서 Exception이 발생하지 않으면 실행 
		} catch(Exception ignore) {/* 이 Exception은 무시하고 넘어간다.*/ }
		return dao.read(bno);
	}

	@Override
	public void update(Board vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}
 
	@Override
	public List<Board> listAll() throws Exception {	
		return dao.listAll();
	}

	@Override
	public Board read4update(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void addAttach(Attachment vo) throws Exception {
		dao.addAttach(vo);		
	}

	@Override
	public void assoicateAttachWithBoard(int bno, Integer[] attaches) throws Exception {
		dao.assoicateAttachWithBoard(bno, attaches);
		
	}

	@Override
	public List<Attachment> getAttaches(int bno) throws Exception {		
		return dao.getAttaches(bno);
	}

	@Override
	public void delAttaches(Integer[] attaches,  HttpServletRequest request) throws Exception {
		/* 
		 * 1. file system에서 파일 삭제
		 * 2. DB에서 파일 정보 삭제
		 */
		String path = request.getSession().getServletContext().getRealPath("resources/files");
		String uName = (String)request.getSession().getAttribute("userId");
		if (uName == null) path = path+"/guest";
		else path = path+"/"+uName;
		File folder = new File(path);		
		List<String> fnames = dao.getFileNames(attaches);
		for (int i=0; i < fnames.size(); i++) {
			File dest = new File(folder, fnames.get(i));
			dest.delete();
		}
		
		dao.delAttaches(attaches);
		
	}

	@Override
	public int countArticles(int bno) throws Exception {
		return dao.countArticles(bno);
	}

	@Override
	public List<Board> listPage(int currentPage, int numOfRecordsPerPage) throws Exception {
		return dao.listPage(currentPage, numOfRecordsPerPage);
	}

	@Override
	public int getTotalCount() throws Exception {
		return dao.getTotalCount();
	}
}
