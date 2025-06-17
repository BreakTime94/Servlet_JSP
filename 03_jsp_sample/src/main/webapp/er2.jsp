<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="er3.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int v1 = Integer.parseInt(request.getParameter("v1"));
		int v2 = Integer.parseInt(request.getParameter("v2"));
		int result = v1 / v2;
		//NumberFormat --> null을 parseInt하면 저렇게 뜸
		//Arithmetic --> 0으로 나눴을 때 
	%>
	<h3>결과는 <%= result %></h3>
</body>
</html>