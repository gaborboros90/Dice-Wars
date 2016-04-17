package controller;

import model.FieldBean;
import model.PlayerColor;
import view.FieldView;

/**
 * FieldController osztály, mely a táblán lévõ mezõk vezérléséért felelõs.
 * MVC architektúrára épül az alkalmazás, így a FieldController-en keresztül érhetjük
 * a mögöttes adatokat (FieldBean) és a felhasználói felületet is (FieldView)
 *
 */

public class FieldController {
	private FieldView view;
	private FieldBean model;
	private boolean isSelected = false;
	
	/**
	 * Konstruktor. Inicializál, és felépíti a GUI-t.
	 * 
	 * @param fieldView Mögöttes GUI-val kapcsolatos dolgokért felelõs
	 * @param model Mögöttes adatok a mezõhöz
	 */
	
	public FieldController(FieldView fieldView, FieldBean model) {
		super();
		this.view = fieldView;
		this.model = model;
		
		this.initGUI();
	}
	
	/**
	 * Mezõ GUI felépítése a feladata
	 */
	
	private void initGUI() {
		setBackgroundColor(model.getOwnnerOfThisField().getColor());
		setDiceNumberText(model.getNumberOfDices());
	}

	/**
	 * 
	 * @return Mezõ nézet visszadaás
	 */
	
	public FieldView getView() {
		return view;
	}

	/**
	 * 
	 * @param view Nézet beállítása
	 */
	
	public void setView(FieldView view) {
		this.view = view;
	}
	
	/**
	 * 
	 * @return Mögöttes adatok visszaadása
	 */

	public FieldBean getModel() {
		return model;
	}
	
	/**
	 * 
	 * @param model Modell beállítása
	 */

	public void setModel(FieldBean model) {
		this.model = model;
	}
	
	/**
	 * Mögöttes színek beállítása
	 * 
	 * @param COLOR Milyen színû felhasználó
	 */
	
	public void setBackgroundColor(PlayerColor COLOR) {
		view.setBackgroundColor(COLOR);
	}
	
	/**
	 * Kockák számának beállítása a mezõn
	 * @param diceNumber kockák száma
	 */
	
	public void setDiceNumberText(int diceNumber) {
		view.setDiceNumberText(diceNumber);
	}
	
	/**
	 * 
	 * @return Ki van-e választva épp a mezõ?
	 */

	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * Mezõ kiválasztására használt metódus
	 * 
	 * @param isSelected kiválasztunk
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
