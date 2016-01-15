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
	
	// wyciaganie product quantity ze storage
	
	public static List<ProductQuantity> getProductQuantites() {
		List<ProductQuantity> productQuantities = new ArrayList<ProductQuantity>();
		List<Product> products = new ArrayList<Product>();
		products = getProducts();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result;
		
		String query = "SELECT * FROM storage";
		
		try {
			connection = dbConnection();
			statement = (Statement) connection.createStatement();
			result = (ResultSet)statement.executeQuery(query);
			while(result.next()) {
				Integer id = result.getInt("product_id");
			    int quantity = result.getInt("quantity");
			    
		    	ProductQuantity pq = new ProductQuantity();
			    pq.setQuantity(quantity);
			    for(int i=0; i<products.size(); i++) {
			    	if(products.get(i).getId() == id) {
			    		pq.setProduct(products.get(i));
			    		break;
			    	}
			    }
			    productQuantities.add(pq);
    
			}
		} catch(SQLException sqle) {
			System.out.println("DB error: getProducts");
			sqle.printStackTrace();
		}
		
		return productQuantities;
	}
	
	// -- wyciaganie przepisow
	public static List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result;
		
		String query = "SELECT * FROM meals";
		
		try {
			connection = dbConnection();
			statement = (Statement) connection.createStatement();
			result = (ResultSet)statement.executeQuery(query);
			while(result.next()) {
				String name = result.getString("title");
			    Integer calorificValue = result.getInt("calorificValue");
			    String description = result.getString("meal_id");
			    
		    	Recipe r = new Recipe();
			    r.setName(name);
			    r.setCalorificValue(calorificValue);
			    r.setDescription(description);
			    recipes.add(r);  
			}
		} catch(SQLException sqle) {
			System.out.println("DB error: getProducts");
			sqle.printStackTrace();
		}
		
		
		return recipes;
	}
	
	// wyciaganie skladnikow
	
	
	
	// -- dodawanie przepisu do bazy
	
	public static void uploadNewRecipe(Recipe r) {
		Connection connection = null;
		Statement statement = null;
		String mealId = ListsOperations.generateMealId();
		
		// dodawanie do tabeli meals
		String query = "insert into meals values ('" + mealId + "', '" + r.getName()
						+ "', " + r.getCalorificValue() + ", 0)";
		System.out.println(query);
				
		try {
			connection = dbConnection();
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
		 // dodawanie powiazan posilek - cecha
		 for(int i=0; i<r.getMealFeatures().size(); i++) {
			 int fid = new MealFeature().getIdByName(r.getMealFeatures().get(i).getName());
			 
			 query = "insert into meal_features_bond values ('" + mealId + "', " + fid + ")";
			 System.out.println(query);
			 
			 try {
					connection = dbConnection();
					statement = (Statement) connection.createStatement();
					statement.executeUpdate(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 }
		 
		 // dodawania powiazan posilek - produkt
		 for(int i=0; i<r.getProducts().size(); i++) {
			 int pid = new ListsOperations().getProductIdByName(r.getProducts().get(i).getProduct().getName());			 
			 query = "insert into ingredients_lists values ('" + mealId + "', " + pid + ")"; 
			 System.out.println(query);
			 
			 try {
					connection = dbConnection();
					statement = (Statement) connection.createStatement();
					statement.executeUpdate(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 }
	}

}
