<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>jstl</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h3>사원목록</h3>
<a href="/TestWeb/empInsert">사원등록</a><br>

<form>
	<input name="departmentId" >
	<button>검색</button>
</form>

<table border =1>
	<thead><tr><th>사번</th><th>이름</th><th>급여</th><th>부서</th></tr></thead>
	<tbody>  
<c:forEach var="vo" items="${list}"> <%--주석 for() --%>
		<tr>
			<td>${vo.getEmployeeId()}</td>
			<td><a href="empUpdate?employeeId=${vo.getEmployeeId()}">  ${vo.firstName} </a> </td>
			<td>${vo.salary}</td>
			<td>${vo.departmentId}</td>
		</tr>
</c:forEach>		
	</tbody></table></body></html>