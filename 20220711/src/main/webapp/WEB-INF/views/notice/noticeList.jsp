<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div>게시글 목록</div>
		<div>
			<table border="1">
				<thead>
					<tr>
						<th width="70">글번호</th>
						<th width="130">작성자</th>
						<th width="200">제목</th>
						<th width="130">작성일</th>
						<th width="70">조회수</th>
						<th width="180">첨부파일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">
							<c:forEach items="${list }" var="list">
								<tr>
								<td>${list.noticeId }</td>
								<td>${list.noticeWriter }</td>
								<td>${list.noticeTitle }</td>
								<td>${list.noticeDate }</td>
								<td>${list.noticeHit }</td>
								<td>${list.noticeAttach }</td>
								</tr>
							</c:forEach>	
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">
									게시글이 존재하지 않습니다.
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div><br>
		<div>
			<button type="button" onclick="location.href='noticeForm.do'">등록</button>
		</div>
	</div>
</body>
</html>