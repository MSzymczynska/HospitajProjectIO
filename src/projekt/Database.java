package projekt;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Database {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/apteka";
	private static final String DB_USER = "lekarz";
	private static final String DB_PASS = "lekarz";
	private static String errorsName;

	public static Connection dbConnection() {
		Connection dbConnection = null;

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			dbConnection = (Connection) DriverManager.getConnection(
					DB_CONNECTION, DB_USER, DB_PASS);
			System.out.println("Zaczynam łączyć");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dbConnection;
	}
	
	public static void addMedicine(int id, String name) {
		Connection dbConnection = null;
		Statement statement = null;
		
		String adminQuery = "INSERT INTO Medicines (idMedicine, medicinesName) VALUES"
				+ "('"
				+ id
				+ "','"
				+ name
				+ "')";

		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			statement.executeUpdate(adminQuery);
			System.out.println("Dodano nowy lek.");
		} catch (SQLException e) {
			System.out.println("B�ad podczas dodawania leku");
			e.printStackTrace();
		}

	}
}