package zmobile.utils;

public class ConsoleColors {

	public static final String GREEN_CHECK = "✅";
	public static final String RED_X = "❌";
	public static final String YELLOW_WARNING = "⚠️";
	public static final String BLUE_INFO = "ℹ️";

	public static String success(String message) {
		return GREEN_CHECK + " " + message;
	}

	public static String error(String message) {
		return RED_X + " " + message;
	}

	public static String warning(String message) {
		return YELLOW_WARNING + " " + message;
	}

	public static String info(String message) {
		return BLUE_INFO + " " + message;
	}
}
