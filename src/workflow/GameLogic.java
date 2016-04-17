package workflow;

import java.util.ArrayList;

import model.PlayerBean;
import view.GameView;
import controller.FieldController;

/**
 * 
 * Játék logikáját tartalmazó osztály
 *
 */

public class GameLogic {
	private GameView view;
	private int calculatedTableWidth;
	private ArrayList<PlayerBean> players;
	private String winnerPlayer;
	
	/**
	 * 
	 * @param view GameView panelje. Minden gui-t el tudunk érni ezen keresztül játék közben ami a játékhoz kell
	 * @param calculatedTableWitdh Tábla mérete
	 * @param players Játékosok
	 */
	
	public GameLogic(GameView view, int calculatedTableWitdh, ArrayList<PlayerBean> players) {
		this.view = view;
		this.calculatedTableWidth = calculatedTableWitdh;
		this.players = players;
	}
	
	/**
	 * Játék közbeni harc. Támadó megtámadja a védekezõt kockákat guritanak
	 * Ha a támadó nyer, elfoglalja a mezõt. Minden kockát amivel támadott át teszünk
	 * a védekezõ mezõjére, kivéve egyet. A védekezõ kockái elvesznek.
	 * Ha a védekezõ nyer, a támadó saját mezõjérõl el tûnnek a kockák, kivéve 1-et. A
	 * védekezõ mezõn minden változatlan marad
	 * 
	 * @param attacker Támadó mezõ controllerje
	 * @param defender Védekezõ mezõ controllerje
	 */
	
	public void fight(FieldController attacker, FieldController defender) {
		int defenderDicesOnThisField = defender.getModel().getNumberOfDices();
		int attackerDicesOnThisField = attacker.getModel().getNumberOfDices();
		
		int attackNumber = throwDices(attacker.getModel().getNumberOfDices()); //Támadó pontszáma amit guritott
		int defendNumber = throwDices(defender.getModel().getNumberOfDices()); //Védekezõ pontszáma amit guritott
		
		System.out.println(attacker.getModel().getOwnnerOfThisField().getColor().toString()+" attacked "+defender.getModel().getOwnnerOfThisField().getColor().toString()+". Result is:  "
				+attackNumber+" : "+defendNumber);
		
		view.setAttackerScore(attacker.getModel().getOwnnerOfThisField().getColor().toString()+": "+attackNumber);
		view.setDefenderScore(defender.getModel().getOwnnerOfThisField().getColor().toString()+": "+defendNumber);
		
		if(attackNumber > defendNumber) { //Támadó nyert
			attacker.getModel().getOwnnerOfThisField().increaseTerritoriesNumber();
			defender.getModel().getOwnnerOfThisField().decreaseTerritoriesNumber();
			
			System.out.println(attacker.getModel().getOwnnerOfThisField().getNumberOfTerritories()+" "+defender.getModel().getOwnnerOfThisField().getNumberOfTerritories());
			
			defender.getModel().getOwnnerOfThisField().removeDices(defenderDicesOnThisField);
			defender.getModel().setOwnnerOfThisField(attacker.getModel().getOwnnerOfThisField());
			defender.getModel().setNumberOfDices(attackerDicesOnThisField - 1);
			attacker.getModel().setNumberOfDices(1);
			
			defender.getView().setBackgroundColor(attacker.getModel().getOwnnerOfThisField().getColor());
			defender.getView().setDiceNumberText(attackerDicesOnThisField - 1);
			attacker.getView().setDiceNumberText(1);
		}
		else { //Védekezõ nyert
			attacker.getModel().removeDices(attackerDicesOnThisField - 1);
			attacker.getModel().getOwnnerOfThisField().removeDices(attackerDicesOnThisField-1);
			attacker.getView().setDiceNumberText(1);
		}
		
		/**
		 * Ha a játéknak vége van (csak 1 játékos maradt) közöljük azt a játékosokkal
		 */
		
		if(isGameEnded()) {
			view.showGameOverText(winnerPlayer);
		}
	}
	
	/**
	 * Számítógépek támadásainak szimulálása
	 * 
	 * @param fields Mezõk controllerjei a táblán
	 * @param players Játékosok akik játszanak
	 */
	
	public void computersAttack(FieldController[][] fields, ArrayList<PlayerBean> players) {
		for(int i = 1; i < players.size(); i++) {
			
			/**
			 * 3/4-ed eséllyel támad 1/4-ed eséllyel tovább adja a kört
			 */
			while(UtilFunctions.generateIntBetweenRange(1, 4) <= 3 ){
				for(int row = 0; row < calculatedTableWidth; row++) {
					for(int column = 0; column < calculatedTableWidth; column++) {
						if(fields[row][column].getModel().getOwnnerOfThisField() == players.get(i) && fields[row][column].getModel().getNumberOfDices() > 1){
							FieldController attacker = fields[row][column];
							FieldController defender = selectFieldForAttack(row,column, fields);
							
							if(defender != null) {
								this.fight(attacker, defender);
							}
						}
					}
				}
			}
			
			this.addAndRandomizeNewDicesForPlayer(players.get(i), fields);
		}
	}
	
	
	/**
	 * 
	 * @param row Sor ahonnan mezõt választunk
	 * @param column oszlop ahonnan mezõt választunk
	 * @param fields Mezõk controllerjei
	 * @return
	 */
	
	private FieldController selectFieldForAttack(int row, int column, FieldController[][] fields) {
		for(int i = 0; i< calculatedTableWidth; i++ ) {
			for(int j = 0; j< calculatedTableWidth; j++ ) {
				if(isNeighBour(i, j, row, column) && fields[i][j].getModel().getOwnnerOfThisField() != fields[row][column].getModel().getOwnnerOfThisField()) {
					return fields[i][j];
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Dobás szimulálása. Annyi kockával dobunk amennyi a mezõn van.
	 * Összegezzük a dobott számok értékeit.
	 * 
	 * @param numberOfDices Kockák száma
	 * @return Dobott értékek összege
	 */
	
	private int throwDices(int numberOfDices) {
		int sumOfThrewDiceValues = 0;
		
		for (int i = 0 ; i < numberOfDices; i++) {
			sumOfThrewDiceValues += UtilFunctions.generateIntBetweenRange(1, 6);
		}
		
		return sumOfThrewDiceValues;
	}
	
	/**
	 * 
	 * @param tryingRowToSelect Kiválasztani próbált mezõ sora
	 * @param tryingColumnToSelect Kiválasztani próbált mezõ oszlopa
	 * @param selectedRow Kiválaszott mezõ sora
	 * @param selectedColumn Kiválaszott mezõ oszlopa
	 * @return Szomszédosak-e?
	 */
	
	private boolean isNeighBour(int tryingRowToSelect, int tryingColumnToSelect, int selectedRow, int selectedColumn) {
		return Math.abs(tryingRowToSelect-selectedRow) < 2 && Math.abs(tryingColumnToSelect - selectedColumn) <2; 
	}
	
	/**
	 * Random adjunk hozzá kockákat a játékoshoz, aki tovább adta a kört. A kockák száma
	 * amit hozzáadunk a mezõkhöz a Rendelkezésre álló mezõk száma játékosonként/2. (páros páratlan értékeket figyelembe
	 * véve)
	 * 
	 * @param player Játékosok
	 * @param fields Mezõk controllerjei
	 */
	
	public void addAndRandomizeNewDicesForPlayer(PlayerBean player, FieldController[][] fields) {
		int numberOfNewDices = player.getNumberOfTerritories() % 2 == 0 
				? player.getNumberOfTerritories() / 2 
				: player.getNumberOfTerritories() / 2 + 1;
				
				System.out.println(numberOfNewDices+" dices added to the territories of the "+player.getColor().toString()+ " player!");
				
		while(numberOfNewDices > 0) {
			int randomRow = UtilFunctions.generateIntBetweenRange(0, calculatedTableWidth-1);
			int randomColumn = UtilFunctions.generateIntBetweenRange(0, calculatedTableWidth-1);
			
			if(fields[randomRow][randomColumn].getModel().getOwnnerOfThisField() == player) {
				fields[randomRow][randomColumn].getModel().addDices(1);
				fields[randomRow][randomColumn].getView().setDiceNumberText(fields[randomRow][randomColumn].getModel().getNumberOfDices());
				numberOfNewDices--;
			}
		}
	}
	
	/**
	 * Van-e egynél több játékos akinek van mezõje?
	 * 
	 * @return Játéknak vége van-e?
	 */
	
	private boolean isGameEnded() {
		int numberOfPlayersWhoHaveTerritorries = 0;
		
		for(PlayerBean player: players) {
			if(player.getNumberOfTerritories() > 0) {
				numberOfPlayersWhoHaveTerritorries++;
				winnerPlayer = player.getColor().toString();
			}
		}
		
		return numberOfPlayersWhoHaveTerritorries == 1 ? true : false;
	}
}
