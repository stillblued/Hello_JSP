package co.micol.prj.dept;

import java.util.ArrayList;

import co.micol.prj.comm.DAO;

public class DeptDAO  extends DAO{

	//전체조회
	public ArrayList<DeptVO> selectAll() {
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();
		try{
			getConnect();
			String sql = "select * from departments order by department_id" ;
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {   //다음 레코드로 이동
				DeptVO vo = new DeptVO();
				vo.setDepartmentName(rs.getString("Department_Name"));
				vo.setDepartmentId(rs.getString("Department_id"));
				vo.setManagerId(rs.getString("manager_id"));
				vo.setLocationId(rs.getString("location_id"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	//단건조회
	public DeptVO selectOne(String department_id) {
		DeptVO vo = new DeptVO();
		try{
			getConnect();
			String sql = "select * from departments where department_id=?" ;
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, department_id);
			rs = psmt.executeQuery();
			if(rs.next()) {   //다음 레코드로 이동				
				vo.setDepartmentName(rs.getString("Department_Name"));
				vo.setDepartmentId(rs.getString("Department_id"));
				vo.setManagerId(rs.getString("manager_id"));
				vo.setLocationId(rs.getString("location_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return vo;
	}

	
	//등록
	public int deptInsert(DeptVO vo) {
		int cnt = 0;
		try {
			getConnect();
			String sql = " insert into"
					   + " departments(department_id, department_name)"
					   + "     values (?,?) ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getDepartmentId());
			psmt.setString(2, vo.getDepartmentName());
			cnt = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}
	//수정
	public int update(DeptVO vo) {
		int r = 0;
				
		return r;
	}
	//삭제
	public int delete(String depaertmentId) {
		int r = 0;
		try{
			//1. connect
			getConnect();
			String sql = "delete departments "
					+ "    where department_id = ? ";
			//2 sql 구문 준비
			psmt = conn.prepareStatement(sql);
			//3. sql 구문 실행
			psmt.setString(1, depaertmentId);
			r = psmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4.연결해제
			disconnect();  
		}
		return r;
	}
}
