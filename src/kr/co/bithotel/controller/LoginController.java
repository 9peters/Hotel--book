package kr.co.bithotel.controller;

import kr.co.bithotel.common.util.Util;
import kr.co.bithotel.dao.LoginMapper;
import kr.co.bithotel.vo.Member;

public class LoginController implements Controller {
	private int no;
	private int permission;
	LoginMapper lm;
	
	public LoginController(int no, int permission) {
		this.no = no;
		this.permission = permission;
		lm = Util.getSession().getMapper(LoginMapper.class);
	}
	
	private Member login() {
		System.out.println("로그인\n");
		Member member = new Member();
		member.setId(Util.input("아이디 : "));
		member.setPassword(Util.input("비밀번호 : "));
		member = lm.getMember(member);
		System.out.println();
		if(member==null) 
			System.out.println("입력정보를 확인하세요.");
		else
			System.out.println(member.getName() +"님 환영합니다.\nBitHotel입니다.");
		return member;
	}
	
	public Member service() {
		if(permission == 0)
			return login();
		else {
			System.out.println("\n로그아웃되었습니다.\n");
			return new Member();
		}
			
	}
}