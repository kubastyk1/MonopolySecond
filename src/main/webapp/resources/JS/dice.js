function getDiceSet() {
	  dice = [];
	  for(var i = 0; i < 2; i ++) {
		 var value = Math.floor(Math.random() * (7 - 1) + 1);
	     dice.push(value);
	  }

	  return dice;
}

function rollDice() {
	    var rolls = getDiceSet();
	    var even = 0;

	    sendNumbers(rolls[0], rolls[1]);

	    var rollBtn = document.getElementById("btn");
	    rollBtn.disabled = true;
	    rollBtn.className = "buttonStyleDisable";

	    var changeBtn = document.getElementById("changeBtn");
	    changeBtn.disabled = false;
	    changeBtn.className = "buttonStyle";

	    var nextBtn = document.getElementById("nextBtn");
	    nextBtn.disabled = false;
	    nextBtn.className = "buttonStyle";

	    for(var i = 1; i < 3; i ++) {
		      var die = document.querySelector('#d' + i);
		      die.className = 'die';
		      die.classList.add('show-' + rolls[even]);
			      even += 1;
		 }
}
