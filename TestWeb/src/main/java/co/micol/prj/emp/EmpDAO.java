package co.micol.prj.emp;

import java.sql.ResultSet;
import java.util.ArrayList;

import co.micol.prj.comm.DAO;

public class EmpDAO extends DAO {
	
	//JOBs 전체조회
	public ArrayList<JobsVO> selectJobs() {
		ArrayList<JobsVO> list = new ArrayList<JobsVO>();
		try {
			getConnect();
			String sql = "select * from jobs order by job_id";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				JobsVO vo = new JobsVO();
				vo.setJobId(rs.getString("job_id"));
				vo.setJobTitle(rs.getString("job_title"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//단건조회
	public EmpVO selectOne(String employeeId) {
		EmpVO vo = new EmpVO();
		try {
			getConnect();
			String sql = "select * from employees where employee_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, employeeId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setEmployeeId(rs.getString("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setSalary(rs.getString("salary"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setDepartmentId(rs.getString("department_id"));
				vo.setJobId(rs.getString("job_id"));
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}
	
	
	//전체조회
	public ArrayList<EmpVO> selectAll(String departmentId){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			//1. 연결
			getConnect();
			//2.sql 구문 준비
			String sql = "select * from employees";
			if(departmentId != null && ! departmentId.isEmpty()) {
				sql += " where department_id = ? ";
			}
			sql += " order by employee_id ";
			
			psmt = conn.prepareStatement(sql);
			if(departmentId != null  && ! departmentId.isEmpty()) {
				psmt.setString(1, departmentId);
			}
			//실행
			ResultSet rs = psmt.executeQuery();
			//조회결과 list에 담기
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmployeeId(rs.getString("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setSalary(rs.getString("salary"));
				vo.setDepartmentId(rs.getString("department_id"));
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
	

	
	//등록
	public int insert(EmpVO vo) {
		int r = 0;
		try{
			//1. connect
			getConnect();
			String sql = "insert into employees(employee_id, department_id, manager_id, commission_pct, "
					+ "          salary, job_id, hire_date, phone_number, email, first_name, last_name ) "
					+ " values( (select max(employee_id)+1 from employees) ,"
					+ "   ?,?,?,?,?,?,?,?,?,?) ";
			//2 sql 구문 준비
			psmt = conn.prepareStatement(sql);
			//3. sql 구문 실행
			psmt.setString(1, vo.getDepartmentId());
			psmt.setString(2, vo.getManagerId());
			psmt.setString(3, vo.getCommissionPct());
			psmt.setString(4, vo.getSalary());
			psmt.setString(5, vo.getJobId());
			psmt.setString(6, vo.getHireDate());
			psmt.setString(7, vo.getPhoneNumber());
			psmt.setString(8, vo.getEmail());
			psmt.setString(9, vo.getFirstName());
			psmt.setString(10, vo.getLastName());
			r = psmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4.연결해제
			disconnect();  
		}	
		
		return 0;
	}
	
	//수정
	public int update(EmpVO vo) {
		int cnt =0;
		
		return cnt;
	}
	
	//삭제
	public int delete(String employeeId) {
		int cnt =0;
		try {
			getConnect();
			String sql = "delete from employees where employee_id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, employeeId);
			cnt = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}
	
}
