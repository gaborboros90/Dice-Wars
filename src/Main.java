import view.ApplicationSingletonFrame;
import view.GameSettingsSingletonPanel;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameSettingsSingletonPanel settings = GameSettingsSingletonPanel.instance();
		settings.startApplicationSettings();
	}
}
