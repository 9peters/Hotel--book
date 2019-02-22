package kr.co.bithotel.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.bithotel.common.db.MyAppSqlConfig;
import kr.co.bithotel.common.util.Util;
import kr.co.bithotel.dao.HotelInfoMapper;
import kr.co.bithotel.vo.HotelInfo;
import kr.co.bithotel.vo.Member;


public class HotelIntroController implements Controller {
	
	HotelInfo hotelinfo = new HotelInfo();
	private HotelInfoMapper mapper;
	private int no;
	private int permission;
	
	public HotelIntroController(int no, int permission) {
		this.no = no;
		this.permission = permission;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(HotelInfoMapper.class);
	}
	
	public Member service() {
		int selectMenu;
		if(permission == 2)
			selectMenu = 2;
		else selectMenu = 1;
		List<HotelInfo> list = mapper.selectHotelInfo();
		
		switch(selectMenu) {
		case 1 :
			System.out.println("1.비트호텔 소개");
			System.out.println("2.비트호텔 비전");
			System.out.println("3.비트호텔 오시는길");
			int selectNum = Integer.parseInt(Util.input("조회할 번호를 선택하세요:"));
			
			
			switch(selectNum) {
			case 1 :
				for(HotelInfo l : list) {
					
					System.out.println(l.getInfo());
				}
				break;
			case 2 :
				for(HotelInfo l : list) {
					System.out.println(l.getVision());
				}
				break;
			case 3 :
				for(HotelInfo l : list) {
					System.out.println(l.getDirections());
				}
				break;
			default :
				System.out.println("조회하실 번호를 다시 입력하세요");
			}
			break;
			
			
		case 2 :	
			System.out.println("1.hotelInfo 조회");
			System.out.println("2.hotelInfo 등록");
			System.out.println("3.hotelInfo 수정");
			int selcetNumber = Integer.parseInt(Util.input("조회할 번호를 선택하세요:"));
			
			switch(selcetNumber) {
			case 1 :
				for(HotelInfo l : list) {
					System.out.println(l.getInfo());
				}
				break;
			case 2 :
				for(HotelInfo l : list) {
						l.setInfo(Util.input("등록할 소개 :"));
						l.setVision(Util.input("등록할 비전  : "));
						l.setDirections(Util.input("등록할 오시는 길 :"));
					}
				break;
			case 3 :
				hotelinfo.setInfo(Util.input("수정할 소개 내용:"));
				hotelinfo.setVision(Util.input("수정할 비전 내용 :"));
				hotelinfo.setDirections(Util.input("수정할 다이렉션 내용 :"));
				mapper.updateHotelInfo(hotelinfo);
				break;
			}
		
			break;
		}
		return null;
	}
}