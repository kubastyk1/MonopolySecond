package com.JStudio.Monopoly.Player;

public class Property {

	private int position;
	private int playerNumber;

	public Property(int position, int playerNumber) {
		super();
		this.position = position;
		this.playerNumber = playerNumber;
	}

	public Property() {
		super();
	}

	public int getPosition(){
		return position;
	}

	public int getPlayerNumber(){
		return playerNumber;
	}
}
