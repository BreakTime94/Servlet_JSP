<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<input name="kor">
		<input name="eng">
		<input name="mat">
		<button>계산</button>
	</form>
	<%
	String k = request.getParameter("kor");
	String e = request.getParameter("eng");
	String m = request.getParameter("mat");
	if(k != null && e != null && m != null) {		
		int kor = Integer.parseInt(k);
		int eng = Integer.parseInt(e);
		int mat = Integer.parseInt(m);
		int total = kor + eng + mat;
		double avg = total / 3.0 ;
	
	%>
	
	<h3><%= total %></h3>
	<h3><%= avg %></h3>
	<% } %>
</body>
</html>