package kr.co.bithotel.controller;

import java.util.List;

import kr.co.bithotel.common.util.Util;
import kr.co.bithotel.dao.RoomCheckMapper;
import kr.co.bithotel.vo.Member;
import kr.co.bithotel.vo.RoomType;

public class RoomCheckController implements Controller {
	private int no;
	private int permission;
	private RoomCheckMapper rcm;
	private List<RoomType> list;
	
	public RoomCheckController(int no, int permission) {
		this.no = no;
		this.permission =permission; //permission;
		rcm = Util.getSession().getMapper(RoomCheckMapper.class);
		list = rcm.selectRoomType();
	}
	
	private void serviceMember() {
		while(true) {
			System.out.println("----------------------------------");
			System.out.println("원하는 객실 타입을 선택하세요.");
			System.out.println("번호\t객실타입\t평수\t최대인원");
			for(RoomType roomType : list) 
				System.out.printf(" %d   %s\t %d\t    %d\n",roomType.getRoomTypeNo(), roomType.getRoomType(), roomType.getPyeong(), roomType.getMaxPersonCount());
			System.out.println("----------------------------------");
			int select = Integer.parseInt(Util.input("\n조회할 객실 타입의 번호를 입력하세요.(-1 : 이전) : "));
			if(select == -1) return;
			if(select < 1 || select > list.size()) {
				Util.invalidInput();
				continue;
			}
			select--;
			System.out.println("----------------------------------");
			System.out.println("번호 : " + list.get(select).getRoomTypeNo());
			System.out.println("객실 타입 : " + list.get(select).getRoomType().trim());
			System.out.println("평수 : " + list.get(select).getPyeong());
			System.out.println("최대인원 : " + list.get(select).getMaxPersonCount());
			System.out.println("객실소개 : " + list.get(select).getRoomContent().replace("\\n", "\n\t   "));
			System.out.println("----------------------------------");
			System.out.print("이전을 입력하면 메인메뉴로 돌아갑니다. : ");
			if(Util.input().equals("이전")) {
				System.out.println();
				return;
			}
		}
	}
	
	private void serviceAdmin() {
		while(true) {
			System.out.println("\n현재 객실 타입 리스트입니다.");
			System.out.println("번호\t객실타입\t평수\t최대인원\t객실개수\t가격");
			for(RoomType roomType : list) 
				System.out.printf(" %d   %s\t %d\t    %d\t\t   %2d\t       %,d\n",roomType.getRoomTypeNo(), roomType.getRoomType(), roomType.getPyeong(), roomType.getMaxPersonCount(),roomType.getRoomCount(),roomType.getPrice());
			int select = Integer.parseInt(Util.input("\n상세 조회를 원하는 객실 번호를 선택하세요.(-1:이전) : "));
			if(select == -1) return;
			if(select < 1 || select > list.size()) {
				Util.invalidInput();
				continue;
			}
			select--;
			List<Integer> nList = rcm.selectRoomNo(select);
			System.out.println("----------------------------------");
			System.out.println("번호 : " + list.get(select).getRoomTypeNo());
			System.out.println("객실타입 : " + list.get(select).getRoomType().trim());
			System.out.println("평수 : " + list.get(select).getPyeong());
			System.out.println("최대인원 : " + list.get(select).getMaxPersonCount());
			System.out.println("객실개수 : " + list.get(select).getRoomCount());
			System.out.println("가격 : " + String.format("%,d", list.get(select).getPrice()) + "원");
			System.out.println("객실소개 : " + list.get(select).getRoomContent().replace("\\n", "\n\t   "));
			System.out.println("객실번호 : ");
			for(int num : nList)
				System.out.println("\t   " + num);
			System.out.println("----------------------------------");
			System.out.print("수정을 원하시면 수정을 입력해주세요. 다른 키를 입력하면 이전으로 돌아갑니다. : ");
			
			if(!Util.input().equals("수정"))
				continue;
			
			System.out.println("수정하고 싶은 곳의 이름과 변경할 값을 입력해주세요.");
			System.out.println("수정을 종료하고 싶으시면 엔터를 입력해주세요.");
			System.out.println("----------------------------------");
			System.out.println("예시");
			System.out.println("객실타입 디럭스룸");
			System.out.println("객실소개 디럭스룸입니다.");
			System.out.println("[엔터]");
			System.out.println("----------------------------------");
			RoomType roomType = list.get(select);
			while(true) {
				String str = Util.input();
				if (str.startsWith("객실타입")) roomType.setRoomType(str.replaceAll("객실타입 ", ""));
				else if (str.startsWith("평수")) roomType.setPyeong(Integer.parseInt(str.replaceAll("평수 ", "")));
				else if (str.startsWith("최대인원")) roomType.setMaxPersonCount(Integer.parseInt(str.replaceAll("최대인원 ", "")));
				else if (str.startsWith("가격")) roomType.setPrice(Integer.parseInt(str.replaceAll("가격 ", "")));
				else if (str.startsWith("객실소개")) roomType.setRoomContent(str.replaceAll("객실소개 ", ""));
				else if (str.equals("")) break;
			}
			rcm.updateRoomType(roomType);
		}
	}
	
	public Member service() {
		if(permission == 2)
			serviceAdmin();
		else serviceMember();
		return null;
	}
}