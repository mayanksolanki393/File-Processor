<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/MainStyle.css">
</head>
<body>
	<c:if test="${!empty param.userName}">
		
		<jsp:useBean id="dao" class="cyb.excelproc.dao.impl.UserDaoImpl"></jsp:useBean>
		<c:set var="daoUser" value="${dao.getUserByUsername(param.userName)}"></c:set>
		
		<c:if test="${param.userPass == daoUser.userPass}">
			<c:set var="user" value="${daoUser}" scope="session"></c:set>
			<c:redirect url="welcome.jsp?actionId=1"></c:redirect>
		</c:if>
		
		<c:set var="msg" value="invalid username or password"></c:set>
	</c:if>
	
	<div class="center">
		<fieldset>
			<legend>Login Form</legend>
			<form action="index.jsp" method="post">
				<table class="login">
					<tr>
						<td>Username</td>
						<td><input type="text" name="userName" placeholder="username" required></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="userPass" placeholder="password" required></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input class="btn" type="submit" value="login">
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input class="btn" type="reset" value="clear">
						<td>
					</tr>
					<tr>
						<td colspan="2"><span class="msg">${msg}</span></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>