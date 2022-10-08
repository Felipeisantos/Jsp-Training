<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Menssagem de erro, entre em contato com o suporte do sistema</h1>
	<textarea rows="50" cols="50">
		<%
		out.print(request.getAttribute("msg"));
		%>
	</textarea>
</body>
</html>