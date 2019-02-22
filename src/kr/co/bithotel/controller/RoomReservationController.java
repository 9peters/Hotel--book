package kr.co.bithotel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import kr.co.bithotel.common.util.Util;
import kr.co.bithotel.dao.RoomReservationMapper;
import kr.co.bithotel.vo.Accommodation;
import kr.co.bithotel.vo.EmptyRoom;
import kr.co.bithotel.vo.Member;
import kr.co.bithotel.vo.RoomType;

public class RoomReservationController implements Controller {
	private int no;
	private int permission;
	private RoomReservationMapper rrm;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	public RoomReservationController(int no, int permission) {
		this.no = no;//no;
		this.permission = permission;//permission;
		rrm = Util.getSession().getMapper(RoomReservationMapper.class);
	}

	private int selectMenu() {
		System.out.println("----------------------------------");
		System.out.println("1. 객실예약");
		System.out.println("2. 예약조회");
		System.out.println("----------------------------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(Util.input());
	}
	
	private void setMemberGuest(Accommodation amd) {
		amd.setName(Util.input("이름 : "));
		amd.setPassword(Util.input("비밀번호 : "));
		amd.setEmail(Util.input("이메일 : "));
		amd.setPhonenumber(Util.input("연락처 : "));
	}
	
	private void setMemberMember(Accommodation amd) {
		Member member = rrm.getMember(no);
		amd.setMemberNo(member.getMemberNo());
		amd.setName(member.getName());
		amd.setPhonenumber(member.getPhoneNumber());
		amd.setEmail(member.getEmail());
	}
	
	private void cashPay(int price) {
		System.out.println();
		System.out.print(new SimpleDateFormat("MM월 dd일 hh시").format(Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant())));
		System.out.println("까지 입금해주세요.");
		System.out.println("입금 계좌 : 신한 110-368-803990");
		System.out.println("입금액 : " + String.format("%,d", price) + "원");
	}
	
	private void cardPay(int price) {
		Util.input("카드번호 : ");
		Util.input("유효기간 : ");
		Util.input("CVC : ");
	}
	
	private void roomReservation() throws ParseException {
		Accommodation amd = new Accommodation();
		amd.setCheckInDate(sdf.parse(Util.input("체크인 날짜(yyyy/mm/dd) : ")));
		amd.setCheckOutDate(sdf.parse(Util.input("체크아웃 날짜(yyyy/mm/dd) : ")));
		amd.setPeopleCount(Integer.parseInt(Util.input("총원 : ")));
		List<EmptyRoom> erList = rrm.selectEmptyRoom(amd);
		System.out.println("\n번호\t   객실명\t   가격");
		int count=1;
		for(EmptyRoom er : erList) 
			System.out.printf(" %d\t%s\t  %,d\n", count++, er.getRoomType(), er.getPrice());
		count = Integer.parseInt(Util.input("예약을 진행할 객실의 번호를 선택하세요 : "));
		
		amd.setRoomNo(erList.get(--count).getRoomNo());
		amd.setRoomTypeNo(erList.get(count).getRoomTypeNo());
		int price = erList.get(count).getPrice();
		
		if(permission == 0) setMemberGuest(amd);
		else setMemberMember(amd);
		
		System.out.println("\n총액 : " + String.format("%,d", price) + "원");
		outer: while(true) {
			switch(Util.input("결제수단을 선택하세요(무통장입금 또는 카드) : ")) {
			case"무통장입금": {
				cashPay(price);
				amd.setPayMethod('M');
				rrm.updateAMD(amd);
				return;
			}
			case"카드": {
				cardPay(price);
				amd.setPayMethod('C');
				amd.setPayDate(new Date());
				break outer;
			}
			default: Util.invalidInput(); continue;
			}
		}
		amd.setMemberNo(no);
		rrm.updateAMD(amd);
		System.out.println("\n예약이 완료되었습니다.");
		System.out.println("예약번호 : " + amd.getAmdNo());
		System.out.println("예약자 : " + amd.getName());
		System.out.println("체크인 일정 : " + new SimpleDateFormat("yyyy년 MM월 dd일").format(amd.getCheckInDate()));
		System.out.println("예약객실 : " + erList.get(count).getRoomType());
	}
	
	private void checkReservation() {
		List<Accommodation> list;
		if(permission == 1)
			list = rrm.selectReservationMember(no);
		else {
			Member member = new Member();
			member.setName(Util.input("이름 : "));
			member.setPassword(Util.input("비밀번호 : "));
			member.setEmail(Util.input("이메일 : "));
			member.setPhoneNumber(Util.input("연락처 : "));
			list = rrm.selectReservationGuest(member);
		}
		
		System.out.println("\n----------------------------------");
		System.out.println("예약번호\t인원\t객실타입\t체크인\t결제여부");
		for(Accommodation amd : list) {
			System.out.printf("  %d\t\t %d    %s   %s\t%s\n", amd.getAmdNo(), amd.getPeopleCount(), rrm.getRoomType(amd.getRoomTypeNo()), new SimpleDateFormat("MM월 dd일").format(amd.getCheckInDate()), 
					amd.getPayDate() == null ? " 미완료" : "  완료");
		}
		if(list.isEmpty())
			System.out.println("예약 내역이 존재하지 않습니다.");
		System.out.println("----------------------------------");
	}
	
	private void serviceMember() throws ParseException {
		switch(selectMenu()) {
			case 1: roomReservation(); break;
			case 2: checkReservation(); break;
			default: Util.invalidInput();
		}
	}
	
	private int selectMenuAdmin() {
		System.out.println("----------------------------------");
		System.out.println("1. 금일 예약 조회");
		System.out.println("2. 특정일 예약 조회");
		System.out.println("----------------------------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(Util.input());
	}
	
	
	
	private void checkReservationAdmin() throws ParseException {
		List<Accommodation> list = null;
		switch(selectMenuAdmin()) {
		case 1: list = rrm.selectReservationAdmin(new SimpleDateFormat("yyyy/MM/dd").format(new Date())); break;
		case 2: list = rrm.selectReservationAdmin(Util.input("조회일을 입력해주세요(yyyy/mm/dd) : "));
		}
		System.out.println("예약번호\t이름\t\t연락처\t\t     이메일\t      객실번호\t   객실종류\t체크인\t체크아웃\t인원\t결제일\t결제수단\t숙박여부");
		for(Accommodation amd : list) {
			System.out.printf(
			"   %d\t       %s\t      %s\t%s\t%d\t %s\t %s\t  %s\t\t  %d\t%s\t   %s\t\t   %c\n",
			amd.getAmdNo(),
			amd.getName(),
			amd.getPhonenumber(),
			amd.getEmail(),
			amd.getRoomNo(),
			rrm.getRoomType(amd.getRoomTypeNo()),
			new SimpleDateFormat("MM/dd").format(amd.getCheckInDate()),
			new SimpleDateFormat("MM/dd").format(amd.getCheckOutDate()),
			amd.getPeopleCount(),
			amd.getPayDate()!=null ? new SimpleDateFormat("MM/dd").format(amd.getPayDate()) : "미완료",
			amd.getPayMethod(),
			amd.getAmdStatus()
			);
		}
		
		int select = Integer.parseInt(Util.input("수정할 예약번호를 입력해주세요(이전:-1) : "));
		if(select == -1) return;
		
		System.out.println("수정하고 싶은 곳의 이름과 변경할 값을 입력해주세요.");
		System.out.println("수정을 종료하고 싶으시면 엔터를 입력해주세요.");
		System.out.println("----------------------------------");
		System.out.println("예시");
		System.out.println("이름 홍길동");
		System.out.println("객실번호 401");
		System.out.println("객실종류 코리안 스위트");
		System.out.println("[엔터]");
		System.out.println("----------------------------------");
		Accommodation amd = rrm.getAccommodation(select);
		while(true) {
			String str = Util.input();
			if (str.startsWith("회원번호")) amd.setMemberNo(Integer.parseInt(str.replaceAll("회원번호 ", "")));
			else if (str.startsWith("이름")) amd.setName(str.replaceAll("이름 ", ""));
			else if (str.startsWith("비밀번호")) amd.setPassword(str.replaceAll("비밀번호 ", ""));
			else if (str.startsWith("연락처")) amd.setPhonenumber(str.replaceAll("연락처 ", ""));
			else if (str.startsWith("이메일")) amd.setEmail(str.replaceAll("이메일 ", ""));
			else if (str.startsWith("객실번호")) amd.setRoomNo(Integer.parseInt(str.replaceAll("객실번호 ", "")));
			else if (str.startsWith("객실종류번호")) amd.setRoomTypeNo(Integer.parseInt(str.replaceAll("객실종류번호 ", "")));
			else if (str.startsWith("체크인")) amd.setCheckInDate(new SimpleDateFormat("yyyy/mm/dd").parse(str.replaceAll("체크인 ", "")));
			else if (str.startsWith("체크아웃")) amd.setCheckInDate(new SimpleDateFormat("yyyy/mm/dd").parse(str.replaceAll("체크아웃 ", "")));
			else if (str.startsWith("총원")) amd.setPeopleCount(Integer.parseInt(str.replaceAll("총원 ", "")));
			else if (str.startsWith("결제일")) amd.setPayDate(new SimpleDateFormat("yyyy/mm/dd").parse(str.replaceAll("결제일 ", "")));
			else if (str.startsWith("결제수단")) amd.setPayMethod(str.replaceAll("결제수단 ", "").charAt(0));
			else if (str.startsWith("숙박상태")) amd.setAmdStatus(str.replaceAll("숙박상태 ", "").charAt(0));
			else if (str.equals("")) break;
		}
		rrm.updateAMDAdmin(amd);
	}
	
	private void serviceAdmin() throws ParseException {
		switch(selectMenu()) {
			case 1: {
				permission=0;
				roomReservation();
				break;
			}
			case 2: {
				checkReservationAdmin();
				break;
			}
			default: Util.invalidInput();
		}
	}
	
	public Member service() throws ParseException {
		if(permission == 2)
			serviceAdmin();
		else serviceMember();
		return null;
	}
}