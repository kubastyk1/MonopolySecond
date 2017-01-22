package com.JStudio.Monopoly.Field;

import com.JStudio.Monopoly.Player.Player;

public abstract class Field {

	public int fieldNumber;
	public String fieldName;
	public String buttons;

	abstract void doMainActivities(Player player);

	public String getFieldName(){
		return fieldName;
	}
	public int getFieldNumber(){
		return fieldNumber;
	}

	public String toString(){
		return "Nr. " + fieldNumber + "  ---  "+ fieldName;
	}
}
