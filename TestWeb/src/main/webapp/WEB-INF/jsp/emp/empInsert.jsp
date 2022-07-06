<%@page import="co.micol.prj.dept.DeptVO"%>
<%@page import="co.micol.prj.emp.JobsVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empInsert.jsp</title>
<script>
function validateForm(){
	if(window.document.frm.employeeId.value == ""  ) {
		alert("사번입력");
		frm.employeeId.focus();
		return false;
	}
	if(window.document.frm.lastName.value == ""  ) {
		alert("이름입력");
		frm.lastName.focus();
		return false;
	}
	if(frm.hireDate.value == ""  ) {
		alert("입사일입력");
		frm.hireDate.focus();
		return false;
	}
	if(frm.jobId.value == ""  ) {
		alert("직무입력");
		frm.jobId.focus();
		return false;
	}
	if(frm.email.value == ""  ) {
		alert("이메일입력");
		frm.email.focus();
		return false;
	}
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if(regExp.test(frm.email.value) == false){
		alert("이메일형식");
		frm.email.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

사원등록
   <form name="frm" action="empInsert" method="post" onsubmit="return validateForm()">
    <div><label>employeeId</label> <input name="employeeId"></div>
    <div><label>firstName</label> <input name="firstName"></div>
    <div><label>lastName</label> <input name="lastName"></div>
    <div><label>email</label> <input name="email"></div>
    <div><label>hireDate</label> <input name="hireDate"></div>
    <div><label>department_id</label> 
    <% ArrayList<DeptVO> depts = (ArrayList<DeptVO>)request.getAttribute("depts"); 
       for(DeptVO dept : depts){
    %>
    	<input type="radio" name="departmentId" value="<%=dept.getDepartmentId()%>">
    				<%=dept.getDepartmentName()%>
    <% } %>	
    </div>
    <div><label>jobId</label> 
    			<select name="jobId">
			<c:forEach items="jobs" var="job">
				<option value="${job.getJobId()}">${job.getJobTitle()}
			</c:forEach>	
		  </select></div>
    <div><label>manager_id</label> <select name="managerId"><option value="100">100 king</select></div>
    <button >등록</button>
    </form>
</body>
</html>