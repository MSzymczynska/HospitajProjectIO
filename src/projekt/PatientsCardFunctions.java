package projekt;

public class PatientsCardFunctions {
	public static String formatujString(String naszString) {
		int result = 0;
		String nowyString = "<html>";
		int flaga = 0;
		for (int i = 0; i < naszString.length(); i++) {
			result++;
			nowyString += naszString.charAt(i);
			if (result == 30 || flaga == 1) {
				flaga = 1;
				if (naszString.charAt(i) == ' ') {
					nowyString += "<br>";
					result = 0;
					flaga = 0;
				}
			}
		}
		nowyString += "</html>";
		return nowyString;
	}
}
