<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
<link rel="stylesheet" type="text/css" href="css/MainStyle.css">
</head>
<body>
<div class="dialog">
	<div class="dialog-heading"><a class="btn-again" href="logout">logout</a><a class="btn-again" href="welcome.jsp?actionId=1">Another operation</a><h1 class="head">File Processor</h1></div>
	<div class="dialog-content">
	<c:forEach var="output" items="${result}">
		<div class="dialog"> 
			<div class="dialog-heading">${output.key} <sup><a href="target/${output.key}_${sessionScope.transaction.tnxId}.txt" download>export</a></sup></div>
			<div class="dialog-content back-yellow">${output.value}</div>
		</div>
	</c:forEach>
	</div>
</div>

<c:remove var="transaction" scope="session"/>
</body>
</html>