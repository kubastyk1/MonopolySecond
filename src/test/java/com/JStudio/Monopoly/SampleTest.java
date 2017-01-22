package com.JStudio.Monopoly;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.JStudio.Monopoly.Field.Field;
import com.JStudio.Monopoly.Field.FieldRepository;
import com.JStudio.Monopoly.Field.NormalField;
import com.JStudio.Monopoly.Player.Dice;
import com.JStudio.Monopoly.Player.Player;
import com.JStudio.Monopoly.Player.PlayerRepository;
import com.JStudio.Monopoly.Player.Property;

import junit.framework.Assert;

public class SampleTest {

	public Map<Integer,PlayerRepository> playerMap = new HashMap<Integer,PlayerRepository>();
	public Map<Integer,LinkedList<Field>> fieldMap = new HashMap<Integer,LinkedList<Field>>();
	int roomNumber = 0;

	@Before
	public void initTest() {

		PlayerRepository playerRepository = new PlayerRepository();

		Player player = new Player(0, "Michal", "red");
		Player player2 = new Player(1, "Angela", "blue");
		playerRepository.addPlayer(player);
		playerRepository.addPlayer(player2);

		Assert.assertEquals(2, playerRepository.getPlayerList().size());

		playerMap.put(roomNumber, playerRepository);

		Assert.assertEquals(1, playerMap.size());

		LinkedList<Field> fields = new LinkedList<Field>();
		FieldRepository repo = new FieldRepository();
		repo.createRepository();
		fields = (LinkedList<Field>) repo.getRepository();
		fieldMap.put(roomNumber, fields);

		Assert.assertEquals(1, fieldMap.size());
	}

	@Test
	public void mainTest(){

		Player player0 = playerMap.get(roomNumber).getPlayerList().get(0);
		Player player1 = playerMap.get(roomNumber).getPlayerList().get(1);

		Assert.assertEquals(6, rollDice(2, 4, 0));
		Assert.assertEquals(6, player0.getPosition());
		Assert.assertEquals(1500, player0.getMoney());
		buyField(player0.getPosition(), 0);
		Assert.assertEquals(1400, player0.getMoney());
		Assert.assertEquals(1, player0.getUserFields().size());

		Assert.assertEquals(10, rollDice(5, 5, 0));
		Assert.assertEquals(16, player0.getPosition());
		Assert.assertEquals(1400, player0.getMoney());
		buyField(player0.getPosition(), 0);
		Assert.assertEquals(1220, player0.getMoney());
		Assert.assertEquals(2, player0.getUserFields().size());

/*		Assert.assertEquals(6, rollDice(3, 3, 1));
		Assert.assertEquals(6, player1.getPosition());
		Assert.assertEquals(1500, player0.getMoney());


		Assert.assertEquals(2, rollDice(1, 1, 1));
		Assert.assertEquals(8, player1.getPosition());*/

	}

	@Test
	public void otherPlayerTwiceSamePositionTest(){

		Player player0 = playerMap.get(roomNumber).getPlayerList().get(0);
		Player player1 = playerMap.get(roomNumber).getPlayerList().get(1);

		player0.setPosition(16);
		Assert.assertEquals(16, player0.getPosition());
		Assert.assertEquals(1500, player0.getMoney());
		buyField(player0.getPosition(), 0);
		Assert.assertEquals(1320, player0.getMoney());
		Assert.assertEquals(1, player0.getUserFields().size());

		player1.setPosition(16);
		Assert.assertEquals(16, player1.getPosition());
		Assert.assertEquals(1500, player1.getMoney());
		buyField(player1.getPosition(), 1);

		Assert.assertEquals(1410, player0.getMoney());
		Assert.assertEquals(1410, player1.getMoney());
		Assert.assertEquals(0, player1.getUserFields().size());

	}

	@Test
	public void samePlayerTwiceSamePositionTest(){

		Player player0 = playerMap.get(roomNumber).getPlayerList().get(0);

		player0.setPosition(16);
		Assert.assertEquals(16, player0.getPosition());
		Assert.assertEquals(1500, player0.getMoney());
		buyField(player0.getPosition(), 0);
		Assert.assertEquals(1320, player0.getMoney());
		Assert.assertEquals(1, player0.getUserFields().size());

		//setPosition po wpisaniu powinno byc na numerze tego pola
		//ide o 40 czyli do okola
		//po dodaniu bonusu na start zmienic na 1520
		player0.setPosition(40);
		Assert.assertEquals(16, player0.getPosition());
		Assert.assertEquals(1320, player0.getMoney());
		buyField(player0.getPosition(), 0);
		Assert.assertEquals(1320, player0.getMoney());
		Assert.assertEquals(1, player0.getUserFields().size());

	}

	@Test
	public void noEnoughtMoneyToBuyTest(){

		Player player0 = playerMap.get(roomNumber).getPlayerList().get(0);

		player0.setPosition(16);
		player0.setMoney(0);
		Assert.assertEquals(16, player0.getPosition());
		Assert.assertEquals(0, player0.getMoney());
		buyField(player0.getPosition(), 0);
		Assert.assertEquals(0, player0.getMoney());
		Assert.assertEquals(0, player0.getUserFields().size());
	}

	public int rollDice(int first, int second, int playerNumber){

		PlayerRepository playerRepository = playerMap.get(roomNumber);

		Dice dice = new Dice(first, second, playerNumber);
        int added = dice.addNumbers();

        Player player = playerRepository.getPlayerList().get(playerNumber);
        player.setPosition(added);

        return added;
	}

	public void buyField(int position, int playerNumber){

		PlayerRepository playerRepository = playerMap.get(roomNumber);

		Property property = new Property(position, playerNumber);

        Player player = playerRepository.getPlayerList().get(property.getPlayerNumber());

        NormalField field = (NormalField) fieldMap.get(roomNumber).get(property.getPosition());
        if(!field.isBought){
        	if(player.getMoney() > field.getValue()){
	        	player.addField(field);
		        player.addMoney(-field.getValue());
	        	field.setBought(true);
	        	field.setOwner(player.getPlayerNumber());
        	}
        } else {
        	List<Field> fieldList = player.getUserFields();
        	if(!fieldList.contains(field)){
        		player.addMoney(-field.getValueToPay());
        		Player owner = playerRepository.getPlayerList().get(field.getOwner());
        		owner.addMoney(field.getValueToPay());
        	}
        }
	}



	@Test
	public void checkIfRollDiceWork(){

		Assert.assertEquals(1, playerMap.size());

		PlayerRepository playerRepository = playerMap.get(roomNumber);

		Assert.assertEquals(2, playerRepository.getPlayerList().size());

		Dice dice = new Dice(2, 2, 0);
        int added = dice.addNumbers();

        Assert.assertEquals(4, added);

        Player player = playerRepository.getPlayerList().get(0);
        player.setPosition(added);

        Assert.assertEquals(4, player.getPosition());

	}

}
