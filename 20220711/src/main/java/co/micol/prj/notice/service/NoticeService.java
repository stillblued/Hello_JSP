package co.micol.prj.notice.service;

import java.util.List;

import co.micol.prj.notice.vo.NoticeVO;

public interface NoticeService {
	
	List<NoticeVO> noticeSelectList(); //전체조회
	NoticeVO noticeSelect(NoticeVO vo); //하나조회
	int noticeInsert(NoticeVO vo); //글입력
	int noticeUpdate(NoticeVO vo); //글수정
	int noticeDelete(NoticeVO vo); //글삭제
	
	List<NoticeVO> noticeSearchList(String key, String val); //글목록에서 검색

}
