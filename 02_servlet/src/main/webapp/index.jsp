<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.red{color: red};
</style>
</head>
<body>
	<h1>나는 인덱스</h1>
	<%@ include file="title.jsp" %>
	<%=request.getParameter("v") %>
	<!-- 둘이 파일은 2개이지만 서로 request를 공유한다. 즉 같은 문서처럼 작동한다. -->
</body>
</html>