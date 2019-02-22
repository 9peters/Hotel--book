package kr.co.bithotel.controller;

import kr.co.bithotel.common.util.Util;
import kr.co.bithotel.dao.MemberSettingMapper;
import kr.co.bithotel.vo.Member;

public class MemberSettingController implements Controller {
	private int no;
	private int permission;
	MemberSettingMapper msm;
	
	public MemberSettingController(int no, int permission) {
		this.no = no;
		this.permission = permission;
		msm = Util.getSession().getMapper(MemberSettingMapper.class);
	}
	
	private String blankCheck(Member member) {
		if(member.getId().equals("")) return "아이디";
		if(member.getPassword().equals("")) return "비밀번호";
		if(member.getName().equals("")) return "이름";
		if(member.getEmail().equals("")) return "이메일";
		if(member.getPhoneNumber().equals("")) return "연락처";
		if(member.getBirthday().equals("")) return "생년월일";
		if(member.getAddress().equals("")) return "주소";
		return "";
	}
	
	private String blankCheck2(Member member) {
		if(member.getName().equals("")) return "이름";
		if(member.getEmail().equals("")) return "이메일";
		if(member.getPhoneNumber().equals("")) return "연락처";
		if(member.getBirthday().equals("")) return "생년월일";
		if(member.getAddress().equals("")) return "주소";
		return "";
	}
	
	private void join() {
		Member member = new Member();
		System.out.println("회원가입\n");
		member.setId(Util.input("아이디 : "));
		member.setPassword(Util.input("비밀번호 : "));
		String checkPassword = Util.input("비밀번호 확인 : ");
		member.setName(Util.input("이름 : "));
		member.setEmail(Util.input("이메일 : "));
		member.setPhoneNumber(Util.input("연락처 : "));
		member.setBirthday(Util.input("생년월일 : "));
		member.setAddress(Util.input("주소 : "));
		System.out.println();
		if(!member.getPassword().equals(checkPassword)) {
			System.out.println("비밀번호가 동일하지 않습니다.");
			return;
		}
		if(!blankCheck(member).equals("")) {
			System.out.println(blankCheck(member) + "가 입력되지 않았습니다.");
			return;
		}
		String isIDUK = msm.checkIDUK(member.getId());
		if(isIDUK!=null) {
			System.out.println("동일한 아이디가 존재합니다.");
			return;
		}
		int check = msm.join(member);
		if(check == 1) {
			System.out.println("회원가입이 성공적으로 되었습니다.");
			return;
		}
		if(check == 0) 
			System.out.println("회원가입을 다시 시도해주세요.");
	}
	
	private int memberInfoMenu() {
		System.out.println("----------------------------------");
		System.out.println("1. 수정");
		System.out.println("2. 탈퇴");
		System.out.println("0. 이전");
		System.out.println("----------------------------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(Util.input());
	}
	
	private void updateMember() {
		Member member = msm.getMember(no);
		System.out.println("회원정보 수정(입력하지 않을 시 수정하지 않습니다.)\n");
		System.out.println("아이디 : " + member.getId());
		System.out.println("이름 : " + member.getName());
		System.out.println("생년월일 : " + member.getBirthday());
		
		String str=null;
		boolean flag=false;
		str=Util.input("새로운 비밀번호 : ");
		if(!str.equals("")) {
			member.setPassword(str);
			flag=true;
		}
			
		
		String pw2 = Util.input("비밀번호 확인 : ");
		
		str=Util.input("이메일 : ");
		if(!str.equals(""))
			member.setEmail(str);
		
		str=Util.input("연락처 : ");
		if(!str.equals(""))
			member.setPhoneNumber(str);
		
		str=Util.input("주소 : ");
		if(!str.equals(""))
			member.setAddress(str);
		System.out.println();


		if(!(flag==false && pw2.equals(""))) {
			if((!member.getPassword().equals(pw2))  ) {
				System.out.println("비밀번호를 다시 한 번 확인해주세요.");
				return;
			} 
		}

		
		if(!blankCheck2(member).equals("")) {
			System.out.println(blankCheck2(member) + "가 입력되지 않았습니다.");
			return;
		}
		msm.updateMember(member);
		System.out.println("수정이 완료되었습니다.");
	}
	
	private Member deleteMember() {
		System.out.println("탈퇴시 사이트의 기능 사용이 제한됩니다.");
		String select = Util.input("탈퇴하시겠습니까? (Y:탈퇴) : ");
		System.out.println();
		if(!select.equals("Y")) {
			System.out.println("탈퇴처리가 취소되었습니다.");
			return null;
		}
		System.out.println("탈퇴처리 되었습니다.");
		msm.deleteMember(no);
		return new Member();
	}
	
	private Member memberInfo() {
		System.out.println("회원정보");
		Member member = msm.getMember(no);
		System.out.println("\n아이디 : " + member.getId());
		if(!Util.input("비밀번호 확인 : ").equals(member.getPassword())) {
			System.out.println("\n비밀번호를 다시 한 번 확인해주세요.");
			return null;
		}
		while(true) {
			switch(memberInfoMenu()) {
				case 1: updateMember(); return null;
				case 2: return deleteMember();
				case 0: return null;
				default: Util.invalidInput(); continue;
			}
		}
	}
	
	private void memberInfoAdmin() {
		
	}
	
	public Member service() {
		switch(permission) {
			case 0: join(); break;
			case 1: return memberInfo();
			case 2: memberInfoAdmin(); break;
		}
		return null;
	}
}