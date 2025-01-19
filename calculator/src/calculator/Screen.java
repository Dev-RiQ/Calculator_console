package calculator;

public class Screen {
	private Screen() {
	}

	private static Screen instance;

	public static Screen getInstance() {
		if (instance == null)
			instance = new Screen();
		return instance;
	}

	public void show(String msg) {
		System.out.println();
		System.out.println(msg);
	}
}
