package yjc.wdb.bbs.dao;

import java.util.List;

import yjc.wdb.bbs.bean.Attachment;
import yjc.wdb.bbs.bean.Board;

public interface BoardDao {
	// CRUD: Create,Retrieve, Update, Delete
		public void create(Board vo) throws Exception;
		public Board read(int bno) throws Exception;
		public void update(Board vo) throws Exception;
		public void delete(int bno) throws Exception;
		public List<Board> listAll() throws Exception;
		public void incReadCnt(int bno) throws Exception;
		public void addAttach(Attachment vo) throws Exception;		
}
