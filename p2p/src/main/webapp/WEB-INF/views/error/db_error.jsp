<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<h1>数据库辞职了...</h1>
	<%
		String message = exception.getMessage();
		//send...
		out.print("</br>message" + message);
		int code = (Integer)request.getAttribute("javax.servlet.error.status_code");
		Class clazz = (Class)request.getAttribute("javax.servlet.error.exception_type");
		out.print("</br>code:" + code);
		out.print("</br>clazz:" + clazz.getName());
		
	%>
</body>
</html>