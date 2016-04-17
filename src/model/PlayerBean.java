package model;

/**
 * J�t�kos oszt�ly. Minden j�t�kost ezen oszt�ly p�ld�nyaival
 * reprezent�lunk j�t�k k�zben
 */

public class PlayerBean {
	private PlayerColor color;
	private int numberOfTerritories;
	private int numberOfDices;
	
	/**
	 * 
	 * @param color J�t�kos sz�ne (ezzel tudjuk k�s�bb szinezni a hozz� tartoz� mez�ket)
	 * @param numberOfTerritories Ter�leteinek sz�ma
	 * @param numberOfDices Kock�inak sz�ma
	 */
	
	public PlayerBean(PlayerColor color, int numberOfTerritories, int numberOfDices) {
		super();
		this.color = color;
		this.numberOfTerritories = numberOfTerritories;
		this.numberOfDices = numberOfDices;
	}
	
	/**
	 * 
	 * @return J�t�kos sz�n�nek lek�rdez�se
	 */

	public PlayerColor getColor() {
		return color;
	}
	
	/**
	 * J�t�kos sz�n�nek be�ll�t�sa
	 * @param color J�t�kos sz�ne
	 */

	public void setColor(PlayerColor color) {
		this.color = color;
	}
	
	/**
	 * 
	 * @return ter�leteinek sz�ma
	 */

	public int getNumberOfTerritories() {
		return numberOfTerritories;
	}

	/**
	 * Ter�leteinek sz�m�nak be�ll�t�sa
	 * @param numberOfTerritories ter�let sz�ma
	 */
	
	public void setNumberOfTerritories(int numberOfTerritories) {
		this.numberOfTerritories = numberOfTerritories;
	}
	
	/**
	 * 
	 * @return Kock�k sz�ma
	 */

	public int getNumberOfDices() {
		return numberOfDices;
	}

	/**
	 * Kock�k sz�m�nak be�ll�t�sa
	 * @param numberOfDices Kock�k sz�ma
	 */
	public void setNumberOfDices(int numberOfDices) {
		this.numberOfDices = numberOfDices;
	}
	
	/**
	 * N�vellj�k meg a jelenlegi kock�k sz�m�t
	 * @param dices kocka sz�ma
	 */
	
	public void addDices(int dices) {
		this.numberOfDices += dices;
	}
	
	/**
	 * Cs�kkents�k a jelenlegi kock�k sz�m�t
	 * @param dices Kocka sz�ma
	 */
	
	public void removeDices(int dices) {
		this.numberOfDices -= dices;
	}
	
	/**
	 * Ter�leteinek sz�m�nak n�vel�se
	 */
	
	public void increaseTerritoriesNumber() {
		this.numberOfTerritories += 1;
	}
	
	/**
	 * Ter�leteinek sz�m�nak cs�kkent�se
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
