package kr.co.bithotel.vo;

public class Member {
	private int memberNo;
	private int permission;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
	private String birthday;
	private String address;
	
	public int getMemberNo() {return memberNo;}
	public void setMemberNo(int memberNo) {this.memberNo = memberNo;}
	public int getPermission() {return permission;}
	public void setPermission(int permission) {this.permission = permission;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	public String getBirthday() {return birthday;}
	public void setBirthday(String birthday) {this.birthday = birthday;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", permission=" + permission + ", id=" + id + ", password=" + password
				+ ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", birthday=" + birthday
				+ ", address=" + address + "]";
	}
	
}