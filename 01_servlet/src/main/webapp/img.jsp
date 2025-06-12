<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="image/jpeg"
    pageEncoding="UTF-8"%>
<%
	String src ="C:\\Users\\tj\\workspaces_kch\\학습자료\\새똥이.jpg";
	//스트림을 출력형태. 먼저 inputstream으로 가져온다
	File file = new File(src);
	FileInputStream fis = new FileInputStream(file);
	byte[] bytes = fis.readAllBytes();
	
	OutputStream os = response.getOutputStream();
	
	os.write(bytes);
	
	fis.close();
	
%>