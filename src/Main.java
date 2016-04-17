import view.GameSettingsSingletonPanel;

/**
 * Main oszt�ly. A program bel�p�si pontja. 
 * 
 * @author Kapit�ny Tam�s, EHA: 
 * Feladat megnevez�se: Dice Wars
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
