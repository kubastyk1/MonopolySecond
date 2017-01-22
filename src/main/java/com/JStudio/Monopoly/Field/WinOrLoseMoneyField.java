package com.JStudio.Monopoly.Field;

import com.JStudio.Monopoly.Player.Player;

public class WinOrLoseMoneyField extends Field{

	private int moneyToPay;

	public WinOrLoseMoneyField(int fieldNumber, String fieldName, int moneyToPay){
		this.fieldNumber = fieldNumber;
		this.fieldName = fieldName;
		this.moneyToPay = moneyToPay;
	}

	@Override
	public void doMainActivities(Player player) {

		 if(player.getMoney() > moneyToPay){
		        player.addMoney(moneyToPay);
		    }
	}
}
