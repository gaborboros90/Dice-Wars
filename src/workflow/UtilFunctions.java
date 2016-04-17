package workflow;

import java.util.Random;

public class UtilFunctions {
	/**
	 * Random eg�sz sz�m gener�l�sa min �s max k�z�tt
	 * 
	 * @param min Als� hat�r
	 * @param max Fels� hat�r
	 * @return Random sz�m fels� �s als� k�z�tt
	 */
	
	public static int generateIntBetweenRange(int min, int max) {
		if(max < min) {
			max = min;
		}
		
		Random random = new Random();
		
		return random.nextInt(max - min + 1) + min;
	}
}
