package com.JStudio.Monopoly.Player;

public class Dice {

	private int firstNumber;
	private int secondNumber;
	private int playerNumber;

	public Dice(int firstNumber, int secondNumber, int playerNumber) {
		super();
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.playerNumber = playerNumber;
	}

	public Dice() {
		super();
	}

	public int getFirstNumber(){
		return firstNumber;
	}

	public int getSecondNumber(){
		return secondNumber;
	}

	public int getPlayerNumber(){
		return playerNumber;
	}

	public int addNumbers(){
		return firstNumber + secondNumber;
	}

}
