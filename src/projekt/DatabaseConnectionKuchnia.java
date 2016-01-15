package projekt;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DatabaseConnectionKuchnia {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://s1.kolodziej.it:3306/Hospital";
	private static final String DB_USER = "hospital";
	private static final String DB_PASS = "c@2ea(*1FsE10cd91F7h";

	public static Connection dbConnection() {
		Connection dbConnection = null;

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			dbConnection = (Connection) DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASS);
			System.out.println("Zaczynam ³¹czyæ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dbConnection;
	}

}
