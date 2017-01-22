package com.JStudio.Monopoly.Player;

import java.util.LinkedList;
import java.util.List;

import com.JStudio.Monopoly.Field.Field;
import com.JStudio.Monopoly.Field.FieldRepository;

public class Player {

	private int playerNumber;
	private String username;
	private int money;
	private int position;
	private String color;
	private List<Field> userFields;

	public Player(int playerNumber, String username, String color){
		this.playerNumber = playerNumber;
		this.username = username;
		money = 1500;
		position = 0;
		this.color = color;
		userFields = new LinkedList<Field>();
	}

	public int getPlayerNumber(){
		return playerNumber;
	}

	public String getUsername(){
		return username;
	}

	public int getMoney(){
		return money;
	}

	public int getPosition(){
		return position;
	}

	public String getColor(){
		return color;
	}
	public void setMoney(int money){
		this.money = money;
	}

	public void addMoney(int moneyToAdd){
		int newMoney = this.money + moneyToAdd;
		this.money = newMoney;
	}

	public void setPosition(int position){
		this.position += position;
		if(this.position > 39)
			this.position = this.position - 40;
	}

	public List<Field> getUserFields(){
		return userFields;
	}

	public void addField(Field field){
		userFields.add(field);
	}

	public String toString(){
		return "Player nr." + playerNumber + ", position: " + position + ", money: " + money;
	}
}
