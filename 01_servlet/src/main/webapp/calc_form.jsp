<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="calc_result.jsp" method="post">
		<input name="calc1">
		<input name="calc2">
		<button>전송</button>
		<button formaction="CalcServ">전송</button>
	</form>
	<h3><%= request.getParameter("v") %></h3>
</body>
</html>