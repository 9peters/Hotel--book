package kr.co.bithotel.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.bithotel.common.db.MyAppSqlConfig;
import kr.co.bithotel.common.util.Util;
import kr.co.bithotel.dao.InquiryMapper;
import kr.co.bithotel.vo.Inquiry;
import kr.co.bithotel.vo.Member;

public class InquiryController implements Controller {
	
	private InquiryMapper mapper;
	Inquiry inquiry = new Inquiry();
	private int no;
	private int permission;
	
	public  InquiryController(int no, int permission) {
		this.no = no;
		this.permission = permission;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(InquiryMapper.class);		
	}
	
	
	public Member service() {
		permission++;
		switch(permission) {
		case 1 :		
			//비회원일 경우의 상담문의 코딩 짜기
			System.out.println("정보를 입력하세요");
			inquiry.setName(Util.input("이름 : "));
			inquiry.setEmail(Util.input("이메일 : "));
			inquiry.setPhoneNumber(Util.input("연락처 : "));

			if(inquiry.getName().equals("") || inquiry.getEmail().equals("") ||
					inquiry.getPhoneNumber().equals("")) {
				System.out.println("등록해야할 정보를 모두 입력하세요");
			}
			
			inquiry.setTitle(Util.input("제목 :"));
			inquiry.setContent(Util.input("내용 :"));
			mapper.insertInquiryNoMember(inquiry);
			System.out.println(inquiry.getInquiryNo()+"번이 저장되었습니다.");		
		 break;	
		 
		 
		case 2:
			//회원일 경우 상담문의 코딩 짜기
			inquiry.setTitle(Util.input("제목 :"));
			inquiry.setContent(Util.input("내용 :"));
			mapper.insertInquiryMember(inquiry);
			System.out.println(inquiry.getInquiryNo()+"저장되었습니다.");	
		 break;	 
		 
		 
		 //관리자일 경우 상담문의 코딩 짜기
		case 3 :
			System.out.println("관리자 게시판");
			System.out.println("--------------");
			System.out.println("1.상담내역 조회");
			System.out.println("2.상담내역 삭제");
			int num = Integer.parseInt(Util.input("메뉴 중 처리할 항목을 선택하세요:"));
			
			List<Inquiry> list = mapper.selectInquiryList();
			
			switch(num) {
			case 1 :
				//1. 상담내역 조회(번호,제목,내용,등록일)
				System.out.println("글번호\t 제목\t 내용\t 등록일\t" );
				for(Inquiry i : list) {
					System.out.printf(i.getInquiryNo()+"\t"+i.getTitle()+"\t"+ 
									  i.getContent()+"\t"+i.getInquiryRegDate());
					System.out.println();
				} 
				break;
				
			case 2 :
				System.out.println("글번호\t 제목\t 등록일\t" );
				for(Inquiry i : list) {
					System.out.printf(i.getInquiryNo()+"\t"+i.getTitle()+"\t"+ 
							i.getInquiryRegDate()+ "\t");
					System.out.println();
				} 
				int inquiryNum = Integer.parseInt(Util.input("삭제할 상담글 번호를 입력하세요:"));
				mapper.deleteInquiry(inquiryNum);
				System.out.println(inquiryNum+"번 회원을 삭제했습니다.");		
				break;
			}
			
		 break;
		 
		}	
		
		
		return null;
	}
}