package co.micol.prj.dept;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeptInsert")
public class DeptInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	//등륵페이지 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/dept/deptInsert.jsp")
			   .forward(request, response);
	}

	//DB 등록 처리
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
		int cnt = deptDAO.deptInsert(vo);
		//결과출력
		response.getWriter()
				.append(cnt +"건이 등록됨");
	}

}
