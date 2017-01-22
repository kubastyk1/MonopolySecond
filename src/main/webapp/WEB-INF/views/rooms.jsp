<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		// Set refresh, autoload time as 5 seconds
		response.setIntHeader("Refresh", 5);
	%>
	<h1>
		<c:out value="${sign}" />
	</h1>

	<c:forEach items="${playerList.getPlayerList()}" var="i">
		<h1>
			<c:out value="${i.getPlayerNumber()+1}. ${i.getUsername()}" />
		</h1>
	</c:forEach>

</body>
</html>