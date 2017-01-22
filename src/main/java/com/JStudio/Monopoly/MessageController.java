package com.JStudio.Monopoly;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.JStudio.Monopoly.Field.ExtraField;
import com.JStudio.Monopoly.Field.GoToJailField;
import com.JStudio.Monopoly.Field.MiddleField;
import com.JStudio.Monopoly.Field.NormalField;
import com.JStudio.Monopoly.Field.WinOrLoseMoneyField;
import com.JStudio.Monopoly.Player.Dice;
import com.JStudio.Monopoly.Player.Player;
import com.JStudio.Monopoly.Player.Property;

@Controller
public class MessageController {

	RoomController roomController = new RoomController();

	/* Message Controllers */
	/*@MessageMapping("/hello")
    @SendTo("/topic/add")
    public Player addingNumbers(Dice dice) throws Exception {
        Thread.sleep(3000); // simulated delay
        int added = dice.addNumbers();
        Player player = roomController.playerList.getPlayerList().get(dice.getPlayerNumber());
        player.setPosition(added);
        return player;
    }

	@MessageMapping("/buyNormalField")
    @SendTo("/topic/buyField")
    public Player buyNormalField(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        Player player = roomController.playerList.getPlayerList().get(property.getPlayerNumber());
        NormalField field = (NormalField) roomController.fields.get(property.getPosition());
        field.doMainActivities(player);
        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/buyMiddleField")
    @SendTo("/topic/buyField")
    public Player buyMiddleField(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        Player player = roomController.playerList.getPlayerList().get(property.getPlayerNumber());
        MiddleField field = (MiddleField) roomController.fields.get(property.getPosition());
        field.doMainActivities(player);
        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/buyExtraField")
    @SendTo("/topic/buyField")
    public Player buyExtraField(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        Player player = roomController.playerList.getPlayerList().get(property.getPlayerNumber());
        ExtraField field = (ExtraField) roomController.fields.get(property.getPosition());
        field.doMainActivities(player);
        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/winOrLoseMoney")
    @SendTo("/topic/playerMoney")
    public Player changeMoney(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        Player player = roomController.playerList.getPlayerList().get(property.getPlayerNumber());
        WinOrLoseMoneyField field = (WinOrLoseMoneyField) roomController.fields.get(property.getPosition());
        field.doMainActivities(player);
        System.out.println(player.toString());
        return player;
    }

	@MessageMapping("/goToJail")
    @SendTo("/topic/playerMoney")
    public Player goToJail(Property property) throws Exception {
        Thread.sleep(3000); // simulated delay
        Player player = roomController.playerList.getPlayerList().get(property.getPlayerNumber());
        GoToJailField field = (GoToJailField) roomController.fields.get(property.getPosition());
        field.doMainActivities(player);
        System.out.println(player.toString());
        return player;
    }
*/
}
