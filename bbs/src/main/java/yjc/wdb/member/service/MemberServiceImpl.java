package yjc.wdb.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.member.bean.Member;
import yjc.wdb.member.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	private MemberDao dao;
	
	@Override
	public void join(Member m) {
		dao.join(m);
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
		return dao.login(id, pw);
	}

}
