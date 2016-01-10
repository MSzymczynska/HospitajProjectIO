/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Maciej
 */
public class Storage {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://s1.kolodziej.it/Hospital";
	static final String USER = "hospital";
	static final String PASS = "c@2ea(*1FsE10cd91F7h";

	private List<ProductQuantity> storage;

	public Storage() {
		this.storage = new ArrayList<>();
		getFromDB();
		//sendToDB();
	}

	public boolean addToStorage(ProductQuantity productQuantity) {
		boolean flag = false;

		if (this.getStorage(productQuantity.product.id).isEmpty()) {
			storage.add(productQuantity);
			flag = true;
		} else {
			for (ProductQuantity pq : storage) {
				if (pq.product.id.compareTo(productQuantity.product.id) == 0) {
					pq.quantity += productQuantity.quantity;
					flag = true;
				}
			}
		}
		if(flag)
			sendToDB();
		return flag;
	}

	public List<ProductQuantity> getStorage() {
		return storage;
	}

	public List<ProductQuantity> getStorage(Integer productID) {
		List<ProductQuantity> result = new ArrayList<>();

		for (ProductQuantity pq : storage) {
			if (pq.product.id.compareTo(productID) == 0) {
				result.add(pq);
			}
		}

		return result;
	}

	public List<ProductQuantity> getStorage(Product product) {
		List<ProductQuantity> result = new ArrayList<>();

		for (ProductQuantity pq : storage) {
			if (pq.product.compareTo(product) == 0) {
				result.add(pq);
			}
		}
		
		return result;
	}

	public boolean removeFromStorage(ProductQuantity productQuantity) {
		boolean flag = false;

		for (ProductQuantity pq : storage) {
			if (pq.product.compareTo(productQuantity.product) == 0) {
				pq.quantity -= productQuantity.quantity;
				flag = true;
			}
		}
		if(flag)
			sendToDB();
		return flag;
	}

	boolean sendToDB() {
		System.out.println("s.sendToDB");

		Connection conn = null;
		PreparedStatement preparedStmt = null;
	    Statement stmt = null;

	   try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			String sqldel = "DELETE FROM storage";
			stmt = conn.createStatement();
			stmt.executeUpdate(sqldel);
			
			String sql = "INSERT INTO storage (product_id, quantity) VALUES (?, ?)";
			for(int i=0; i<storage.size(); i++)
			{	
				preparedStmt = conn.prepareStatement(sql);
				preparedStmt.setInt(1, storage.get(i).product.id);
				preparedStmt.setInt(2, storage.get(i).quantity);
				preparedStmt.execute();
			}

			preparedStmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (preparedStmt != null)
					preparedStmt.close();
			} catch (SQLException se2) {
				return false;
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private boolean getFromDB() {
		System.out.println("s.getFromDB");

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT s.product_id, s.quantity, p.title, p.producent, p.expiration_date FROM storage s, products p WHERE s.product_id = p.product_id";
			ResultSet rs = stmt.executeQuery(sql);

			storage.clear();
			while (rs.next()) {
				int id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				String title = rs.getString("title");
				String producent = rs.getString("producent");
				Date expDate = rs.getDate("expiration_date");

				//this.addToStorage(new ProductQuantity(new Product(id, title, producent, expDate), quantity));
				storage.add(new ProductQuantity(new Product(id, title, producent, expDate), quantity));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				return false;
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public void refresh() {
		System.out.println("s.refresh");
		getFromDB();
	}
}
