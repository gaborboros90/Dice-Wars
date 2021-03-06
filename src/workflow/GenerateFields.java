package workflow;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.FieldBean;
import model.PlayerBean;
import view.FieldView;
import controller.FieldController;

/**
 * 
 * Oszt�ly t�bla gener�l�s�ra
 *
 */

public class GenerateFields {
	/**
	 * Statikus met�dus, t�bla gener�l�s�ra
	 * 
	 * @param numberOfPlayers j�t�kosok sz�ma
	 * @param calculatedTableWitdh kisz�m�tott t�bla sz�less�g
	 * @param players J�t�kosok
	 * @param gameView J�t�k panelje
	 * @return Mez� controllerjeinek kett� dimenzi�s t�mbje
	 */
	
	public static FieldController[][] generate(int numberOfPlayers, int calculatedTableWitdh, ArrayList<PlayerBean> players, JPanel gameView) {
		Map<Integer, Integer> availableFieldsPerPlayer = new HashMap<Integer, Integer>();
		Map<Integer, Integer> availableDicesPerPlayer = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < numberOfPlayers; i++ ) {
			availableFieldsPerPlayer.put(i,players.get(i).getNumberOfTerritories());
			availableDicesPerPlayer.put(i,players.get(i).getNumberOfDices());
		}
		
		FieldController[][] fieldControllers = new FieldController[calculatedTableWitdh][calculatedTableWitdh];
		gameView.setLayout(new GridLayout(calculatedTableWitdh, calculatedTableWitdh));
		
		for(int x = 0; x < calculatedTableWitdh; x++ ) {
			for (int y = 0; y < calculatedTableWitdh; y++) {
				int randomPlayerIndex = UtilFunctions.generateIntBetweenRange(0, players.size()-1);
				int randomPlayerFieldNumber = availableFieldsPerPlayer.get(randomPlayerIndex);
				int randomPlayerDicesNumber = availableDicesPerPlayer.get(randomPlayerIndex);
				
				if(randomPlayerFieldNumber == 0) {
					y--;
					continue;
				}
				
				availableFieldsPerPlayer.put(randomPlayerIndex, --randomPlayerFieldNumber);
				int randomDiceNumberOnThisField = Math.min(8, UtilFunctions.generateIntBetweenRange(1, availableDicesPerPlayer.get(randomPlayerIndex) - availableFieldsPerPlayer.get(randomPlayerIndex)));
				availableDicesPerPlayer.put(randomPlayerIndex, randomPlayerDicesNumber - randomDiceNumberOnThisField);
				
				fieldControllers[x][y] = new FieldController(new FieldView(), new FieldBean( randomDiceNumberOnThisField, players.get(randomPlayerIndex)));
				gameView.add(fieldControllers[x][y].getView());
			}
		}
		
		return fieldControllers;
	}
	
	/**
	 * T�blra m�reteinek (sz�less�g ,magass�g) meghat�roz�sa a j�t�kos sz�m�nak �s a t�bla m�ret�nek (S,M,L) alapj�n
	 * 
	 * @param nmbOfPlayers J�t�kos sz�ma
	 * @param tableSize Kiv�lasztott t�bla m�ret (S,M,L)
	 * @return Eg�sz �rt�k, a t�bla sz�less�ge
	 */
	
	public static int getTableSizeRelatedToNumberOfPlayers(int nmbOfPlayers, String tableSize) {
		int tableWidth = 0;
		
		switch (tableSize) {
			case "Small":
					tableWidth = nmbOfPlayers * 2;
				break;
				
			case "Medium":
					tableWidth = nmbOfPlayers * 3;
				break;
				
			case "Large":
					tableWidth = nmbOfPlayers * 4;
				break;
	
			default:
				break;
		}
		
		return tableWidth;
	}
}
