package kr.co.bithotel.dao;

import java.util.List;
import kr.co.bithotel.vo.Inquiry;

public interface InquiryMapper {
	
	
	//비회원 문의 입력
	void insertInquiryNoMember(Inquiry inquiry);
	
	//회원 문의 입력
	void insertInquiryMember(Inquiry inquiry);
	
	
	//관리자 상담문의 전체 조회
	List<Inquiry> selectInquiryList();
			
	//관리자 상담문의 삭제
	void deleteInquiry(int no);
	
	
}