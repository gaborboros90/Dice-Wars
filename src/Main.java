import view.ApplicationSingletonFrame;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationSingletonFrame applicationInstanceFrame = ApplicationSingletonFrame.instance();
		applicationInstanceFrame.startApplication();
	}
}
