package yjc.wdb.bbs.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import yjc.wdb.bbs.bean.Reply;

@Repository
public class MariaReplyDao implements ReplyDao {
	private static final String namespace = "yjc.wdb.bbs.ReplyMapper";
	@Inject
	private SqlSession sqlSession ;
	
	@Override
	public List<Reply> list(int bno) throws Exception {
		return sqlSession.selectList(namespace+".list", bno);
	}

	@Override
	public void create(Reply reply) throws Exception {
		sqlSession.insert(namespace+".create", reply);
	}

	@Override
	public void update(Reply reply) throws Exception {
		sqlSession.update(namespace+".update", reply);
	}

	@Override
	public void delete(int rno) throws Exception {
		sqlSession.delete(namespace+".delete", rno);
	}

	@Override
	public int replyCount(int bno) throws Exception {
		return sqlSession.selectOne(namespace+".replyCount", bno);
	}

	@Override
	public Reply getReply(int rno) throws Exception {
		return sqlSession.selectOne(namespace+".getReply", rno);
	}
}
