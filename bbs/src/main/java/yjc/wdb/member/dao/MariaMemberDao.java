package yjc.wdb.member.dao;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.member.bean.Member;

@Repository
public class MariaMemberDao implements MemberDao {
	private static final String namespace = "yjc.wdb.member.MemberMapper";

	@Inject
	private SqlSession sqlSession ;
	
	@Override
	public void join(Member m) {
		sqlSession.insert(namespace+".join", m);
	}

	@Override
	public void update(Member m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Member m) {
		// TODO Auto-generated method stub

	}

	@Override
	public Member login(String id, String pw) {		
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", pw);
		return sqlSession.selectOne(namespace+".login", map);
	}

}
