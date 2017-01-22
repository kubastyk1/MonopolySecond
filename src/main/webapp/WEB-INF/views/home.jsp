<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/loginstyle.css" />" type="text/css" >
</head>
<body>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden"
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>

	<!--  Jezeli nie jest zalogowany  -->
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<form name='signupForm' action='/Monopoly/signup' method='GET'>
			<input name="submit" class="buttonStyle" type="submit" value="Sign up" />
		</form>
		<form name='loginForm' action='/Monopoly/login' method='GET'>
			<input name="submit" class="buttonStyle2" type="submit" value="Log in" />
		</form>
	</c:if>

	<!--  Jezeli jest zalogowany  -->
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<form name='logoutForm' action="javascript:formSubmit()" method='GET'>
			<input name="submit" class="buttonStyleRed" type="submit" value="Log out" />
		</form>
	</c:if>

	<h1> Monopoly Game</h1>

	<div class="background">
		<div class="text">Room 1</div>
		<div class="text2">${sizeInRoom}/2</div>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/login' method='GET'>
				<input name="submit" class="buttonStyleRoomRed" type="submit" value="Log in to play" />
			</form>
		</c:if>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/room/1/2' method='GET'>
				<input name="submit" class="buttonStyleRoomGreen" type="submit" value="Join game!" />
			</form>
		</c:if>
	</div>

	<div class="background">
		<div class="text">Room 2</div>
		<div class="text2">2 players</div>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/login'  method='GET'>
				<input name="submit" class="buttonStyleRoomRed" type="submit" value="Log in to play" />
			</form>
		</c:if>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/room/2/2' method='GET'>
				<input name="submit" class="buttonStyleRoomGreen" type="submit" value="Join game!" />
			</form>
		</c:if>
	</div>

	<div class="background">
		<div class="text">Room 3</div>
		<div class="text2">3 players</div>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/login' method='GET'>
				<input name="submit" class="buttonStyleRoomRed" type="submit" value="Log in to play" />
			</form>
		</c:if>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/room/3/3' method='GET'>
				<input name="submit" class="buttonStyleRoomGreen" type="submit" value="Join game!" />
			</form>
		</c:if>
	</div>

	<div class="background">
		<div class="text">Room 4</div>
		<div class="text2">4 players</div>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/login' method='GET'>
				<input name="submit" class="buttonStyleRoomRed" type="submit" value="Log in to play" />
			</form>
		</c:if>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/room/4/4' method='GET'>
				<input name="submit" class="buttonStyleRoomGreen" type="submit" value="Join game!" />
			</form>
		</c:if>
	</div>

	<div class="background">
		<div class="text">Room 5</div>
		<div class="text2">4 players</div>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/login' method='GET'>
				<input name="submit" class="buttonStyleRoomRed" type="submit" value="Log in to play" />
			</form>
		</c:if>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form name='addForm' class="buttonForm" action='/Monopoly/room/5/4' method='GET'>
				<input name="submit" class="buttonStyleRoomGreen" type="submit" value="Join game!" />
			</form>
		</c:if>
	</div>
</body>
</html>
