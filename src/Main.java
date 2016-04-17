import view.GameSettingsSingletonPanel;

/**
 * Main osztály. A program belépési pontja. 
 * 
 * @author Kapitány Tamás, EHA: 
 * Feladat megnevezése: Dice Wars
 *
 */

public class Main {

	/**
	 * @param args Parancssori agrumentumok
	 */
	public static void main(String[] args) {
		GameSettingsSingletonPanel settings = GameSettingsSingletonPanel.instance();
		settings.startApplicationSettings();
	}
}
