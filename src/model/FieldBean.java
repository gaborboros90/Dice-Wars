package model;

/**
 * 
 * FielBean osztály a mezõk adatainak tárolására
 */

public class FieldBean {
	private int numberOfDices;
	private PlayerBean ownnerOfThisField;
	
	/**
	 * 
	 * @param numberOfDices Kockák száma a mezõn
	 * @param ownnerOfThisField Mezõ "tulajdonosa", aki egy játékos lesz
	 */
	
	public FieldBean(int numberOfDices, PlayerBean ownnerOfThisField) {
		super();
		this.numberOfDices = numberOfDices;
		this.ownnerOfThisField = ownnerOfThisField;
	}
	
	/**
	 * 
	 * @return Mezõn lévõ kockák lekérdezése
	 */
	
	public int getNumberOfDices() {
		return numberOfDices;
	}
	
	/**
	 * Mezõn lévõ kockák beállítása
	 * @param numberOfDices Kockák száma
	 */
	
	public void setNumberOfDices(int numberOfDices) {
		this.numberOfDices = numberOfDices;
	}
	
	/**
	 * 
	 * @return Mezõn lévõ Játékos lekérdezése
	 */
	
	public PlayerBean getOwnnerOfThisField() {
		return ownnerOfThisField;
	}
	
	/**
	 * 
	 * @param ownnerOfThisField Mezõ tulajdonosa (játékos)
	 */
	
	public void setOwnnerOfThisField(PlayerBean ownnerOfThisField) {
		this.ownnerOfThisField = ownnerOfThisField;
	}
	/**
	 * Kockák számának növelése
	 * @param dices Kockák száma
	 */
	
	public void addDices(int dices) {
		this.numberOfDices += dices;
	}
	
	/**
	 * Kockák számának csökkentése a mezõn
	 * @param dices Kockák száma
	 */
	
	public void removeDices(int dices) {
		this.numberOfDices -= dices;
	}
	
	@Override
	public String toString() {
		return "FieldBean [numberOfDices=" + numberOfDices
				+ ", ownnerOfThisField=" + ownnerOfThisField + "]";
	}	
}
