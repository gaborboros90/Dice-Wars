package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * Oszt�ly amely a j�t�k Panelj�t fogja �ssze
 * Tartalmazza a mez�ket �s a j�t�k �ll�s�hoz sz�ks�ges sz�vegmez�ket, illetve
 * a k�r tov�bbad�sa gombot
 *
 */

public class GameView extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel gameTable;
	private JPanel footer;
	private JButton endTurnButton;
	private JLabel attackerScore;
	private JLabel defenderScore;
	
	/**
	 * 
	 * @return V�dekez� pontsz�m�nak labelj�t k�ri le
	 */
	
	public JLabel getDefenderScore() {
		return defenderScore;
	}
	
	/**
	 * Be�ll�tja a v�dekez� j�t�kos pontsz�m�t a mez�n (JLAbel-en)
	 * @param score V�dekez� pontsz�ma
	 */

	public void setDefenderScore(String score) {
		this.defenderScore.setText(score);
	}
	
	/**
	 * @return T�mad� pontsz�m�nak labelj�t adja vissza
	 */

	public JLabel getAttackerScore() {
		return attackerScore;
	}
	
	/**
	 * 
	 * @param score T�mad� pontsz�m�t �ll�tja be
	 */
	
	public void setAttackerScore(String score) {
		this.attackerScore.setText(score);
	}
	
	public GameView() {	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		gameTable = new JPanel(new GridBagLayout());
		footer = new JPanel(new FlowLayout());
		gameTable.setPreferredSize(new Dimension(600,550));
		endTurnButton = new JButton("Next Turn");
		
		
		add(gameTable);
		attackerScore = new JLabel("Attacker: ");
		defenderScore = new JLabel("Defender: ");
		footer.add(attackerScore);
		footer.add(defenderScore);
		footer.add(endTurnButton);
		add(footer);
		ApplicationSingletonFrame.instance().add(this);
	}
	
	/**
	 * 
	 * @return Mez� tov�bbad�sa gomb lek�r�se (referencia k�r�se)
	 */
	
	public JButton getEndTurnButton() {
		return endTurnButton;
	}
	
	/**
	 * 
	 * @param endTurnButton Gomb be�ll�t�sa
	 */

	public void setEndTurnButton(JButton endTurnButton) {
		this.endTurnButton = endTurnButton;
	}
	
	/**
	 * 
	 * @return J�t�k t�bl�j�nak panelj�t adja vissza
	 */
	
	public JPanel getGameTablePanel() {
		return this.gameTable;
	}
	
	/**
	 * V�ge van a j�t�knak, k�z�lj�k ezt a felhaszn�l�val is
	 * @param winnerPlayer Gy�ztes j�t�kos neve (sz�n�vel azonos�tott n�v->PlayerColor enumb�l)
	 */
	
	public void showGameOverText(String winnerPlayer) {
		JPanel framePanel = (JPanel)ApplicationSingletonFrame.instance().getContentPane();
		framePanel.remove(this);
		framePanel.revalidate();
		framePanel.repaint();
		
		JPanel panel = new JPanel(new GridBagLayout());
		JLabel gameOverLabel = new JLabel("Game Over!!! "+winnerPlayer+" has won the game");
		gameOverLabel.setFont(new Font("Serif",Font.BOLD,22));
		panel.add(gameOverLabel);
		framePanel.add(panel);
	}
}
