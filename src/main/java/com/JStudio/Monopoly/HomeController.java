package com.JStudio.Monopoly;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
import com.JStudio.Monopoly.User.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	RoomController roomController = new RoomController();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		/*TODO if back button pressed program should remove player from map
		int j = 0;
		int size = 10;	// number of existing rooms
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			//The user is logged in
			Map<Integer,PlayerRepository> playerMap = roomController.getMap();
			System.out.println("zalogowany");
			for(int i = 0; i < size; i++){
				System.out.println("w petli");
				if(playerMap.containsKey(i)){
					System.out.println("znalazlem taki klucz = " + i);
					PlayerRepository playerRepository = playerMap.get(i);
					for(Player player : playerRepository.getPlayerList()){
						if(player.equals(auth.getName())){
							System.out.println("usuwam");
							playerMap.get(i).getPlayerList().remove(player);
							roomController.setMap(playerMap);
						}
					}
				}
			}
		}*/
		int sizeInRoom = roomController.getSizeInRoom();
		model.addAttribute("sizeInRoom", sizeInRoom);

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			System.out.println(targetUrl);
			if(StringUtils.hasText(targetUrl)){
				model.addObject("targetUrl", targetUrl);
				model.addObject("loginUpdate", true);
			}
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}

	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?""
                             :session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}

	@RequestMapping(value = "/admin/update**", method = RequestMethod.GET)
	public ModelAndView updatePage(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();

		if (isRememberMeAuthenticated()) {
			//send login for update
			setRememberMeTargetUrlToSession(request);
			model.addObject("loginUpdate", true);
			model.setViewName("/login");

		} else {
			model.setViewName("update");
		}

		return model;

	}

	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/admin/update");
		}
	}

	private boolean isRememberMeAuthenticated() {

		Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupGet() {

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(User user, Model model) {

		model.addAttribute("newUser", user);
		return "rooms";
	}

	@RequestMapping(value = "/rooms**", method = RequestMethod.GET)
	public ModelAndView roomsPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("rooms");

		return model;
	}


	/* Message Controllers */
/*	@MessageMapping("/hello")
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
    }*/

}
