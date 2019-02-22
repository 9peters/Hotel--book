package kr.co.bithotel.controller;

import kr.co.bithotel.common.util.Util;
import kr.co.bithotel.vo.Member;

public class BitHotelController {
	private int no = 0;
	private int permission = 0;
	
	private int selectMenu() {
		System.out.println("----------------------------------");
		if(permission == 0) {
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 호텔소개");
			System.out.println("4. 객실조회");
			System.out.println("5. 객실예약");
			System.out.println("6. 상담문의");
			System.out.println("7. 호텔후기");
		} else if (permission == 1){
			System.out.println("1. 로그아웃");
			System.out.println("2. 회원정보");
			System.out.println("3. 호텔소개");
			System.out.println("4. 객실조회");
			System.out.println("5. 객실예약");
			System.out.println("6. 상담문의");
			System.out.println("7. 호텔후기");
		} else if (permission == 2) {
			System.out.println("1. 로그아웃");
			System.out.println("2. 호텔소개");
			System.out.println("3. 객실조회");
			System.out.println("4. 객실예약");
			System.out.println("5. 상담문의");
			System.out.println("6. 호텔후기");
		}
		System.out.println("0. 종료");
		System.out.println("----------------------------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(Util.input());
	}
	
	private void exit() {
		System.out.println("Bit Hotel 사이트를 종료합니다.");
		System.exit(0);
	}
	
	public void service() {
		System.out.println("----------------------------------");
		System.out.println("Bit Hotel");
		System.out.println("----------------------------------");
		while(true) {
			Controller ctrl = null;
			if(permission != 2) {
				switch(selectMenu()) {
				case 1: ctrl = new LoginController(no, permission); break;
				case 2: ctrl = new MemberSettingController(no, permission); break;
				case 3: ctrl = new HotelIntroController(no, permission); break;
				case 4: ctrl = new RoomCheckController(no, permission); break;
				case 5: ctrl = new RoomReservationController(no, permission); break;
				case 6: ctrl = new InquiryController(no, permission); break;
				case 7: ctrl = new HotelReviewController(no, permission); break;
				case 0: exit();
				default: Util.invalidInput();
			}
			}
			if(permission == 2) {
				switch(selectMenu()) {
				case 1: ctrl = new LoginController(no, permission); break;
				case 2: ctrl = new HotelIntroController(no, permission); break;
				case 3: ctrl = new RoomCheckController(no, permission); break;
				case 4: ctrl = new RoomReservationController(no, permission); break;
				case 5: ctrl = new InquiryController(no, permission); break;
				case 6: ctrl = new HotelReviewController(no, permission); break;
				case 0: exit();
				default: Util.invalidInput();
			}
			}
			if(ctrl!=null) {
				try {
					Member member = ctrl.service();
					if(member!=null) {
						no = member.getMemberNo();
						permission = member.getPermission();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}