package kr.co.bithotel.dao;

import java.util.List;

import kr.co.bithotel.vo.Member;
import kr.co.bithotel.vo.Review;


public interface ReviewMapper {
	
		
	//후기 등록(비회원)
	void insertReviewNoMember(Review review);
	
	//후기 등록(회원)
	void insertReviewMember(Review review);
	
	//회원 아이디로 리뷰 조회
	Member selectReviewById();
	
	//3개월 내 이용한 비회원 후기 조회
	List<Review> selectReviewNoMemberList(Review review);
	
	//3개월 내 이용한 회원 후기 조회
	List<Review> selectReviewMemberList(int no);
	
	//관리자 리뷰 조회
	List<Review> selectReviewList(); 
	
	//후기 삭제
	void deleteReview(int no);
		
}