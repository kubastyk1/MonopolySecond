package com.JStudio.Monopoly.Field;

import java.util.LinkedList;
import java.util.List;

public class FieldRepository {

	LinkedList<Field> gameFields;

	public void createRepository(){

		gameFields = new LinkedList<Field>();
		gameFields.add(new EmptyField(0,"Start!"));
		gameFields.add(new NormalField(1, "Brown1", 1, 60));
		gameFields.add(new WinOrLoseMoneyField(2,"Community", 100));
		gameFields.add(new NormalField(3, "Brown2", 1, 60));
		gameFields.add(new WinOrLoseMoneyField(4, "Incom", -200));
		gameFields.add(new MiddleField(5, "Middle", 100));
		gameFields.add(new NormalField(6, "Light1", 2, 100));
		gameFields.add(new WinOrLoseMoneyField(7, "Chance!", 200));
		gameFields.add(new NormalField(8, "Light2", 2, 100));
		gameFields.add(new NormalField(9, "Light3", 2, 120));
		gameFields.add(new EmptyField(10, "Jail!"));
		gameFields.add(new NormalField(11, "Pink1", 3, 140));
		gameFields.add(new ExtraField(12, "Buy Me!", 50, 150));
		gameFields.add(new NormalField(13, "Pink2", 3, 140));
		gameFields.add(new NormalField(14, "Pink3", 3, 160));
		gameFields.add(new MiddleField(15, "Middle Field", 100));
		gameFields.add(new NormalField(16, "Orange1", 4, 180));
		gameFields.add(new WinOrLoseMoneyField(17,"Community Chest", 100));
		gameFields.add(new NormalField(18, "Orange2", 4, 180));
		gameFields.add(new NormalField(19, "Orange3", 4, 200));
		gameFields.add(new EmptyField(20, "Parking"));
		gameFields.add(new NormalField(21, "Red 1", 5, 220));
		gameFields.add(new WinOrLoseMoneyField(22, "Chance!", 200));
		gameFields.add(new NormalField(23, "Red 2", 5, 220));
		gameFields.add(new NormalField(24, "Red 3", 5, 240));
		gameFields.add(new MiddleField(25, "Middle ", 100));
		gameFields.add(new NormalField(26, "Yellow1", 6, 260));
		gameFields.add(new NormalField(27, "Yellow2", 6, 260));
		gameFields.add(new ExtraField(28, "Buy Me!", 50, 150));
		gameFields.add(new NormalField(29, "Yellow3", 6, 280));
		gameFields.add(new GoToJailField(30, "Go to JAIL!"));
		gameFields.add(new NormalField(31, "Green1", 7, 300));
		gameFields.add(new NormalField(32, "Green2", 7, 300));
		gameFields.add(new WinOrLoseMoneyField(33, "Community Chest",  100));
		gameFields.add(new NormalField(34, "Green3", 7, 320));
		gameFields.add(new MiddleField(35,"Middle Field", 100));
		gameFields.add(new WinOrLoseMoneyField(36, "Chance?", 200));
		gameFields.add(new NormalField(37, "darkBlue1", 8, 350));
		gameFields.add(new WinOrLoseMoneyField(38, "LuxuryTax", -200));
		gameFields.add(new NormalField(39, "darkBlue2", 8, 400));
	}

	public List<Field> getRepository(){

		return gameFields;
	}

	public Field getFieldFromRepository(int fieldNumber){

		Field field = gameFields.get(fieldNumber);
		return field;
	}

}
