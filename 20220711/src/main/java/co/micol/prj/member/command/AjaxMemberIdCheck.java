package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.micol.prj.comm.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class AjaxMemberIdCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		MemberService memberDao = new MemberServiceImpl();
		boolean b = false;
		String id = request.getParameter("id");
		b = memberDao.isMemberIdCheck(id); //true 사용가능
		
		String result = "usable";
		
		if(!b) {
			result = "unusable";
		} 
		
		
		return "ajax:" + result;
	}

}
