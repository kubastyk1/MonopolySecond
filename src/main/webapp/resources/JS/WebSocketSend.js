
function sendNumbers(dice1, dice2) {
	transparentColor(position2);
	myPlayerNumber = getPlayerNumberFromJS();
    stompClient.send("/app/hello", {}, JSON.stringify(
    		{ 'firstNumber': dice1, 'secondNumber': dice2, 'playerNumber': myPlayerNumber}));
}

function buyNormalField(){
	myPlayerNumber = getPlayerNumberFromJS();
    stompClient.send("/app/buyNormalField", {}, JSON.stringify(
    		{'position': position, 'playerNumber': myPlayerNumber}));
    setButtonAfterBuy()
}

function buyMiddleField(){
	myPlayerNumber = getPlayerNumberFromJS();
    stompClient.send("/app/buyMiddleField", {}, JSON.stringify(
    		{'position': position, 'playerNumber': myPlayerNumber}));
    setButtonAfterBuy()
}

function buyExtraField(){
	myPlayerNumber = getPlayerNumberFromJS();
    stompClient.send("/app/buyExtraField", {}, JSON.stringify(
    		{'position': position, 'playerNumber': myPlayerNumber}));
    setButtonAfterBuy()
}

function winOrLoseMoney(){
	myPlayerNumber = getPlayerNumberFromJS();
	stompClient.send("/app/winOrLoseMoney", {}, JSON.stringify(
    		{'position': position, 'playerNumber': myPlayerNumber}));
}

function goToJail(){
	myPlayerNumber = getPlayerNumberFromJS();
	stompClient.send("/app/goToJail", {}, JSON.stringify(
    		{'position': position, 'playerNumber': myPlayerNumber}));
}

function doNothing(){

}

function sendToGetNextPlayer() {

	myPlayerNumber = getPlayerNumberFromJS();
    stompClient.send("/app/nextPlayer", {}, JSON.stringify(
    		{'position': position, 'playerNumber': myPlayerNumber}));
}

/* Button actions */
function setButtonAfterBuy(){
	var changeBtn = document.getElementById("changeBtn");
    changeBtn.disabled = true;
    changeBtn.className = "buttonStyleDisable";
}

/* WebSocket functions */
/*var position = 0;
var position2 = 0;
var i = 0;

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

function showNumber(message) {

	position2 = position;
	position = JSON.parse(message.body).position;
	chooseButtons(position);
	var response = document.getElementById('added');
    var node = "Position of " + JSON.parse(message.body).username
    	+ " is: " + position + "money: " + JSON.parse(message.body).money +"$";
    changeColor(position, JSON.parse(message.body).color);
    response.innerHTML = node;
}

function showNewMoney(message){
	changeDescription(JSON.parse(message.body).playerNumber,
			JSON.parse(message.body).username, JSON.parse(message.body).money);

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
}*/