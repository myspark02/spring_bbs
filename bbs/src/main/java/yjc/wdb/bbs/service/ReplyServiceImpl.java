package yjc.wdb.bbs.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import yjc.wdb.bbs.bean.Reply;
import yjc.wdb.bbs.dao.ReplyDao;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	private ReplyDao dao;
	
	@Override
	public List<Reply> list(int bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void create(Reply reply) throws Exception {
		dao.create(reply);
	}

	@Override
	public void update(Reply reply) throws Exception {
		dao.update(reply);
	}

	@Override
	public void delete(int rno) throws Exception {
		dao.delete(rno);
	}

	@Override
	public int replyCount(int bno) throws Exception {
		return dao.replyCount(bno);
	}
}
