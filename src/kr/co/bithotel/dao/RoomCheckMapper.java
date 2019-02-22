package kr.co.bithotel.dao;

import java.util.List;
import kr.co.bithotel.vo.RoomType;

public interface RoomCheckMapper {
	List<RoomType> selectRoomType();
	List<Integer> selectRoomNo(int type);
	int updateRoomType(RoomType roomType);
}