package yjc.wdb.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import yjc.wdb.bbs.bean.Attachment;
import yjc.wdb.bbs.bean.Board;

public interface BoardService {
	// CRUD: Create,Retrieve, Update, Delete
	public void create(Board vo) throws Exception;
	public Board read(String userId, int bno) throws Exception;	
	public void update(Board vo) throws Exception;
	public void delete(int bno) throws Exception;
	public List<Board> listAll() throws Exception;
	public Board read4update(int bno) throws Exception;
	public void addAttach(Attachment vo) throws Exception;
	public void assoicateAttachWithBoard(int bno, Integer[] attaches) throws Exception;
	public List<Attachment> getAttaches(int bno)  throws Exception;
	public void delAttaches(Integer[] attaches, HttpServletRequest request) throws Exception;	
	public int countArticles(int bno) throws Exception;
	public List<Board> listPage(int currentPage, int numOfRecordsPerPage) throws Exception;
	public int getTotalCount() throws Exception;
}
