package view;

import javax.swing.JFrame;

public class ApplicationSingletonFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static ApplicationSingletonFrame instance = null;
	
	private ApplicationSingletonFrame() {
	}
	
	static public ApplicationSingletonFrame instance () {
		if( instance == null ) {
			instance = new ApplicationSingletonFrame();
		}
		
		return instance;
	}
	
	public void showApplicationFrame() {
		setSize(600, 600);
		setTitle("Dice Wars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void finalize() {
		instance = null;
	}
}
