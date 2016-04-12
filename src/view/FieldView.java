package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PlayerColor;

public class FieldView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel diceNumberText;

	public FieldView() {
		setLayout(new GridBagLayout());
		diceNumberText = new JLabel();
		diceNumberText.setFont(new Font("Serif",Font.BOLD,24));
		diceNumberText.setIcon(new ImageIcon(this.getClass().getResource("../images/dice_32.png")));
		add(diceNumberText);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void setDiceNumberText(int diceNumber) {
		diceNumberText.setText(Integer.toString(diceNumber));
	}
	
	public void setBackgroundColor(PlayerColor COLOR) {
		switch (COLOR) {
		case USER:
			setBackground(Color.GRAY);
			break;
			
		case RED:
			setBackground(Color.RED);
			break;
			
		case GREEN:
			setBackground(Color.GREEN);
			break;
			
		case BLUE:
			setBackground(Color.BLUE);
			break;
	
		case YELLOW:
			setBackground(Color.YELLOW);
			break;

		default:
			break;
		}
	}
}
