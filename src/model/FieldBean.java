package model;

public class FieldBean {
	private int numberOfDices;
	private PlayerBean ownnerOfThisField;
	
	public FieldBean(int numberOfDices, PlayerBean ownnerOfThisField) {
		super();
		this.numberOfDices = numberOfDices;
		this.ownnerOfThisField = ownnerOfThisField;
	}
	
	public int getNumberOfDices() {
		return numberOfDices;
	}
	public void setNumberOfDices(int numberOfDices) {
		this.numberOfDices = numberOfDices;
	}
	public PlayerBean getOwnnerOfThisField() {
		return ownnerOfThisField;
	}
	public void setOwnnerOfThisField(PlayerBean ownnerOfThisField) {
		this.ownnerOfThisField = ownnerOfThisField;
	}
	
	@Override
	public String toString() {
		return "FieldBean [numberOfDices=" + numberOfDices
				+ ", ownnerOfThisField=" + ownnerOfThisField + "]";
	}	
}
