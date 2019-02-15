package yjc.wdb.bbs.service;

import java.util.List;

import yjc.wdb.bbs.bean.Attachment;
import yjc.wdb.bbs.bean.Board;

public interface BoardService {
	// CRUD: Create,Retrieve, Update, Delete
	public void create(Board vo) throws Exception;
	public Board read(int bno) throws Exception;	
	public void update(Board vo) throws Exception;
	public void delete(int bno) throws Exception;
	public List<Board> listAll() throws Exception;
	public Board read4update(int bno) throws Exception;
	public void addAttach(Attachment vo) throws Exception;	
}
