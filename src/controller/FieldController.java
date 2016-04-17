package controller;

import model.FieldBean;
import model.PlayerColor;
import view.FieldView;

/**
 * FieldController oszt�ly, mely a t�bl�n l�v� mez�k vez�rl�s��rt felel�s.
 * MVC architekt�r�ra �p�l az alkalmaz�s, �gy a FieldController-en kereszt�l �rhetj�k
 * a m�g�ttes adatokat (FieldBean) �s a felhaszn�l�i fel�letet is (FieldView)
 *
 */

public class FieldController {
	private FieldView view;
	private FieldBean model;
	private boolean isSelected = false;
	
	/**
	 * Konstruktor. Inicializ�l, �s fel�p�ti a GUI-t.
	 * 
	 * @param fieldView M�g�ttes GUI-val kapcsolatos dolgok�rt felel�s
	 * @param model M�g�ttes adatok a mez�h�z
	 */
	
	public FieldController(FieldView fieldView, FieldBean model) {
		super();
		this.view = fieldView;
		this.model = model;
		
		this.initGUI();
	}
	
	/**
	 * Mez� GUI fel�p�t�se a feladata
	 */
	
	private void initGUI() {
		setBackgroundColor(model.getOwnnerOfThisField().getColor());
		setDiceNumberText(model.getNumberOfDices());
	}

	/**
	 * 
	 * @return Mez� n�zet visszada�s
	 */
	
	public FieldView getView() {
		return view;
	}

	/**
	 * 
	 * @param view N�zet be�ll�t�sa
	 */
	
	public void setView(FieldView view) {
		this.view = view;
	}
	
	/**
	 * 
	 * @return M�g�ttes adatok visszaad�sa
	 */

	public FieldBean getModel() {
		return model;
	}
	
	/**
	 * 
	 * @param model Modell be�ll�t�sa
	 */

	public void setModel(FieldBean model) {
		this.model = model;
	}
	
	/**
	 * M�g�ttes sz�nek be�ll�t�sa
	 * 
	 * @param COLOR Milyen sz�n� felhaszn�l�
	 */
	
	public void setBackgroundColor(PlayerColor COLOR) {
		view.setBackgroundColor(COLOR);
	}
	
	/**
	 * Kock�k sz�m�nak be�ll�t�sa a mez�n
	 * @param diceNumber kock�k sz�ma
	 */
	
	public void setDiceNumberText(int diceNumber) {
		view.setDiceNumberText(diceNumber);
	}
	
	/**
	 * 
	 * @return Ki van-e v�lasztva �pp a mez�?
	 */

	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * Mez� kiv�laszt�s�ra haszn�lt met�dus
	 * 
	 * @param isSelected kiv�lasztunk
	 */
	
	public void setSelected(boolean isSelected) {
		if(!isSelected) {
			view.resetBackgroundColor();
		}
		else {
			view.highlightBackgroundColorForSelect();
		}
		
		this.isSelected = isSelected;
	}
	
	
}
