package co.micol.prj.dept;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeptUpdate")
public class DeptUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//수정페이지 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터
		String departmentId = request.getParameter("departmentId");
		//부서번호로 단건조회
		DeptDAO deptDAO = new DeptDAO();
		request.setAttribute("dept", deptDAO.selectOne(departmentId));
		request.getRequestDispatcher("/WEB-INF/jsp/dept/deptUpdate.jsp")
			   .forward(request, response);
	}

	//DB 수정 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//파라미터를 VO 담고
		String id = request.getParameter("departmentId");
		String name = request.getParameter("departmentName");
		DeptVO vo = new DeptVO();
		vo.setDepartmentId(id);
		vo.setDepartmentName(name);
		//DB처리
		DeptDAO deptDAO = new DeptDAO();
		int cnt = deptDAO.update(vo);
		//결과출력
		response.getWriter()
				.append(cnt +"건이 등록됨");
	}

}
