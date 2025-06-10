package Service;

import dao.MemberDao;
import domain.Member;

public class MemberService { //트랜잭션 지점
	private MemberDao memberDao = new MemberDao();
	// 기능
	// 회원가입 
	public void register(Member member) {
		memberDao.insert(member);
	}
	
	//회원조회
	
	public Member findBy(String id) {
		return memberDao.selectOne(id);
	}
}
