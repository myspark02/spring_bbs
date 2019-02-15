package yjc.wdb.bbs.service;

import java.util.List;

import javax.inject.Inject;

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
	public Board read(int bno) throws Exception {
		dao.incReadCnt(bno);
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
}
