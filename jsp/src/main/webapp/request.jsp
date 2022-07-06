<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request.jsp</title>
</head>
<body>
<ul>
<li> parameter : <%=request.getParameter("name") %>
<li> parameter : <%=request.getAttribute("id") %>
<li> method:  <%=request.getMethod() %>
<li> <%-- ArrayList list =(   ) request.getAttribute("arr");  --%>
<li> url   :  <%=request.getRequestURL() %>
<li> uri   :   <%=request.getRequestURI() %>
<li> 프로토콜: <%=request.getProtocol() %>
<li> query str : <%=request.getQueryString() %>
<li> ip addr : <%=request.getRemoteAddr() %>
</ul>
</body>
</html>