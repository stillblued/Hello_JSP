package co.micol.prj;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpDAO extends DAO {

	//전체조회
	public ArrayList<EmpVO> selectAll(){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			//1. 연결
			getConnect();
			//2.sql 구문 준비
			String sql = "select * from employees order by employee_id";
			PreparedStatement stmt = conn.prepareStatement(sql);
			//실행
			ResultSet rs = stmt.executeQuery();
			//조회결과 list에 담기
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmployeeId(rs.getString("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setSalary(rs.getString("salary"));
				list.add(vo);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4. 연결해제
			disconnect();
		}
		return list;
	}
	
	//단건조회
	public EmpVO selectOne(String id) {
		EmpVO vo = new EmpVO();
		return vo;
	}
	
	//등록
	public int insert(EmpVO vo) {
		int cnt=0;
		return cnt;
	}
	
	//수정
	
	//삭제
	
}
