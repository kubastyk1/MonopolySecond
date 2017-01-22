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

public class WinOrLoseMoneyFieldTest {

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
	public void winOrLoseMoneyTest() throws Exception{

		Dice dice = new Dice(1,1,0);
		roomController.addingNumbers(dice);

		Assert.assertEquals(2, player0.getPosition());
		Assert.assertEquals(1500, player0.getMoney());

		property = new Property(player0.getPosition(), 0);
		roomController.changeMoney(property);

		Assert.assertEquals(1600, player0.getMoney());
		Assert.assertEquals(0, player0.getUserFields().size());

		dice = new Dice(1,1,0);
		roomController.addingNumbers(dice);

		Assert.assertEquals(4, player0.getPosition());
		Assert.assertEquals(1600, player0.getMoney());

		property = new Property(player0.getPosition(), 0);
		roomController.changeMoney(property);

		Assert.assertEquals(1400, player0.getMoney());
		Assert.assertEquals(0, player0.getUserFields().size());

	}

}
