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
 * Osztály amely a játék Paneljét fogja össze
 * Tartalmazza a mezõket és a játék állásához szükséges szövegmezõket, illetve
 * a kör továbbadása gombot
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
	 * @return Védekezõ pontszámának labeljét kéri le
	 */
	
	public JLabel getDefenderScore() {
		return defenderScore;
	}
	
	/**
	 * Beállítja a védekezõ játékos pontszámát a mezõn (JLAbel-en)
	 * @param score Védekezõ pontszáma
	 */

	public void setDefenderScore(String score) {
		this.defenderScore.setText(score);
	}
	
	/**
	 * @return Támadó pontszámának labeljét adja vissza
	 */

	public JLabel getAttackerScore() {
		return attackerScore;
	}
	
	/**
	 * 
	 * @param score Támadó pontszámát állítja be
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
	 * @return Mezõ továbbadása gomb lekérése (referencia kérése)
	 */
	
	public JButton getEndTurnButton() {
		return endTurnButton;
	}
	
	/**
	 * 
	 * @param endTurnButton Gomb beállítása
	 */

	public void setEndTurnButton(JButton endTurnButton) {
		this.endTurnButton = endTurnButton;
	}
	
	/**
	 * 
	 * @return Játék táblájának paneljét adja vissza
	 */
	
	public JPanel getGameTablePanel() {
		return this.gameTable;
	}
	
	/**
	 * Vége van a játéknak, közöljük ezt a felhasználóval is
	 * @param winnerPlayer Gyõztes játékos neve (színével azonosított név->PlayerColor enumból)
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
