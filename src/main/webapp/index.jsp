<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Bem Vindo Ao Jsp</h1>

	<form action="/Jsp-Training/ServletLogin" method="post">
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
		<table>
			<tr>
				<td><label>Login</label></td>
				<td><input name="login" type="text"></td>
			</tr>

			<tr>
				<td><label>Senha</label></td>
				<td><input name="senha" type="password"></td>
			</tr>
			<tr>
				<td><button type="submit">Enviar</button></td>
			</tr>
		</table>

	</form>
	<h4>${msg}</h4>
</body>
</html>