package co.micol.prj.member.service;

import java.util.List;
import co.micol.prj.member.vo.MemberVO;

public interface MemberService {

	
	List<MemberVO> memberSelectList(); //멤버전체조회 R
	
	MemberVO memberSelectOne(MemberVO vo); //멤버단건조회 R
	
	int memberInsert(MemberVO vo); //멤버삽입 C
	int memberUpdate(MemberVO vo); //멤버수정 U
	int memberDelete(MemberVO vo); //멤버삭제 D
	
	boolean isMemberIdCheck(String id); //id 중복체크
	
	MemberVO memberLogin(MemberVO vo); //login 처리
	
	
	
	
	
}
