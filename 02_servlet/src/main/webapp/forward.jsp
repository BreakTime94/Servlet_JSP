<%-- <%@page import="jdk.internal.misc.FileSystemOption"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>내 응답 이만큼 있다고</h3>
<%
	System.out.println(request.getParameter("v"));
	request.getRequestDispatcher("index.jsp").forward(request, response);
	// 클라이언트는 forward.jsp를 요청하였으나, 
	// 화면은 index.jsp를 forward 한다.

%>
</body>
</html>