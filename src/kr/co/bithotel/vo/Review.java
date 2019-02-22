package kr.co.bithotel.vo;

import java.util.Date;

public class Review {
	private int reviewNo;
	private int memberNo;
	private String name;
	private String password;
	private String phoneNumber;
	private String email;
	private int reviewRating;
	private int amdNo;
	private int roomNo;
	private int roomTypeNo;
	private Date checkInDate;
	private String reviewContent;
	private Date reviewRegDate;
	private Member member;
	
		
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}
	public int getAmdNo() {
		return amdNo;
	}
	public void setAmdNo(int amdNo) {
		this.amdNo = amdNo;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getRoomTypeNo() {
		return roomTypeNo;
	}
	public void setRoomTypeNo(int roomTypeNo) {
		this.roomTypeNo = roomTypeNo;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Date getReviewRegDate() {
		return reviewRegDate;
	}
	public void setReviewRegDate(Date reviewRegDate) {
		this.reviewRegDate = reviewRegDate;
	}
	
	
	
}
