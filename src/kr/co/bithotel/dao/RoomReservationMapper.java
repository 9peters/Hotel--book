package kr.co.bithotel.dao;

import java.util.List;

import kr.co.bithotel.vo.Accommodation;
import kr.co.bithotel.vo.EmptyRoom;
import kr.co.bithotel.vo.Member;

public interface RoomReservationMapper {
	List<EmptyRoom> selectEmptyRoom(Accommodation amd);
	Member getMember(int no);
	void updateAMD(Accommodation amd);
	List<Accommodation> selectReservationMember(int no);
	List<Accommodation> selectReservationGuest(Member member);
	List<Accommodation> selectReservationAdmin(String date);
	String getRoomType(int no);
	Accommodation getAccommodation(int no);
	void updateAMDAdmin(Accommodation amd);
}