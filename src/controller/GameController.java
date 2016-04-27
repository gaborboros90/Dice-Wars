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
 * Ezt az osztályt a j�t�k j�t�k k�zbeni vez�rl�s�re haszn�lljuk
 * illetve itt helyezz�k el az esem�nykezel�ket is.
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
	 * Inicializ�l�s�rt felel�s
	 * 
	 * @param numberOfPlayers J�t�kosok sz�ma
	 * @param sizeOfTable T�bla m�rete
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
	 * Esem�nykezel�s inicializ�l�s��rt felel�s met�dus. Minden mez�re k�l�n listenereket tesz�nk
	 * K�r �tad�sa gombra is be�ll�tjuk az esem�nykezel�st
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
	 * Kiv�laszt�s elt�vol�t�sa az �sszes mez�r�l
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
	 * @return Van-e kiv�lasztva mez� 
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
	 * @param tryingRowToSelect Mez� sora, amit ki szeretn�nk v�lasztani a t�bl�n (ahov� kattintottunk)
	 * @param tryingColumnToSelect Mez� oszlopa, amit ki szeretn�nk v�lasztani a t�bl�n (ahov� kattintottunk)
	 * @param selectedRow Kor�bban kiv�lasztott mez� sora a t�bl�n
	 * @param selectedColumn Kor�bban kiv�lasztott mez� oszlopa a t�bl�n
	 * @return Szomsz�dos elemet akarunk-e t�madni?
	 */
	
	private boolean isNeighBour(int tryingRowToSelect, int tryingColumnToSelect, int selectedRow, int selectedColumn) {
		return Math.abs(tryingRowToSelect-selectedRow) < 2 && Math.abs(tryingColumnToSelect - selectedColumn) <2; 
	}
	
	/**
	 * Kiv�laszt�sok t�rl�se
	 */
	
	private void resetSelect() {
		currentlySelectedFieldsColumn = -1;
		currentlySelectedFieldsRow = -1;
		
		removeSelectFromAllFields();
	}
}
