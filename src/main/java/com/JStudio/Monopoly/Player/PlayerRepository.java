package com.JStudio.Monopoly.Player;

import java.util.LinkedList;

public class PlayerRepository {

	private LinkedList<Player> playerList;
	
	
	public PlayerRepository(){
		playerList = new LinkedList<Player>();
	}
	
	public void addPlayer(Player player){
		playerList.add(player);
	}
	
	public LinkedList<Player> getPlayerList(){
		return playerList;
	}
	
}
