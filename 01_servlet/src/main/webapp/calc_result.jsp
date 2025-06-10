<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	//scriptlet
	int num1 = Integer.parseInt(request.getParameter("calc1")); 
	int num2 = Integer.parseInt(request.getParameter("calc2"));
	//아래의 <%= Expression Tag 라고 한다.	
%>
	<h3><%= num1 + num2 %></h3> 
	<h3><%= num1 - num2 %></h3>
	<h3><%= num1 * num2 %></h3>
	<h3><%= num1 / num2 %></h3>
</body>
</html>