
var playerNumber = 0;

function changeColor(position, color) {

	var x = document.getElementById("f" + position);
	x.style.backgroundColor = color;
}

function transparentColor(position){
	var x = document.getElementById("f" + position);
	x.style.backgroundColor = "transparent";
}

function chooseButtons(position){
	//var param1 =  "<c:out value="${fieldRepo[position].getButtons()}"/>";
	//var param1 = '${fieldRepo[1].getButtons()}';

	var btn = document.getElementById('changeBtn');
	switch(position){
		/*Normal Field*/
    	case 1:	case 3:	case 6:	case 8:	case 9:
    	case 11: case 13: case 14: case 16: case 18: case 19:
    	case 21: case 23: case 24: case 26: case 27: case 29:
    	case 31: case 32: case 34: case 37: case 39:
    	case 12: case 28:
            btn.innerHTML = "Buy Field";
            btn.onclick = buyNormalField;
        	break;

    	/*Middle Field*/
    	case 5:	case 15: case 25: case 35:
    	case 12: case 28:
            btn.innerHTML = "Buy Field";
            btn.onclick = buyMiddleField;
        	break;

    	/*Extra Field*/
    	case 12: case 28:
            btn.innerHTML = "Buy Field";
            btn.onclick = buyExtraField;
        	break;

        /*Comunity Chest Field*/
    	case 2:	case 17: case 33:
    		btn.innerHTML = "You win 100$!";
            btn.onclick = winOrLoseMoney;
        	break;

        /*Pay Tax Field*/
    	case 4:	case 38:
    		btn.innerHTML = "You lose 200$!";
            btn.onclick = winOrLoseMoney;
        	break;
        /*Chance Field*/
    	case 7: case 22: case 36:
    		btn.innerHTML = "You win 200$!";
            btn.onclick = winOrLoseMoney;
        	break;

        /*Jail*/
    	case 10:
    		btn.innerHTML = "Just visiting Jail";
    		btn.onclick = doNothing;
        	break;

        /*Parking*/
    	case 20:
    		btn.innerHTML = "Parking";
    		btn.onclick = doNothing;
        	break;

        /*Go to Jail*/
    	case 30:
           	btn.innerHTML = "Go to Jail!";
            btn.onclick = goToJail;
        	break;

        /*Start*/
    	case 0:
    		btn.innerHTML = "Start! Get 200$";
            btn.onclick = doNothing;
        	break;
	}
}

function enableButtons(){

	var rollBtn = document.getElementById("btn");
    rollBtn.disabled = false;
    rollBtn.className = "buttonStyle";
    var changeBtn = document.getElementById("changeBtn");
    changeBtn.innerHTML = "   ---------   ";
    changeBtn.disabled = true;
    changeBtn.className = "buttonStyleDisable";
    var nextBtn = document.getElementById("nextBtn");
    nextBtn.disabled = true;
    nextBtn.className = "buttonStyleDisable";
}

function disableButtons(){

	var rollBtn = document.getElementById("btn");
    rollBtn.disabled = false;
    rollBtn.className = "buttonStyleDisable";
    var changeBtn = document.getElementById("changeBtn");
    changeBtn.innerHTML = "   ---------   ";
    changeBtn.disabled = true;
    changeBtn.className = "buttonStyleDisable";
    var nextBtn = document.getElementById("nextBtn");
    nextBtn.disabled = true;
    nextBtn.className = "buttonStyleDisable";
}