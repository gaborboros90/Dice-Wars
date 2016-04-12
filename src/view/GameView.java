package view;

import javax.swing.JPanel;

public class GameView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameView() {	
		ApplicationSingletonFrame.instance().add(this);
	}
}
