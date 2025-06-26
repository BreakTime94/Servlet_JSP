<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<div class="container"> 
		<c:forEach items="${list}" var="i">
		<%-- <c:if test="${i.LANG_CODE_ID == 'ko'}"> --%>
			<ul class="list-group">
			  <li class="list-group-item">${i.POST_SJ }</li>
			  <li class="list-group-item">${i.POST_SN }</li>
			  <li class="list-group-item">${i.ADDRESS }</li>
			  <li class="list-group-item">${i.SUBWAY_INFO }</li>
			</ul>
		<%-- </c:if> --%>
	    </c:forEach>
	<!-- not empty fn:trim(i.ADDRESS) && not empty fn:trim(i.SUBWAY_INFO)  -->
</div>
</body>
</html>