<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome User</title>
<link rel="stylesheet" type="text/css" href="css/MainStyle.css">
</head>
<body>
	
	<div class="dialog">
	<div class="dialog-heading"><a class="btn-again" href="logout">logout</a><h1>File Processor</h1></div>
	<div class="dialog-content">
	<jsp:useBean id="actionDao" class="cyb.excelproc.dao.impl.ActionDaoImpl"></jsp:useBean>
	<form action="#" method="get">
		<div class="formelement">
		<label>Operation 
			<select name="actionId" onchange="this.form.submit()">
				<c:forEach var="action" items="${actionDao.getActions()}">
					<option value="${action.actionId}"
						<c:if test="${param.actionId == action.actionId}">selected</c:if>> ${action.actionName}
					</option>
				</c:forEach>
			</select>
		</label>
		</div>
	</form>
	
	<form action="ProcessData" method="post" enctype="multipart/form-data">
		<c:if test="${!empty param.actionId}">
			<input type="hidden" name="actionId" value="${param.actionId}">
			<fmt:parseNumber var="actId" integerOnly="true" type="number" value="${param.actionId}" />
			<input type="hidden" name="noif" value="${actionDao.getActionById(actId).actionNoif}">
			<c:forEach begin="1" end="${actionDao.getActionById(actId).actionNoif}" varStatus="loop">
				<div class="formelement"> <label>File ${loop.index} &nbsp;&nbsp; <input type="file" name="file${loop.index}" required></label></div>
			</c:forEach>
			
			<div class="formelement">
				<input class="formbtn" type="submit" value="submit">
			</div>
		</c:if>
	</form>

	</div>
	</div>
	</body>
	</html>
	
	