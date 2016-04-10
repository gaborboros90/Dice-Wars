package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
	
	public void startApplication() {
		setSize(600, 600);
		setTitle("Dice Wars");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void finalize() {
		instance = null;
	}
}
