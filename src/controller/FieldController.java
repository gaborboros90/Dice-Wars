package controller;

import model.FieldBean;
import model.PlayerColor;
import view.FieldView;

public class FieldController {
	private FieldView view;
	private FieldBean model;
	
	public FieldController(FieldView fieldView, FieldBean model) {
		super();
		this.view = fieldView;
		this.model = model;
		
		this.initGUI();
	}
	
	private void initGUI() {
		setBackgroundColor(model.getOwnnerOfThisField().getColor());
		setDiceNumberText(model.getNumberOfDices());
	}

	public FieldView getView() {
		return view;
	}

	public void setView(FieldView view) {
		this.view = view;
	}

	public FieldBean getModel() {
		return model;
	}

	public void setModel(FieldBean model) {
		this.model = model;
	}
	
	public void setBackgroundColor(PlayerColor COLOR) {
		view.setBackgroundColor(COLOR);
	}
	
	public void setDiceNumberText(int diceNumber) {
		view.setDiceNumberText(diceNumber);
	}
}
