package kr.co.bithotel.vo;

import java.util.Date;

public class Accommodation {
	private int amdNo;
	private int memberNo;
	private String name;
	private String password;
	private String phonenumber;
	private String email;
	private int roomNo;
	private int roomTypeNo;
	private Date checkInDate;
	private Date checkOutDate;
	private int peopleCount;
	private Date payDate;
	private char payMethod;
	private char amdStatus='N';
	
	public int getAmdNo() {return amdNo;}
	public void setAmdNo(int amdNo) {this.amdNo = amdNo;}
	public int getMemberNo() {return memberNo;}
	public void setMemberNo(int memberNo) {this.memberNo = memberNo;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getPhonenumber() {return phonenumber;}
	public void setPhonenumber(String phonenumber) {this.phonenumber = phonenumber;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public int getRoomNo() {return roomNo;}
	public void setRoomNo(int roomNo) {this.roomNo = roomNo;}
	public int getRoomTypeNo() {return roomTypeNo;}
	public void setRoomTypeNo(int roomTypeNo) {this.roomTypeNo = roomTypeNo;}
	public Date getCheckInDate() {return checkInDate;}
	public void setCheckInDate(Date checkInDate) {this.checkInDate = checkInDate;}
	public Date getCheckOutDate() {return checkOutDate;}
	public void setCheckOutDate(Date checkOutDate) {this.checkOutDate = checkOutDate;}
	public int getPeopleCount() {return peopleCount;}
	public void setPeopleCount(int peopleCount) {this.peopleCount = peopleCount;}
	public Date getPayDate() {return payDate;}
	public void setPayDate(Date payDate) {this.payDate = payDate;}
	public char getPayMethod() {return payMethod;}
	public void setPayMethod(char payMethod) {this.payMethod = payMethod;}
	public char getAmdStatus() {return amdStatus;}
	public void setAmdStatus(char amdStatus) {this.amdStatus = amdStatus;}
}