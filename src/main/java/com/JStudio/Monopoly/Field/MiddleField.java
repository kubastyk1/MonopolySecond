package com.JStudio.Monopoly.Field;

import com.JStudio.Monopoly.Player.Player;

public class MiddleField extends NormalField{

	public MiddleField(int number, String name, int value){
		super(number, name, value);
	}

	@Override
	public void doMainActivities(Player player) {

        if(player.getMoney() > value){
        	player.addField(this);
	        player.addMoney(-value);
	    }
	}

	public int getValue(){
		return value;
	}

}
