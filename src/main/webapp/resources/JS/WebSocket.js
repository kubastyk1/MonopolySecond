/* WebSocket functions */
var playerNameFromJS = "user1";
var playerNumberFromJS = 0;
var position = 0;
var position2 = 0;
var i = 0;
// positionsArray = [ new0, new1, old0, old1];
var positionsArray = [0,0,0,0];
var isStart = true;
var firstTwoValueCounter = 0;

/*
function connect() {
    var socket = new SockJS("<c:url value='/hello'/>");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        document.getElementById('added').innerHTML = '';
        stompClient.subscribe('/topic/add', showNumber);
        stompClient.subscribe('/topic/buyField', showProperty);
        stompClient.subscribe('/topic/playerMoney', showNewMoney);
    });
}
*/
function showNumber(message) {

	var playerNumber =JSON.parse(message.body).playerNumber;
	//For 2 first dice rolling
	if(isStart == true){
		positionsArray[playerNumber] = JSON.parse(message.body).position;
		firstTwoValueCounter++;
		if(firstTwoValueCounter == numberOfPlayersInGame){
			isStart = false;
		}
	}else{
		positionsArray[playerNumber+numberOfPlayersInGame] = positionsArray[playerNumber];
		positionsArray[playerNumber] = JSON.parse(message.body).position;
		transparentColor(positionsArray[playerNumber+numberOfPlayersInGame]);
	}
	position = JSON.parse(message.body).position;

	playerNameFromJS = JSON.parse(message.body).username;
	chooseButtons(JSON.parse(message.body).position);
	var response = document.getElementById('added');
    var node = "Position of " + JSON.parse(message.body).username
    	+ " is: " + JSON.parse(message.body).position +"numberOfPlayersInGame-- "+ numberOfPlayersInGame + "0-- " + positionsArray[0] +" 1-- " + positionsArray[1] +" 2-- " + positionsArray[2] +" 3-- " + positionsArray[3];
    changeColor(position, JSON.parse(message.body).color);
    response.innerHTML = node;
}

function showNewMoney(message){
	changeDescription(JSON.parse(message.body).playerNumber,
			JSON.parse(message.body).username, JSON.parse(message.body).money);

}

function doNothing(){

}

function getUsernameFromJS(){
	return playerNameFromJS;
}

function getPlayerNumberFromJS(){
	return playerNumberFromJS;
}

function showProperty(message) {

	changeColor(position, JSON.parse(message.body).color);
	var textnode = document.createTextNode(JSON.parse(message.body)
				.userFields[i].fieldName);
    var node = document.createElement("LI");
    node.appendChild(textnode);
    document.getElementById("dropdown-menu").appendChild(node);
    i++;
    changeDescription(JSON.parse(message.body).playerNumber,
			JSON.parse(message.body).username, JSON.parse(message.body).money);
}

function showNextPlayer(message){

	playerNumberFromJS = JSON.parse(message.body).playerNumber;
	var playerNameHTML = document.getElementById("playerName").innerHTML;
	var playerNameJSON = JSON.parse(message.body).username;
	if(playerNameHTML.localeCompare(playerNameJSON) == 0){
		enableButtons();
	}else{
		disableButtons();
	}

}