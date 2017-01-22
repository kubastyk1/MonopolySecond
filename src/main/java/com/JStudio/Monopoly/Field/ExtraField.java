package com.JStudio.Monopoly.Field;

import com.JStudio.Monopoly.Player.Player;

public class ExtraField extends NormalField{

	public ExtraField(int number, String name, int categoryId, int value){
		super(number, name, categoryId, value);
	}

	@Override
	public void doMainActivities(Player player) {

        if(player.getMoney() > value){
        	player.addField(this);
	        player.addMoney(-value);
	    }
	}

	public int getCategoryID(){
		return categoryID;
	}
}
