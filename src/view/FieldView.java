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
	private Color defaultColor;

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
	
	public void resetBackgroundColor() {
		setBackground(defaultColor);
	}
	
	public void highlightBackgroundColorForSelect() {
		setBackground(Color.BLACK);
	}
}
