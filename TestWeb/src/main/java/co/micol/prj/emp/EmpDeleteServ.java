package co.micol.prj.emp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpDeleteServ
 */
@WebServlet("/empDelete")
public class EmpDeleteServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터
		String employeeId = request.getParameter("employeeId");
		//ㅇ매 -> delete 메서드
		EmpDAO dao = new EmpDAO();
		int cnt = dao.delete(employeeId);
		
		response.getWriter()
				.append("<script>")
				.append("alert('"+ cnt +" 건 이 삭제완료')")
				.append("location.href='empList';")
			    .append("</script>");
		//request.setAttribute("msg", cnt + "이 삭제됨");
		//request.getRequestDispatcher("/WEB-INF/jsp/message.jsp")
		//       .forward(request, response);
	}


}
