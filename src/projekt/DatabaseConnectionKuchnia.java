package projekt;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dbConnection;
	}
	
	// wyciaganie produktow z bazy danych
	
	public static List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result;
		
		String query = "SELECT * FROM products";
		
		try {
			connection = dbConnection();
			statement = (Statement) connection.createStatement();
			result = (ResultSet)statement.executeQuery(query);
			while(result.next()) {
				Integer id = result.getInt("product_id");
			    String name = result.getString("title");
			    String producer = result.getString("producent");
			    Date expirationDate = result.getDate("expiration_date");
			    
			    // prosze bez komentarza ;P

		    	Product p = new Product();
			    p.setId(id);
			    p.setName(name);
			    p.setProducer(producer);
			    p.setExpirationDate(expirationDate);
			    products.add(p);
    
			}
		} catch(SQLException sqle) {
			System.out.println("DB error: getProducts");
			sqle.printStackTrace();
		}
		
		return products;
	}

}
