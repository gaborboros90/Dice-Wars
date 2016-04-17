package workflow;

import java.util.ArrayList;

import model.PlayerBean;
import view.GameView;
import controller.FieldController;

/**
 * 
 * J�t�k logik�j�t tartalmaz� oszt�ly
 *
 */

public class GameLogic {
	private GameView view;
	private int calculatedTableWidth;
	private ArrayList<PlayerBean> players;
	private String winnerPlayer;
	
	/**
	 * 
	 * @param view GameView panelje. Minden gui-t el tudunk �rni ezen kereszt�l j�t�k k�zben ami a j�t�khoz kell
	 * @param calculatedTableWitdh T�bla m�rete
	 * @param players J�t�kosok
	 */
	
	public GameLogic(GameView view, int calculatedTableWitdh, ArrayList<PlayerBean> players) {
		this.view = view;
		this.calculatedTableWidth = calculatedTableWitdh;
		this.players = players;
	}
	
	/**
	 * J�t�k k�zbeni harc. T�mad� megt�madja a v�dekez�t kock�kat guritanak
	 * Ha a t�mad� nyer, elfoglalja a mez�t. Minden kock�t amivel t�madott �t tesz�nk
	 * a v�dekez� mez�j�re, kiv�ve egyet. A v�dekez� kock�i elvesznek.
	 * Ha a v�dekez� nyer, a t�mad� saj�t mez�j�r�l el t�nnek a kock�k, kiv�ve 1-et. A
	 * v�dekez� mez�n minden v�ltozatlan marad
	 * 
	 * @param attacker T�mad� mez� controllerje
	 * @param defender V�dekez� mez� controllerje
	 */
	
	public void fight(FieldController attacker, FieldController defender) {
		int defenderDicesOnThisField = defender.getModel().getNumberOfDices();
		int attackerDicesOnThisField = attacker.getModel().getNumberOfDices();
		
		int attackNumber = throwDices(attacker.getModel().getNumberOfDices()); //T�mad� pontsz�ma amit guritott
		int defendNumber = throwDices(defender.getModel().getNumberOfDices()); //V�dekez� pontsz�ma amit guritott
		
		System.out.println(attacker.getModel().getOwnnerOfThisField().getColor().toString()+" attacked "+defender.getModel().getOwnnerOfThisField().getColor().toString()+". Result is:  "
				+attackNumber+" : "+defendNumber);
		
		view.setAttackerScore(attacker.getModel().getOwnnerOfThisField().getColor().toString()+": "+attackNumber);
		view.setDefenderScore(defender.getModel().getOwnnerOfThisField().getColor().toString()+": "+defendNumber);
		
		if(attackNumber > defendNumber) { //T�mad� nyert
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
		else { //V�dekez� nyert
			attacker.getModel().removeDices(attackerDicesOnThisField - 1);
			attacker.getModel().getOwnnerOfThisField().removeDices(attackerDicesOnThisField-1);
			attacker.getView().setDiceNumberText(1);
		}
		
		/**
		 * Ha a j�t�knak v�ge van (csak 1 j�t�kos maradt) k�z�lj�k azt a j�t�kosokkal
		 */
		
		if(isGameEnded()) {
			view.showGameOverText(winnerPlayer);
		}
	}
	
	/**
	 * Sz�m�t�g�pek t�mad�sainak szimul�l�sa
	 * 
	 * @param fields Mez�k controllerjei a t�bl�n
	 * @param players J�t�kosok akik j�tszanak
	 */
	
	public void computersAttack(FieldController[][] fields, ArrayList<PlayerBean> players) {
		for(int i = 1; i < players.size(); i++) {
			
			/**
			 * 3/4-ed es�llyel t�mad 1/4-ed es�llyel tov�bb adja a k�rt
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
	 * @param row Sor ahonnan mez�t v�lasztunk
	 * @param column oszlop ahonnan mez�t v�lasztunk
	 * @param fields Mez�k controllerjei
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
	 * Dob�s szimul�l�sa. Annyi kock�val dobunk amennyi a mez�n van.
	 * �sszegezz�k a dobott sz�mok �rt�keit.
	 * 
	 * @param numberOfDices Kock�k sz�ma
	 * @return Dobott �rt�kek �sszege
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
	 * @param tryingRowToSelect Kiv�lasztani pr�b�lt mez� sora
	 * @param tryingColumnToSelect Kiv�lasztani pr�b�lt mez� oszlopa
	 * @param selectedRow Kiv�laszott mez� sora
	 * @param selectedColumn Kiv�laszott mez� oszlopa
	 * @return Szomsz�dosak-e?
	 */
	
	private boolean isNeighBour(int tryingRowToSelect, int tryingColumnToSelect, int selectedRow, int selectedColumn) {
		return Math.abs(tryingRowToSelect-selectedRow) < 2 && Math.abs(tryingColumnToSelect - selectedColumn) <2; 
	}
	
	/**
	 * Random adjunk hozz� kock�kat a j�t�koshoz, aki tov�bb adta a k�rt. A kock�k sz�ma
	 * amit hozz�adunk a mez�kh�z a Rendelkez�sre �ll� mez�k sz�ma j�t�kosonk�nt/2. (p�ros p�ratlan �rt�keket figyelembe
	 * v�ve)
	 * 
	 * @param player J�t�kosok
	 * @param fields Mez�k controllerjei
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
	 * Van-e egyn�l t�bb j�t�kos akinek van mez�je?
	 * 
	 * @return J�t�knak v�ge van-e?
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
