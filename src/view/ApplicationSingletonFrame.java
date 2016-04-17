package view;

import javax.swing.JFrame;

/**
 * 
 * Egyke osztály az alkalmazás keretét tartalmazza. 
 * Bárhonnan elérhetõ
 */

public class ApplicationSingletonFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static ApplicationSingletonFrame instance = null;
	
	private ApplicationSingletonFrame() {
	}
	
	/**
	 * 
	 * @return Keret példányát visszaadja, ha létre lett hozva, ha még nincs létrehozva létrehoz és visszaadja
	 */
	
	static public ApplicationSingletonFrame instance () {
		if( instance == null ) {
			instance = new ApplicationSingletonFrame();
		}
		
		return instance;
	}
	
	/**
	 * Keret megjelenítése, és beállításai
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
