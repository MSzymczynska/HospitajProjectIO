package projekt;

public class Functions {

	public static String formatString(String ourString) {
		int result = 0;
		String newString = "<html>";
		int flag = 0;
		for (int i = 0; i < ourString.length(); i++) {
			result++;
			newString += ourString.charAt(i);
			if (result == 30 || flag == 1) {
				flag = 1;
				if (ourString.charAt(i) == ' ') {
					newString += "<br>";
					result = 0;
					flag = 0;
				}
			}
		}
		newString += "</html>";
		return newString;
	}
}
