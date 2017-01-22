package com.JStudio.Monopoly.Field;

import com.JStudio.Monopoly.Field.Field;
import com.JStudio.Monopoly.Player.Player;

public class NormalField extends Field{

	public int categoryID;
	public int value;
	public int valueToPay;
	public boolean isBought = false;
	public int ownerNumber;

	public NormalField(int number, String name, int categoryId, int value){
		this.fieldNumber = number;
		this.fieldName = name;
		this.categoryID = categoryId;
		this.value = value;
		valueToPay = value/2;
	}

	public NormalField(int number, String name, int value){
		this.fieldNumber = number;
		this.fieldName = name;
		this.value = value;
		valueToPay = value/2;
	}
//usunac to
	@Override
	public void doMainActivities(Player player) {

 /*       if(player.getMoney() > value){
        	player.addField(this);
	        player.addMoney(-value);
	    }*/
	}

	public int getCategoryID(){
		return categoryID;
	}

	public int getValue(){
		return value;
	}

	public boolean isBought() {
		return isBought;
	}

	public void setBought(boolean isBought) {
		this.isBought = isBought;
	}

	public int getOwner() {
		return ownerNumber;
	}

	public void setOwner(int ownerNumber) {
		this.ownerNumber = ownerNumber;
	}

	public int getValueToPay() {
		return valueToPay;
	}

	public void setValueToPay(int valueToPay) {
		this.valueToPay = valueToPay;
	}


}
