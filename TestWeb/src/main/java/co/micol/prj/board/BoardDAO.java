package co.micol.prj.board;

import java.util.ArrayList;

import co.micol.prj.comm.DAO;

public class BoardDAO extends DAO {
	
	//조회
	public ArrayList<BoardVO> selectAll(){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnect();
		
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	//추가
	
	//수정
	
	//삭제
	
}
