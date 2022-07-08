package co.micol.prj.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.MainCommand;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private HashMap <String, Command> map = new HashMap<>();
    
    public FrontController() {
        super();  
    }

	public void init(ServletConfig config) throws ServletException {
		//초기화메소드(맵핑부분작성)
		map.put("/main.do", new MainCommand());
		map.put("/memberLoginForm.do", new MemberLoginForm());
		map.put("/memberLogin.do", new MemberLogin());
		
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서비스실행메소드
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath(); 
		String page = uri.substring(contextPath.length()); //실제 요청 페이지
		
		Command command = map.get(page); //실제 수행할 커맨드 찾음 new MainCommand();
		String viewPage = command.exec(request, response); //요청 Command 수행 결과 받음 
		
		//viewResolve
		if(!viewPage.endsWith(".do") && !viewPage.equals(null)) {
			viewPage = "WEB-INF/views/" + viewPage + ".jsp"; //시스템 접근 가능 폴더 더해주고
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response); //원하는 페이지를 호출 전달
		} else {
			response.sendRedirect(viewPage); //.do로 권한위임처리
		}
		
	}

}
