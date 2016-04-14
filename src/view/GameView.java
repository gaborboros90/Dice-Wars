package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameView extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel gameTable;
	private JPanel footer;
	private JButton endTurnButton;
	
	public GameView() {	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		gameTable = new JPanel(new GridBagLayout());
		footer = new JPanel();
		gameTable.setPreferredSize(new Dimension(600,560));
		footer.setPreferredSize(new Dimension(600, 40));
		endTurnButton = new JButton("Next Turn");
		
		
		add(gameTable);
		footer.add(endTurnButton);
		add(footer);
		ApplicationSingletonFrame.instance().add(this);
	}
	
	public JButton getEndTurnButton() {
		return endTurnButton;
	}

	public void setEndTurnButton(JButton endTurnButton) {
		this.endTurnButton = endTurnButton;
	}
	
	public JPanel getGameTablePanel() {
		return this.gameTable;
	}
}
