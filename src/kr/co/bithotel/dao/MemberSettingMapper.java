package kr.co.bithotel.dao;

import kr.co.bithotel.vo.Member;

public interface MemberSettingMapper {
	int join(Member member);
	String checkIDUK(String id);
	Member getMember(int no);
	int updateMember(Member member);
	void deleteMember(int no);
}