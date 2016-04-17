package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.PlayerBean;
import model.PlayerColor;
import view.FieldView;
import view.GameView;
import workflow.GameLogic;
import workflow.GenerateFields;
import workflow.GeneratePlayers;

/**
 * 
 * Ezt az osztályt a játék játék közbeni vezérlésére használljuk
 * illetve itt helyezzük el az eseménykezelõket is.
 */

public class GameController {
	private int calculatedTableWitdh;
	private GameView view;
	private FieldController[][] fields;
	private ArrayList<PlayerBean> players;
	private int currentlySelectedFieldsRow;
	private int currentlySelectedFieldsColumn;
	private GameLogic gameLogic;
	
	/**
	 * Inicializálásért felelõs
	 * 
	 * @param numberOfPlayers Játékosok száma
	 * @param sizeOfTable Tábla mérete
	 */
	
	public GameController(int numberOfPlayers, String sizeOfTable) {
		this.view = new GameView();
		this.calculatedTableWitdh = GenerateFields.getTableSizeRelatedToNumberOfPlayers(numberOfPlayers, sizeOfTable);
		this.players = GeneratePlayers.createPlayers(numberOfPlayers, calculatedTableWitdh);
		this.gameLogic = new GameLogic(view, calculatedTableWitdh, players);
		this.fields = GenerateFields.generate(numberOfPlayers, calculatedTableWitdh, players, view.getGameTablePanel());

		generateListeners();
	}
	
	
	/**
	 * Eseménykezelés inicializálásáért felelõs metódus. Minden mezõre külön listenereket teszünk
	 * Kör átadása gombra is beállítjuk az eseménykezelést
	 */
		
	private void generateListeners() {
		for (int i = 0 ;i < calculatedTableWitdh; i++) {
			for (int j = 0; j < calculatedTableWitdh; j++) {
				fields[i][j].getView().putClientProperty("row", i);
				fields[i][j].getView().putClientProperty("column", j);
				fields[i][j].getView().addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) {
					}
					
					@Override
					public void mouseExited(MouseEvent arg0) {
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						FieldView view = (FieldView)arg0.getSource();
						int row = (Integer)view.getClientProperty("row");
						int column = (Integer)view.getClientProperty("column");
						FieldController fieldController = fields[row][column];
						
						if(fieldController.getModel().getOwnnerOfThisField().getColor() == PlayerColor.USER && fieldController.getModel().getNumberOfDices() > 1){
							removeSelectFromAllFields();
							fieldController.setSelected(true);
							currentlySelectedFieldsRow = row;
							currentlySelectedFieldsColumn = column;
						}
						else if(isAnyFieldSelected() && fieldController.getModel().getOwnnerOfThisField().getColor() != PlayerColor.USER) {
							if(isNeighBour(row, column, currentlySelectedFieldsRow, currentlySelectedFieldsColumn)){
								fieldController.setSelected(true);
								gameLogic.fight(fields[currentlySelectedFieldsRow][currentlySelectedFieldsColumn], fieldController);
								resetSelect();
							}
						}
					}
				});
			}
		}
		
		view.getEndTurnButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameLogic.addAndRandomizeNewDicesForPlayer(players.get(0), fields);
				gameLogic.computersAttack(fields, players);
			}
		});
	}
	
	/**
	 * Kiválasztás eltávolítása az összes mezõrõl
	 */
	
	private void removeSelectFromAllFields () {
		for(int i = 0; i < calculatedTableWitdh; i++) {
			for ( int j=0; j<calculatedTableWitdh; j++) {
				fields[i][j].setSelected(false);
			}
		}
	}
	
	/**
	 * 
	 * @return Van-e kiválasztva mezõ 
	 */
	
	private boolean isAnyFieldSelected() {
		for(int i = 0; i < calculatedTableWitdh; i++) {
			for ( int j=0; j<calculatedTableWitdh; j++) {
				if(fields[i][j].isSelected())
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param tryingRowToSelect Mezõ sora, amit ki szeretnénk választani a táblán (ahová kattintottunk)
	 * @param tryingColumnToSelect Mezõ oszlopa, amit ki szeretnénk választani a táblán (ahová kattintottunk)
	 * @param selectedRow Korábban kiválasztott mezõ sora a táblán
	 * @param selectedColumn Korábban kiválasztott mezõ oszlopa a táblán
	 * @return Szomszédos elemet akarunk-e támadni?
	 */
	
	private boolean isNeighBour(int tryingRowToSelect, int tryingColumnToSelect, int selectedRow, int selectedColumn) {
		return Math.abs(tryingRowToSelect-selectedRow) < 2 && Math.abs(tryingColumnToSelect - selectedColumn) <2; 
	}
	
	/**
	 * Kiválasztások törlése
	 */
	
	private void resetSelect() {
		currentlySelectedFieldsColumn = -1;
		currentlySelectedFieldsRow = -1;
		
		removeSelectFromAllFields();
	}
}
