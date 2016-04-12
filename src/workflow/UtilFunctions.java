package workflow;

import java.util.Random;

public class UtilFunctions {
	public static int generateIntBetweenRange(int min, int max) {
		if(max < min) {
			max = min;
		}
		
		Random random = new Random();
		
		return random.nextInt(max - min + 1) + min;
	}
}
