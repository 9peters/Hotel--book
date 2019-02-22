package kr.co.bithotel.vo;

public class RoomType {
	private int roomTypeNo;
	private String roomType;
	private int pyeong;
	private int maxPersonCount;
	private int roomCount;
	private int price;
	private String roomContent;
	
	public int getRoomTypeNo() {return roomTypeNo;}
	public void setRoomTypeNo(int roomTypeNo) {this.roomTypeNo = roomTypeNo;}
	public String getRoomType() {return roomType;}
	public void setRoomType(String roomType) {this.roomType = roomType;}
	public int getPyeong() {return pyeong;}
	public void setPyeong(int pyeong) {this.pyeong = pyeong;}
	public int getMaxPersonCount() {return maxPersonCount;}
	public void setMaxPersonCount(int maxPersonCount) {this.maxPersonCount = maxPersonCount;}
	public int getRoomCount() {return roomCount;}
	public void setRoomCount(int roomCount) {this.roomCount = roomCount;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public String getRoomContent() {return roomContent;}
	public void setRoomContent(String roomContent) {this.roomContent = roomContent;}
}