package yjc.wdb.bbs.dao;

import java.util.List;

import yjc.wdb.bbs.bean.Attachment;
import yjc.wdb.bbs.bean.Board;
import yjc.wdb.bbs.bean.SearchCondition;

public interface BoardDao {
	// CRUD: Create,Retrieve, Update, Delete
		public void create(Board vo) throws Exception;
		public Board read(int bno) throws Exception;
		public void update(Board vo) throws Exception;
		public void delete(int bno) throws Exception;
		public List<Board> listAll() throws Exception;
		public void incReadCnt(int bno) throws Exception;
		public void addAttach(Attachment vo) throws Exception;
		public void assoicateAttachWithBoard(int bno, Integer[] attaches)  throws Exception;
		public List<Attachment> getAttaches(int bno)  throws Exception;
		public void delAttaches(Integer[] attaches)  throws Exception;		
		public List<String> getFileNames(Integer[] attaches) throws Exception;
		public void addUserArticle(String userId, int bno) throws Exception;
		public int countArticles(int bno) throws Exception;
		public List<Board> listPage(int currentPage, int numOfRecordsPerPage) throws Exception;
		public int getTotalCount() throws Exception;
		public List<Board> searchBoard(SearchCondition search) throws Exception;
		public int getSearchTotalCount(SearchCondition search) throws Exception;
}

