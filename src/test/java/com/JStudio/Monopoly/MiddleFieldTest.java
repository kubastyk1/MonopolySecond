package com.JStudio.Monopoly;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.JStudio.Monopoly.Field.Field;
import com.JStudio.Monopoly.Field.FieldRepository;
import com.JStudio.Monopoly.Player.Dice;
import com.JStudio.Monopoly.Player.Player;
import com.JStudio.Monopoly.Player.PlayerRepository;
import com.JStudio.Monopoly.Player.Property;

import junit.framework.Assert;

public class MiddleFieldTest {

	public Map<Integer,PlayerRepository> playerMap = new HashMap<Integer,PlayerRepository>();
	public Map<Integer,LinkedList<Field>> fieldMap = new HashMap<Integer,LinkedList<Field>>();
	public RoomController roomController = new RoomController();
	public Property property;
	public Player player0;
	public Player player1;
	int roomNumber = 0;

	@Before
	public void initTest() {

		PlayerRepository playerRepository = new PlayerRepository();

		player0 = new Player(0, "Michal", "red");
		player1 = new Player(1, "Angela", "blue");
		playerRepository.addPlayer(player0);
		playerRepository.addPlayer(player1);

		Assert.assertEquals(2, playerRepository.getPlayerList().size());

		roomController.playerMap.put(roomNumber, playerRepository);

		Assert.assertEquals(1, roomController.playerMap.size());

		LinkedList<Field> fields = new LinkedList<Field>();
		FieldRepository repo = new FieldRepository();
		repo.createRepository();
		fields = (LinkedList<Field>) repo.getRepository();

		roomController.fieldMap.put(roomNumber, fields);

		Assert.assertEquals(1, roomController.fieldMap.size());
	}

	@Test
	public void otherPlayerTwiceSamePositionTest() throws Exception{

		player0.setPosition(5);
		Assert.assertEquals(5, player0.getPosition());
		Assert.assertEquals(1500, player0.getMoney());

		property = new Property(player0.getPosition(), 0);
		roomController.buyMiddleField(property);

		Assert.assertEquals(1400, player0.getMoney());
		Assert.assertEquals(1, player0.getUserFields().size());

		player1.setPosition(5);
		Assert.assertEquals(5, player1.getPosition());
		Assert.assertEquals(1500, player1.getMoney());

		property = new Property(player1.getPosition(), 1);
		roomController.buyMiddleField(property);

		Assert.assertEquals(1450, player0.getMoney());
		Assert.assertEquals(1450, player1.getMoney());
		Assert.assertEquals(0, player1.getUserFields().size());

	}

	@Test
	public void samePlayerTwiceSamePositionTest() throws Exception{

		player0.setPosition(5);
		Assert.assertEquals(5, player0.getPosition());
		Assert.assertEquals(1500, player0.getMoney());

		property = new Property(player0.getPosition(), 0);
		roomController.buyMiddleField(property);

		Assert.assertEquals(1400, player0.getMoney());
		Assert.assertEquals(1, player0.getUserFields().size());

		//setPosition po wpisaniu powinno byc na numerze tego pola
		//ide o 40 czyli do okola
		//po dodaniu bonusu na start zmienic na 1520
		player0.setPosition(40);
		Assert.assertEquals(5, player0.getPosition());
		Assert.assertEquals(1400, player0.getMoney());

		property = new Property(player0.getPosition(), 0);
		roomController.buyNormalField(property);

		Assert.assertEquals(1400, player0.getMoney());
		Assert.assertEquals(1, player0.getUserFields().size());

	}

	@Test
	public void noEnoughtMoneyToBuyTest() throws Exception{

		player0.setPosition(5);
		player0.setMoney(0);
		Assert.assertEquals(5, player0.getPosition());
		Assert.assertEquals(0, player0.getMoney());

		property = new Property(player0.getPosition(), 0);
		roomController.buyMiddleField(property);

		Assert.assertEquals(0, player0.getMoney());
		Assert.assertEquals(0, player0.getUserFields().size());
	}

}
