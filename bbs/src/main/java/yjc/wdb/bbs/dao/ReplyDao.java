package yjc.wdb.bbs.dao;

import java.util.List;

import yjc.wdb.bbs.bean.Reply;

public interface ReplyDao {
	public List<Reply> list(int bno) throws Exception;
	public void create(Reply reply) throws Exception;
	public void update(Reply reply) throws Exception;
	public void delete(int rno) throws Exception;
	public int replyCount(int bno) throws Exception;
	public Reply getReply(int rno) throws Exception;
}
