<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%-- 
	<%@ %> : directive 지시어
	<% %> : scriptlet 스크립트 구문
	<%! %> : declare 선언부
	<%= %> : expression 표현식
	
	contentType : 브라우저에서 해석될 MIME TYPE 뒤의 charset은 html로 해석시의 utf-8이다. (charset 생략시 기본값 iso-8859-1)
	pageEncoding : file이 어떤 charset으로 저장될지를 선택
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%=new Date()%></h2> 
	<h3><%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%></h3>
	<h3>한글 테스트</h3>
	<%!
	//여기는 class의 구간으로 보는 것이 맞다. 전역변수는 선언 가능
		static int si = 10;
		String m() {
			return "abcd"; // 그렇기 때문에 메서드를 실행할 수 없다. 
			
		}
	%>
	<h3><%= m() %></h3>
</body>
</html>