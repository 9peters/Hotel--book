package kr.co.bithotel.vo;

import java.util.Date;


public class Inquiry {
	
	private int inquiryNo;
	private int memberNo;
	private String name;
	private String email;
	private String phoneNumber;
	private String title;
	private String content;
	private Date inquiryRegDate;
	
	
	public int getInquiryNo() {
		return inquiryNo;
	}


	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}





	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public Date getInquiryRegDate() {
		return inquiryRegDate;
	}


	public void setInquiryRegDate(Date inquiryRegDate) {
		this.inquiryRegDate = inquiryRegDate;
	}


}
