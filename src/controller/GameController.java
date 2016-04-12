package controller;

import java.util.ArrayList;

import model.PlayerBean;
import view.GameView;
import workflow.GenerateFields;
import workflow.GeneratePlayers;

public class GameController {
	private int numberOfPlayers;
	private String sizeOfTable;
	int calculatedTableWitdh;
	private GameView view;
	private FieldController[][] fields;
	private ArrayList<PlayerBean> players;
	
	public GameController(int numberOfPlayers, String sizeOfTable) {
		this.numberOfPlayers = numberOfPlayers;
		this.sizeOfTable = sizeOfTable;
		this.view = new GameView();
		this.calculatedTableWitdh = GenerateFields.getTableSizeRelatedToNumberOfPlayers(numberOfPlayers, sizeOfTable);
		players = GeneratePlayers.createPlayers(numberOfPlayers, calculatedTableWitdh);
		
		generateFields();
	}
	
	private void generateFields () {
		fields = GenerateFields.generate(numberOfPlayers, calculatedTableWitdh, players, view);
	}
}
