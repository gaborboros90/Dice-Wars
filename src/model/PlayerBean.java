package model;

public class PlayerBean {
	private PlayerColor color;
	private int numberOfTerritories;
	private int numberOfDices;
	
	public PlayerBean(PlayerColor color, int numberOfTerritories, int numberOfDices) {
		super();
		this.color = color;
		this.numberOfTerritories = numberOfTerritories;
		this.numberOfDices = numberOfDices;
	}

	public PlayerColor getColor() {
		return color;
	}

	public void setColor(PlayerColor color) {
		this.color = color;
	}

	public int getNumberOfTerritories() {
		return numberOfTerritories;
	}

	public void setNumberOfTerritories(int numberOfTerritories) {
		this.numberOfTerritories = numberOfTerritories;
	}

	public int getNumberOfDices() {
		return numberOfDices;
	}

	public void setNumberOfDices(int numberOfDices) {
		this.numberOfDices = numberOfDices;
	}

	@Override
	public String toString() {
		return "Player [color=" + color + ", numberOfTerritories="
				+ numberOfTerritories + ", numberOfDices=" + numberOfDices
				+ "]";
	}
}
