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
 * Oszt�ly a mez�k GUI komponenseihez
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
	 * Kock�k sz�m�nak megjelen�t�se a mez�n (GUI-n)
	 * @param diceNumber Kock�k sz�ma
	 */
	
	public void setDiceNumberText(int diceNumber) {
		diceNumberText.setText(Integer.toString(diceNumber));
	}
	
	/**
	 * Mez� sz�nez�se a j�t�kos sz�ne alapj�n
	 * @param COLOR J�t�kos sz�ne
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
	 * H�tt�rsz�n vissza�ll�t�sa alap�rtelmezett sz�nre
	 */
	
	public void resetBackgroundColor() {
		setBackground(defaultColor);
	}
	
	/**
	 * H�tt�rsz�n be�ll�t�sa, ha kiv�lasztottuk a mez�t (t�madni szeretn�nk ezzel a mez�vel)
	 */
	
	public void highlightBackgroundColorForSelect() {
		setBackground(Color.BLACK);
	}
}
