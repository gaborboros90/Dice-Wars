package workflow;

import java.util.Random;

public class UtilFunctions {
	/**
	 * Random egész szám generálása min és max között
	 * 
	 * @param min Alsó határ
	 * @param max Felsõ határ
	 * @return Random szám felsõ és alsó között
	 */
	
	public static int generateIntBetweenRange(int min, int max) {
		if(max < min) {
			max = min;
		}
		
		Random random = new Random();
		
		return random.nextInt(max - min + 1) + min;
	}
}
