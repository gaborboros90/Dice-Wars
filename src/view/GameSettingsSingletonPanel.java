package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;

public class GameSettingsSingletonPanel extends JPanel {

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
	private JPanel tableSizeContainer;
	private JComboBox<String> tableSizeCb;
	private JLabel tableSizeLabel;
	private String[] tableSizesText = { "Small", "Medium", "Large"};
	
	private ApplicationSingletonFrame frameInstance;
	
	private GameSettingsSingletonPanel() {
		frameInstance = ApplicationSingletonFrame.instance();
		
		gameSettingsLayout = new GridLayout(4,1, 20,20);
		playersNumberText = new JLabel("SZTE Dice Wars",new ImageIcon(this.getClass().getResource("../images/diceIcon.png")), JLabel.CENTER);
		playersNumberText.setFont(new Font("Serif",Font.BOLD,22));
		playersNumberText.setHorizontalTextPosition(JLabel.LEADING);
		appOwnerText = new JLabel("Tamás Kapitány, SZTE 2016", JLabel.CENTER);
		settingsButtons = new JButton[4];
		tableSizeContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 180, 0));
		tableSizeCb = new JComboBox<String>(tableSizesText);
		tableSizeLabel = new JLabel("Choose table size: ");
		
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
			settingsButtons[i].setActionCommand(Integer.toString(i+2));
			settingsButtons[i].addActionListener(new ActionListener() {
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int numberOfPlayers = Integer.parseInt(e.getActionCommand());
					removePanel();
					new GameController(numberOfPlayers, (String)tableSizeCb.getSelectedItem());
				}
			});
			
			buttonContainer.add(settingsButtons[i]);
		}
		
		tableSizeContainer.add(tableSizeLabel);
		tableSizeContainer.add(tableSizeCb);
		
		add(playersNumberText);
		add(buttonContainer);
		add(tableSizeContainer);
		add(appOwnerText);
	}
	
	public void startApplicationSettings () {
		initSettingsPanel();
		
		frameInstance.showApplicationFrame();
		frameInstance.add(this);
	}
	
	private void removePanel() {
		JPanel framePanel = (JPanel)frameInstance.getContentPane();
		framePanel.remove(this);
		framePanel.revalidate();
		framePanel.repaint();
	}
	
	public void finalize() {
		instance = null;
	}
}
