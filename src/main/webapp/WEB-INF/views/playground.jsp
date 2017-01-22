<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monopoly Game</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/fieldstyle.css" />" type="text/css" >
	<script src="<c:url value="/resources/JS/WebSocketSend.js" />"></script>
	<script src="<c:url value="/resources/JS/WebSocket.js" />"></script>
	<script src="<c:url value="/resources/JS/GameOptions.js" />"></script>
	<script>
	var numberOfPlayersInGame = "${numberOfPlayersInGame}";

    window.onload = function() {
    	/* Conection */
   		var stompClient = null;
   		connect();

   		/* Roll dice button
   		var btn = document.querySelector('.roll-btn');
	    btn.addEventListener('click', rollDice, false);*/
    }

	/* WebSocket functions */
	function connect() {
	    var socket = new SockJS("<c:url value='/hello'/>");
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function(frame) {
	        console.log('Connected: ' + frame);
	        document.getElementById('added').innerHTML = '';
	        stompClient.subscribe('/topic/add', showNumber);
	        stompClient.subscribe('/topic/buyField', showProperty);
	        stompClient.subscribe('/topic/playerMoney', showNewMoney);
	        stompClient.subscribe('/topic/getNextPlayer', showNextPlayer);
	    });
	}

	</script>
</head>
<body>
	<div class="ground">
		<div id="f20" class="divCorner"> <c:out value="${fieldRepo[20]}" />
		</div><div id="f21" class="divNormal"> <c:out value="${fieldRepo[21]}" />
			<div class="divAbsolute" style="background-color: red;"></div>
		</div><div id="f22" class="divNormal"><c:out value="${fieldRepo[22]}" />
		</div><div id="f23" class="divNormal"> <c:out value="${fieldRepo[23]}" />
			<div class="divAbsolute" style="background-color: red;"></div>
		</div><div id="f24" class="divNormal"> <c:out value="${fieldRepo[24]}" />
			<div class="divAbsolute" style="background-color: red;"></div>
		</div><div id="f25" class="divNormal"><c:out value="${fieldRepo[25]}" />
		</div><div id="f26" class="divNormal"> <c:out value="${fieldRepo[26]}" />
			<div class="divAbsolute" style="background-color: yellow;"></div>
		</div><div id="f27" class="divNormal"> <c:out value="${fieldRepo[27]}" />
			<div class="divAbsolute" style="background-color: yellow;"></div>
		</div><div id="f28" class="divNormal"><c:out value="${fieldRepo[28]}" />
		</div><div id="f29" class="divNormal"> <c:out value="${fieldRepo[29]}" />
			<div class="divAbsolute" style="background-color: yellow;"></div>
		</div><div id="f30" class="divCorner"> <c:out value="${fieldRepo[30]}" />
		</div><div id="f19" class="divNormalLeft"> <c:out value="${fieldRepo[19]}" />
			<div class="divAbsolute" style="background-color: orange;"></div>
		</div><div id="f31" class="divNormalRight"> <c:out value="${fieldRepo[31]}" />
			<div class="divAbsolute" style="background-color: green;"></div>
		</div><div id="f18" class="divNormalLeft"> <c:out value="${fieldRepo[18]}" />
			<div class="divAbsolute" style="background-color: orange;"></div>
		</div><div id="f32" class="divNormalRight"> <c:out value="${fieldRepo[32]}" />
		<div class="divAbsolute" style="background-color: green;"></div>
		</div><div id="f17" class="divNormalLeft"><c:out value="${fieldRepo[17]}" />
		</div><div id="f33" class="divNormalRight"><c:out value="${fieldRepo[33]}" />
		</div><div id="f16" class="divNormalLeft"> <c:out value="${fieldRepo[16]}" />
			<div class="divAbsolute" style="background-color: orange;"></div>
		</div><div id="f34" class="divNormalRight"> <c:out value="${fieldRepo[34]}" />
			<div class="divAbsolute" style="background-color: green;"></div>
		</div><div id="f15" class="divNormalLeft"><c:out value="${fieldRepo[15]}" />
		</div><div id="f35" class="divNormalRight"><c:out value="${fieldRepo[35]}" />
		</div><div id="f14" class="divNormalLeft"> <c:out value="${fieldRepo[14]}" />
			<div class="divAbsolute" style="background-color: pink;"></div>
		</div><div id="f36" class="divNormalRight"><c:out value="${fieldRepo[36]}" />
		</div><div id="f13" class="divNormalLeft"> <c:out value="${fieldRepo[13]}" />
			<div class="divAbsolute" style="background-color: pink;"></div>
		</div><div id="f37" class="divNormalRight"> <c:out value="${fieldRepo[37]}" />
			<div class="divAbsolute" style="background-color: darkblue;"></div>
		</div><div id="f12" class="divNormalLeft"><c:out value="${fieldRepo[12]}" />
		</div><div id="f38" class="divNormalRight"><c:out value="${fieldRepo[38]}" />
		</div><div id="f11" class="divNormalLeft"> <c:out value="${fieldRepo[11]}" />
			<div class="divAbsolute" style="background-color: pink;"></div>
		</div><div id="f39" class="divNormalRight"> <c:out value="${fieldRepo[39]}" />
			<div class="divAbsolute" style="background-color: darkblue;"></div>
		</div><div id="f10" class="divCornerBottom"> <c:out value="${fieldRepo[10]}" />
		</div><div id="f9" class="divNormalBottom"> <c:out value="${fieldRepo[9]}" />
			<div class="divAbsolute" style="background-color: lightSkyBlue;"></div>
		</div><div id="f8" class="divNormalBottom"> <c:out value="${fieldRepo[8]}" />
			<div class="divAbsolute" style="background-color: lightSkyBlue;"></div>
		</div><div id="f7" class="divNormalBottom"><c:out value="${fieldRepo[7]}" />
		</div><div id="f6" class="divNormalBottom"> <c:out value="${fieldRepo[6]}" />
			<div class="divAbsolute" style="background-color: lightSkyBlue;"></div>
		</div><div id="f5" class="divNormalBottom"><c:out value="${fieldRepo[5]}" />
		</div><div id="f4" class="divNormalBottom"><c:out value="${fieldRepo[4]}" />
		</div><div id="f3" class="divNormalBottom"> <c:out value="${fieldRepo[3]}" />
			<div class="divAbsolute" style="background-color: brown;"></div>
		</div><div id="f2" class="divNormalBottom"><c:out value="${fieldRepo[2]}" />
		</div><div id="f1" class="divNormalBottom"> <c:out value="${fieldRepo[1]}" />
			<div class="divAbsolute" style="background-color: brown;"></div>
		</div><div id="f0" class="divCornerBottom"> <c:out value="${fieldRepo[0]}" />
		</div>
	</div>

	<div>.</div>
	<h1 id="playerName">${pageContext.request.userPrincipal.name}</h1>
	<jsp:include page="dice.jsp" flush="true" />
 	<jsp:include page="table.jsp" flush="true" />

	<div><c:out value="${playerList[0]}" /></div>
	<div><c:out value="${playerList[1]}" /></div>

	<div id="pacz">0</div>
	<div id="added">0</div>


</body>
</html>