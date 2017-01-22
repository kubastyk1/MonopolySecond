package com.JStudio.Monopoly;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.JStudio.Monopoly.Field.ExtraField;
import com.JStudio.Monopoly.Field.Field;
import com.JStudio.Monopoly.Field.FieldRepository;
import com.JStudio.Monopoly.Field.GoToJailField;
import com.JStudio.Monopoly.Field.MiddleField;
import com.JStudio.Monopoly.Field.NormalField;
import com.JStudio.Monopoly.Field.WinOrLoseMoneyField;
import com.JStudio.Monopoly.Player.Dice;
import com.JStudio.Monopoly.Player.Player;
import com.JStudio.Monopoly.Player.PlayerRepository;
import com.JStudio.Monopoly.Player.Property;

@Controller
@RequestMapping("/room/{roomNumber}")
public class RoomController {

	public String[] colors = { "red", "blue", "green", "pink" };
	public Map<Integer,PlayerRepository> playerMap = new HashMap<Integer,PlayerRepository>();
	public Map<Integer,LinkedList<Field>> fieldMap = new HashMap<Integer,LinkedList<Field>>();
	private int roomNumber = 0;
	private int spaceInRoom;
	private boolean isRoomFull;

	@RequestMapping("/{numberOfPlayers}")
	public String waitForAll(@PathVariable int roomNumber, @PathVariable int numberOfPlayers, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			/* The user is not logged in */
			return "login";
		}

		boolean playerCreated = false;
		this.roomNumber = roomNumber;
		spaceInRoom = numberOfPlayers;
		String sign;
		PlayerRepository playerRepository;
		int howManyPlayersOnList = 0;

		/* Checks if the room already exist*/
		if(playerMap.containsKey(roomNumber)){
			playerRepository = playerMap.get(roomNumber);

			/*No such player on the list*/
			for (Player player : playerRepository.getPlayerList()) {
				if (auth.getName().equals(player.getUsername())) {

					playerCreated = true;
				} else {
					/*if game is full redirect home*/
				/*	if (isRoomFull) {
						return "home";
					}*/
				}
			}

		} else {
			playerRepository = new PlayerRepository();
		}

		howManyPlayersOnList = playerRepository.getPlayerList().size();

		if (playerCreated == false) {
			Player player = new Player(howManyPlayersOnList, auth.getName(), colors[howManyPlayersOnList]);
			playerRepository.addPlayer(player);
		}

		int playersNeededToStart = numberOfPlayers - howManyPlayersOnList;
		if (playersNeededToStart == 1) {
			sign = "Waiting for " + playersNeededToStart + " player.";
		} else {
			sign = "Waiting for " + playersNeededToStart + " players.";
		}
		model.addAttribute("sign", sign);
		model.addAttribute("playerList", playerRepository);

		playerMap.remove(roomNumber);
		playerMap.put(roomNumber, playerRepository);

		if (howManyPlayersOnList < numberOfPlayers) {
			return "rooms";
		} else {

			/* Creating new play area */
			LinkedList<Field> fields = new LinkedList<Field>();
			FieldRepository repo = new FieldRepository();
			repo.createRepository();
			fields = (LinkedList<Field>) repo.getRepository();
			fieldMap.put(roomNumber, fields);
			isRoomFull = true;

			return "redirect:/room/{roomNumber}/playground";
		}
	}

	@RequestMapping(value = "/playground", method = RequestMethod.GET)
	public String game(@PathVariable int roomNumber, Model model) {

		System.out.println(playerMap.get(1).getPlayerList().get(0).toString());
		System.out.println(playerMap.get(1).getPlayerList().get(1).toString());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			/* The user is not logged in */
			return "login";
		}

		PlayerRepository playerRepository = playerMap.get(roomNumber);
		LinkedList<Field> fields = fieldMap.get(roomNumber);

		model.addAttribute("fieldRepo", fields);
		model.addAttribute("playerList", playerRepository.getPlayerList());
		model.addAttribute("numberOfPlayersInGame", spaceInRoom);
		return "playground";
	}

	public Map<Integer,PlayerRepository> getMap(){
		return playerMap;
	}

	public void setMap(Map<Integer,PlayerRepository> playerMap){
		this.playerMap = playerMap;
	}


	public int getSpaceInRoom(){

		return spaceInRoom;
	}

	public int getSizeInRoom(){

		int sizeInRoom;

		if(playerMap.isEmpty()){
			sizeInRoom = 50;
		} else {
			sizeInRoom = playerMap.get(1).getPlayerList().size();
		}
		return sizeInRoom;
	}
















	/* Message Controllers */
	@MessageMapping("/hello")
    @SendTo("/topic/add")
    public Player addingNumbers(Dice dice) throws Exception {
        Thread.sleep(3000); // simulated delay
        PlayerRepository playerRepository = playerMap.get(roomNumber);
        int added = dice.addNumbers();
        Player player = playerRepository.getPlayerList().get(dice.getPlayerNumber());
        int oldPosition = player.getPosition();
        player.setPosition(added);

        if(oldPosition > player.getPosition())
        	player.addMoney(200);

        return player;
    }

	@MessageMapping("/buyNormalField")
    @SendTo("/topic/buyField")
    public Player buyNormalField(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        PlayerRepository playerRepository = playerMap.get(roomNumber);
        Player player = playerRepository.getPlayerList().get(property.getPlayerNumber());
        NormalField field = (NormalField) fieldMap.get(roomNumber).get(property.getPosition());

//        field.doMainActivities(player); 	stare

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

        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/buyMiddleField")
    @SendTo("/topic/buyField")
    public Player buyMiddleField(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        PlayerRepository playerRepository = playerMap.get(roomNumber);
        Player player = playerRepository.getPlayerList().get(property.getPlayerNumber());
        MiddleField field = (MiddleField) fieldMap.get(roomNumber).get(property.getPosition());

     //   field.doMainActivities(player);

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

        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/buyExtraField")
    @SendTo("/topic/buyField")
    public Player buyExtraField(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        PlayerRepository playerRepository = playerMap.get(roomNumber);
        Player player = playerRepository.getPlayerList().get(property.getPlayerNumber());
        ExtraField field = (ExtraField) fieldMap.get(roomNumber).get(property.getPosition());

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

        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/winOrLoseMoney")
    @SendTo("/topic/playerMoney")
    public Player changeMoney(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        PlayerRepository playerRepository = playerMap.get(roomNumber);
        Player player = playerRepository.getPlayerList().get(property.getPlayerNumber());
        WinOrLoseMoneyField field = (WinOrLoseMoneyField) fieldMap.get(roomNumber).get(property.getPosition());
        field.doMainActivities(player);
        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/goToJail")
    @SendTo("/topic/playerMoney")
    public Player goToJail(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        PlayerRepository playerRepository = playerMap.get(roomNumber);
        Player player = playerRepository.getPlayerList().get(property.getPlayerNumber());
        GoToJailField field = (GoToJailField) fieldMap.get(roomNumber).get(property.getPosition());
        field.doMainActivities(player);
        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/nextPlayer")
    @SendTo("/topic/getNextPlayer")
    public Player nextPlayer(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        PlayerRepository playerRepository = playerMap.get(roomNumber);
        Player nextPlayer;

    	int oldPlayerNumber = property.getPlayerNumber();
        Player player = playerRepository.getPlayerList().get(oldPlayerNumber);

        if(playerRepository.getPlayerList().size() > oldPlayerNumber + 1){
        	nextPlayer = playerRepository.getPlayerList().get(oldPlayerNumber+1);
        } else {
        	nextPlayer = playerRepository.getPlayerList().get(0);
        }

        System.out.println("nastepny " + nextPlayer.toString());
        return nextPlayer;
    }
}
