package view;

import javax.swing.JFrame;

/**
 * 
 * Egyke oszt�ly az alkalmaz�s keret�t tartalmazza. 
 * B�rhonnan el�rhet�
 */

public class ApplicationSingletonFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static ApplicationSingletonFrame instance = null;
	
	private ApplicationSingletonFrame() {
	}
	
	/**
	 * 
	 * @return Keret p�ld�ny�t visszaadja, ha l�tre lett hozva, ha m�g nincs l�trehozva l�trehoz �s visszaadja
	 */
	
	static public ApplicationSingletonFrame instance () {
		if( instance == null ) {
			instance = new ApplicationSingletonFrame();
		}
		
		return instance;
	}
	
	/**
	 * Keret megjelen�t�se, �s be�ll�t�sai
	 */
	
	public void showApplicationFrame() {
		setSize(600, 600);
		setTitle("Dice Wars");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void finalize() {
		instance = null;
	}
}
