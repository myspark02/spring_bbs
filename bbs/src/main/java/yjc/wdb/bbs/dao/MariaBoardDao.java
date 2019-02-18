package yjc.wdb.bbs.dao;

import java.util.*;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.bbs.bean.Attachment;
import yjc.wdb.bbs.bean.Board;

@Repository
public class MariaBoardDao implements BoardDao {
	private static final String namespace = "yjc.wdb.bbs.BoardMapper";

	@Inject
	private SqlSession sqlSession ;
	
	@Override
	public void create(Board vo) throws Exception {
		sqlSession.insert(namespace+".create", vo);		
		System.out.println(vo);
	}

	@Override
	public Board read(int bno) throws Exception {
		return sqlSession.selectOne(namespace+".read", bno);
	}

	@Override
	public void update(Board vo) throws Exception {
		sqlSession.update(namespace+".update", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(namespace+".delete", bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		List<Board> list = sqlSession.selectList(namespace+".listAll");		
		return list;
	}

	@Override
	public void incReadCnt(int bno) throws Exception {
		sqlSession.update(namespace+".incReadCnt", bno);
	}

	@Override
	public void addAttach(Attachment vo) throws Exception {
		sqlSession.insert(namespace+".addAttach", vo);
		
	}

	@Override
	public void assoicateAttachWithBoard(int bno, Integer[] attaches) throws Exception {		
		
		AttachFileToBoard aftb = new AttachFileToBoard(bno, attaches);
		sqlSession.update(namespace+".associateAttachWithBoard", aftb);
	}

	@Override
	public List<Attachment> getAttaches(int bno) throws Exception {
		return sqlSession.selectList(namespace+".getAttaches", bno);
	}

	@Override
	public void delAttaches(Integer[] attaches) throws Exception {
		List<Integer> list = Arrays.asList(attaches);
		sqlSession.delete(namespace+".delAttaches", list);		
	}

	@Override
	public List<String> getFileNames(Integer[] attaches) throws Exception {
		List<Integer> list = Arrays.asList(attaches);
		return sqlSession.selectList(namespace+".getFileNames", list);
	}
}

class AttachFileToBoard {
	private int bno;
	private List<Integer> list;
	
	public AttachFileToBoard(int bno, Integer[] attaches) {		
		this.bno = bno;
		this.list = Arrays.asList(attaches);
	}
	
	public int getBno() {
		return bno;
	}
	
	public List<Integer> getList() {
		return list;
	}	
}


