<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/tablestyle.css" />" type="text/css" >
<script type="text/javascript">
	function changeDescription(playerNumber, username, money){
		document.getElementById("a"+playerNumber).innerHTML = username + "   " + money +"$";
	}
</script>
</head>
<body>

<div class="container">
  <ul>
	<c:forEach items="${playerList}" var="i">
		<li class="dropdown">
      		<input id="toggle3" type="checkbox" />
      		<a id="a${i.getPlayerNumber()}" href="#" class="dropdown2">
      		<c:out value="${i.getUsername()}  ${i.getMoney()}$" /></a>
      	  	<ul id="dropdown-menu" class="dropdown-menu">

      	  	<!-- 	<c:forEach items="${i.getUserFields()}" var="j">
      	  			<li>HOME</li>
		       		<li><c:out value="${j.getFieldName()}" /></li>
		        </c:forEach>-->
	      	</ul>
    	</li>
	</c:forEach>
 </ul>
</div>

</body>
</html>