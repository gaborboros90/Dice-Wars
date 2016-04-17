package model;

/**
 * Játékos osztály. Minden játékost ezen osztály példányaival
 * reprezentálunk játék közben
 */

public class PlayerBean {
	private PlayerColor color;
	private int numberOfTerritories;
	private int numberOfDices;
	
	/**
	 * 
	 * @param color Játékos színe (ezzel tudjuk késõbb szinezni a hozzá tartozó mezõket)
	 * @param numberOfTerritories Területeinek száma
	 * @param numberOfDices Kockáinak száma
	 */
	
	public PlayerBean(PlayerColor color, int numberOfTerritories, int numberOfDices) {
		super();
		this.color = color;
		this.numberOfTerritories = numberOfTerritories;
		this.numberOfDices = numberOfDices;
	}
	
	/**
	 * 
	 * @return Játékos színének lekérdezése
	 */

	public PlayerColor getColor() {
		return color;
	}
	
	/**
	 * Játékos színének beállítása
	 * @param color Játékos színe
	 */

	public void setColor(PlayerColor color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return területeinek száma
	 */

	public int getNumberOfTerritories() {
		return numberOfTerritories;
	}

	/**
	 * Területeinek számának beállítása
	 * @param numberOfTerritories terület száma
	 */
	
	public void setNumberOfTerritories(int numberOfTerritories) {
		this.numberOfTerritories = numberOfTerritories;
	}
	
	/**
	 * 
	 * @return Kockák száma
	 */

	public int getNumberOfDices() {
		return numberOfDices;
	}

	/**
	 * Kockák számának beállítása
	 * @param numberOfDices Kockák száma
	 */
	public void setNumberOfDices(int numberOfDices) {
		this.numberOfDices = numberOfDices;
	}
	
	/**
	 * Növelljük meg a jelenlegi kockák számát
	 * @param dices kocka száma
	 */
	
	public void addDices(int dices) {
		this.numberOfDices += dices;
	}
	
	/**
	 * Csökkentsük a jelenlegi kockák számát
	 * @param dices Kocka száma
	 */
	
	public void removeDices(int dices) {
		this.numberOfDices -= dices;
	}
	
	/**
	 * Területeinek számának növelése
	 */
	
	public void increaseTerritoriesNumber() {
		this.numberOfTerritories += 1;
	}
	
	/**
	 * Területeinek számának csökkentése
	 */
	
	public void decreaseTerritoriesNumber() {
		this.numberOfTerritories -= 1;
	}

	@Override
	public String toString() {
		return "Player [color=" + color + ", numberOfTerritories="
				+ numberOfTerritories + ", numberOfDices=" + numberOfDices
				+ "]";
	}
}
