<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dicestyle.css" />" type="text/css">
<script src="<c:url value="/resources/JS/dice.js" />"></script>
<script src="<c:url value="/resources/JS/GameOptions.js" />"></script>
<script src="<c:url value="/resources/JS/sockjs-0.3.4.js" />"></script>
<script src="<c:url value="/resources/JS/stomp.js" />"></script>
</head>
<body>

	<div class="wrapper">
		<section class="die-container">
			<div class="die" id="d1">
				<div class="one">1</div>
				<div class="two">2</div>
				<div class="three">3</div>
				<div class="four">4</div>
				<div class="five">5</div>
				<div class="six">6</div>
			</div>
		</section>

		<section class="die-container">
			<div class="die" id="d2">
				<div class="one">1</div>
				<div class="two">2</div>
				<div class="three">3</div>
				<div class="four">4</div>
				<div class="five">5</div>
				<div class="six">6</div>
			</div>
		</section>


		<button id="btn" class="roll-btn" onclick="rollDice();">Roll Dice</button>
		<div id="options">
			<button id="changeBtn" class="buttonStyleDisable" disabled>---------</button>
			<button id="nextBtn" class="buttonStyleDisable" onclick="sendToGetNextPlayer();" disabled>Next player</button>
		</div>

		<script>
			var playerNameHTML = document.getElementById("playerName").innerHTML;
			var playerNameModel = "${playerList[0].getUsername()}";
			if(playerNameHTML.localeCompare(playerNameModel) == 0){
				enableButtons();
			}else{
				disableButtons();
			}
		</script>
<!--
		<sec:authentication var="principal" property="principal" />
		<c:if test="${playerList[0].getUsername() != principal.username}">
			<div>${principal.username}</div>
 			<script type="text/javascript">
				disableButtons();
			</script>
		</c:if>
-->
<%-- 		<c:if test="${playerList[0].getUsername() == principal.username}">
			<div>${principal.username}</div>
 			<script type="text/javascript">
				enableButtons();
			</script>
		</c:if> --%>


	</div>
</body>
</html>