package kr.co.bithotel.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.bithotel.common.db.MyAppSqlConfig;
import kr.co.bithotel.common.util.Util;
import kr.co.bithotel.dao.ReviewMapper;
import kr.co.bithotel.vo.Member;
import kr.co.bithotel.vo.Review;


public class HotelReviewController implements Controller {
	
	private ReviewMapper mapper;
	Review review = new Review();
	private int no;
	private int permission;	
	
	public HotelReviewController(int no, int permission) {
		this.no = no;
		this.permission = permission;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(ReviewMapper.class);		
	}
	
	
	public Member service() {
		permission++;
		switch(permission) {
		case 1 :
			//비회원일 경우 리뷰 후기 남기기	
			System.out.println("호텔 예약시 투숙객 정보를 입력하세요");
			review.setName(Util.input("성명:"));
			review.setPassword(Util.input("비밀번호:"));
			review.setPhoneNumber(Util.input("연락처:"));
			review.setEmail(Util.input("이메일:"));
			
			if(review.getName().equals("") || review.getPassword().equals("") || 
					review.getEmail().equals("") || review.getPhoneNumber().equals("")) {
				System.out.println("등록해야할 정보를 모두 입력하세요");
			}
			
			List<Review> list2 = mapper.selectReviewNoMemberList(review);
			if(list2.isEmpty()) {
				System.out.println("3개월 내 이용한 내역이 없습니다.");
			} else {
				for(Review r : list2) {

					
					
					//방번호, 방종류, 숙박일자 불러오기
						System.out.println(r.getRoomNo()+"\t"+r.getCheckInDate()+"\t"+r.getAmdNo()); 
					}	
					
					int selectRoomNo2 = Integer.parseInt(Util.input("후기를 남길 숙박 번호를 선택하세요"));
					
			
					for(Review r : list2) {						
					if(r.getAmdNo()==selectRoomNo2) {
						review.setReviewRating(Integer.parseInt(Util.input("별점:")));
						review.setReviewContent(Util.input("이용 후기:"));
						mapper.insertReviewMember(review);
						Review reviewNo = new Review();				
						list2.add(review);
						break;					
					}
					}
					review.setReviewRating(Integer.parseInt(Util.input("별점:")));
					review.setReviewContent(Util.input("이용 후기:"));
					System.out.println(review.getReviewContent());
					mapper.insertReviewNoMember(review);
					System.out.println(review.getReviewNo()+"번이 저장되었습니다.");
			}

			break;
			
			
		case 2 :
			//회원일 때 리뷰 작성
			List<Review> list = mapper.selectReviewMemberList(no);
			
			for(Review r : list) {
				if(list==null) {
					System.out.println("3개월 내 이용한 내역이 없습니다.");
				}
				
			//방번호, 방종류, 숙박일자 불러오기
				System.out.println(r.getRoomNo()+"\t"+r.getCheckInDate()+"\t"+r.getAmdNo()); 
			}	
				
			//호텔후기 조회하고 -> 작성하기
				int selectRoomNo = Integer.parseInt(Util.input("후기를 남길 숙박 번호를 선택하세요"));
				
				for(Review r : list) {						
				if(r.getAmdNo()==selectRoomNo) {
					review.setReviewRating(Integer.parseInt(Util.input("별점:")));
					review.setReviewContent(Util.input("이용 후기:"));
					mapper.insertReviewMember(review);
					Review reviewNo = new Review();				
					list.add(review);
					break;					
				}
				//번호가 다  0번으로 나옴
				System.out.println(review.getReviewNo()+"번이 저장되었습니다.");
				break;

			}
			
		
		case 3 :
			//관리자일 때 리뷰 조회
			System.out.println("---------------");
			System.out.println("1. 호텔후기 조회");
			System.out.println("2. 호텔후기 삭제");
			
			int selectMenu = Integer.parseInt(Util.input("메뉴 중 처리할 항목을 선택하세요:"));		
			List<Review> reviewlist = mapper.selectReviewList();
			
			
			switch(selectMenu) {			 
			case 1 :
				// 1.호텔 후기 조회
				System.out.println("글번호\t 별점 \t 후기\t");
				for(Review r : reviewlist) {
					System.out.printf(r.getReviewNo()+"\t"+r.getReviewRating()+"\t"
									  +r.getReviewContent());
					System.out.println();
				}
				break;
				
				
			// 2.호텔 후기 삭제
			case 2 :
				System.out.println("글번호\t 별점 \t 후기\t");
				for(Review r : reviewlist) {
					System.out.printf(r.getReviewNo()+"\t"+r.getReviewRating()+"\t"
							+r.getReviewContent());
					System.out.println();
				}
				
				int deleteNum = Integer.parseInt(Util.input("삭제할 글번호를 입력하세요"));
				
				for(Review r : reviewlist) {
					if(deleteNum==r.getReviewNo()) {
						
						mapper.deleteReview(deleteNum);
						System.out.println(deleteNum +"번 회원을 삭제했습니다.");
						break;
					}
					System.out.println("삭제할 글 번호가 없습니다.");
					return null;
				}
		// 리뷰 번호 없는데 번호 입력될 때마다 다 삭제됨
				break;
			}		
		}
		return null;
	}
}