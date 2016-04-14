package workflow;

import controller.FieldController;

public class GameLogic {
	public void fight(FieldController attacker, FieldController defender) {
		int defenderDicesOnThisField = defender.getModel().getNumberOfDices();
		//int attackerDicesOnThisField = attacker.getModel().getNumberOfDices();
		
		defender.getModel().setNumberOfDices(defenderDicesOnThisField - 1);
	}

}
