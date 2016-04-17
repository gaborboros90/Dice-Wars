package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PlayerColor;

/**
 * 
 * Osztály a mezõk GUI komponenseihez
 *
 */

public class FieldView extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel diceNumberText;
	private Color defaultColor;

	public FieldView() {
		setLayout(new GridBagLayout());
		diceNumberText = new JLabel();
		diceNumberText.setFont(new Font("Serif",Font.BOLD,24));
		diceNumberText.setIcon(new ImageIcon(this.getClass().getResource("../images/dice_32.png")));
		add(diceNumberText);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	/**
	 * Kockák számának megjelenítése a mezõn (GUI-n)
	 * @param diceNumber Kockák száma
	 */
	
	public void setDiceNumberText(int diceNumber) {
		diceNumberText.setText(Integer.toString(diceNumber));
	}
	
	/**
	 * Mezõ színezése a játékos színe alapján
	 * @param COLOR Játékos színe
	 */
	
	public void setBackgroundColor(PlayerColor COLOR) {
		switch (COLOR) {
		case USER:
			setBackground(Color.GRAY);
			defaultColor = Color.GRAY;
			break;
			
		case RED:
			setBackground(Color.RED);
			defaultColor = Color.RED;
			break;
			
		case GREEN:
			setBackground(Color.GREEN);
			defaultColor = Color.GREEN;
			break;
			
		case BLUE:
			setBackground(Color.BLUE);
			defaultColor = Color.BLUE;
			break;
	
		case YELLOW:
			setBackground(Color.YELLOW);
			defaultColor = Color.YELLOW;
			break;

		default:
			break;
		}
	}
	
	/**
	 * Háttérszín visszaállítása alapértelmezett színre
	 */
	
	public void resetBackgroundColor() {
		setBackground(defaultColor);
	}
	
	/**
	 * Háttérszín beállítása, ha kiválasztottuk a mezõt (támadni szeretnénk ezzel a mezõvel)
	 */
	
	public void highlightBackgroundColorForSelect() {
		setBackground(Color.BLACK);
	}
}
