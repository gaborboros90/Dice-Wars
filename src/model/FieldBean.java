package model;

/**
 * 
 * FielBean oszt�ly a mez�k adatainak t�rol�s�ra
 */

public class FieldBean {
	private int numberOfDices;
	private PlayerBean ownnerOfThisField;
	
	/**
	 * 
	 * @param numberOfDices Kock�k sz�ma a mez�n
	 * @param ownnerOfThisField Mez� "tulajdonosa", aki egy j�t�kos lesz
	 */
	
	public FieldBean(int numberOfDices, PlayerBean ownnerOfThisField) {
		super();
		this.numberOfDices = numberOfDices;
		this.ownnerOfThisField = ownnerOfThisField;
	}
	
	/**
	 * 
	 * @return Mez�n l�v� kock�k lek�rdez�se
	 */
	
	public int getNumberOfDices() {
		return numberOfDices;
	}
	
	/**
	 * Mez�n l�v� kock�k be�ll�t�sa
	 * @param numberOfDices Kock�k sz�ma
	 */
	
	public void setNumberOfDices(int numberOfDices) {
		this.numberOfDices = numberOfDices;
	}
	
	/**
	 * 
	 * @return Mez�n l�v� J�t�kos lek�rdez�se
	 */
	
	public PlayerBean getOwnnerOfThisField() {
		return ownnerOfThisField;
	}
	
	/**
	 * 
	 * @param ownnerOfThisField Mez� tulajdonosa (j�t�kos)
	 */
	
	public void setOwnnerOfThisField(PlayerBean ownnerOfThisField) {
		this.ownnerOfThisField = ownnerOfThisField;
	}
	/**
	 * Kock�k sz�m�nak n�vel�se
	 * @param dices Kock�k sz�ma
	 */
	
	public void addDices(int dices) {
		this.numberOfDices += dices;
	}
	
	/**
	 * Kock�k sz�m�nak cs�kkent�se a mez�n
	 * @param dices Kock�k sz�ma
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
