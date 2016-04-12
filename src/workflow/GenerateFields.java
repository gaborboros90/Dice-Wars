package workflow;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.FieldBean;
import model.PlayerBean;
import model.PlayerColor;
import view.FieldView;
import controller.FieldController;

public class GenerateFields {
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
				
				System.out.println(availableFieldsPerPlayer.get(randomPlayerIndex) + " "+availableDicesPerPlayer.get(randomPlayerIndex));
				
				fieldControllers[x][y] = new FieldController(new FieldView(), new FieldBean( randomDiceNumberOnThisField, players.get(randomPlayerIndex)));
				gameView.add(fieldControllers[x][y].getView());
			}
		}
		
		return fieldControllers;
	}
	
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
