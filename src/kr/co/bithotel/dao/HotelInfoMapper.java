package kr.co.bithotel.dao;

import java.util.List;

import kr.co.bithotel.vo.HotelInfo;

public interface HotelInfoMapper {
		
	//관리자 호텔 인포 등록 
	void insertHotelInfo(HotelInfo hotelinfo);
	
	//관리자 호텔 인포 수정
	void updateHotelInfo(HotelInfo hotelinfo);
	
	//관리자, 회원 호텔 소개 조회
	List<HotelInfo> selectHotelInfo();
	
	//회원 호텔 비전 조회
	String selectHotelVision();
	
	//회원 -> 호텔 오시는 길 조회
	String selectHotelDirections();
} 