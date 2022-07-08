package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.comm.Command;

public class MemberLogout implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		request.setAttribute("message", name + "님 정상적으로 로그아웃 되었습니다.");
		
		session.invalidate(); //세션 삭제
		
		
		return "member/memberLogout";
	
	}

}
