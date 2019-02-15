package yjc.wdb.member.dao;

import yjc.wdb.member.bean.Member;

public interface MemberDao {
	public void join(Member m);
	public void update(Member m);
	public void delete(Member m);
	public Member login(String id, String pw);
}

