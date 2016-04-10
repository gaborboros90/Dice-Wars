package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.security.acl.Owner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSettingsSingletonPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GameSettingsSingletonPanel instance = null;
	private LayoutManager gameSettingsLayout;
	private JLabel playersNumberText;
	private JLabel appOwnerText;
	private JButton[] settingsButtons;
	private JPanel buttonContainer;
	
	private ApplicationSingletonFrame frameInstance;
	
	private GameSettingsSingletonPanel() {
		gameSettingsLayout = new GridLayout(3,1, 20,20);
		playersNumberText = new JLabel("SZTE Dice Wars", JLabel.CENTER);
		playersNumberText.setFont(new Font("Serif",Font.BOLD,22));
		appOwnerText = new JLabel("Tamás Kapitány, SZTE 2016", JLabel.CENTER);
		settingsButtons = new JButton[4];
		
		buttonContainer = new JPanel();
	}
	
	public static GameSettingsSingletonPanel instance () {
		if (instance == null) {
			instance = new GameSettingsSingletonPanel();
		}
		
		return instance;
	}
	
	private void initSettingsPanel () {
		setLayout(gameSettingsLayout);
		buttonContainer.setLayout(new FlowLayout());
		
		for(int i = 0; i <= 3 ; i++) {
			settingsButtons[i] = new JButton(i+2+" players");
			settingsButtons[i].setVerticalAlignment(JButton.CENTER);
			buttonContainer.add(settingsButtons[i]);
		}
		
		add(playersNumberText);
		add(buttonContainer);
		add(appOwnerText);
	}
	
	public void startApplicationSettings () {
		initSettingsPanel();
		
		frameInstance = ApplicationSingletonFrame.instance();
		frameInstance.showApplicationFrame();
		frameInstance.add(this);
	}
	
	public void finalize() {
		instance = null;
	}
}
