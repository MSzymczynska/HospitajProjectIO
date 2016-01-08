/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Maciej
 */
public class StorageArchive {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://s1.kolodziej.it/Hospital";
	static final String USER = "hospital";
	static final String PASS = "c@2ea(*1FsE10cd91F7h";
	private StorageMainPanel smp;

	private List<ProductMovement> archive;

	public StorageArchive(StorageMainPanel smp) {
		this.smp = smp;
		this.archive = new ArrayList<>();
		this.getFromDB();
	}

	public boolean addToArchive(ProductMovement productMovement) {
		archive.add(productMovement);
		sendToDB();
		return true;
	}

	public List<ProductMovement> getArchive() {
		return archive;
	}

	public List<ProductMovement> getArchive(String productName) {
		List<ProductMovement> result = new ArrayList<>();

		for (ProductMovement pm : archive) {
			if (pm.productQuantity.product.name.compareTo(productName) == 0) {
				result.add(pm);
			}
		}

		return result;
	}

	public List<ProductMovement> getArchive(Product product) {
		List<ProductMovement> result = new ArrayList<>();

		for (ProductMovement pm : archive) {
			if (pm.productQuantity.product.compareTo(product) == 0) {
				result.add(pm);
			}
		}

		return result;
	}

	public List<ProductMovement> getArchive(Date date) {
		List<ProductMovement> result = new ArrayList<>();

		for (ProductMovement pm : archive) {
			if (pm.date.compareTo(date) == 0) {
				result.add(pm);
			}
		}

		return result;
	}

	public boolean sendToDB() {
		System.out.println("a.sendToDB");

		Connection conn = null;
		PreparedStatement preparedStmt = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sqldel = "DELETE FROM storageArchive";
			stmt = conn.createStatement();
			stmt.executeUpdate(sqldel);

			String sql = "INSERT INTO storageArchive (product_id, quantity, datee, fromm, too, is_done) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE product_id=product_id";
			for (int i = 0; i < archive.size(); i++) {
				preparedStmt = conn.prepareStatement(sql);
				preparedStmt.setInt(1, archive.get(i).productQuantity.product.id);
				preparedStmt.setInt(2, archive.get(i).productQuantity.quantity);
				preparedStmt.setTimestamp(3, new Timestamp(archive.get(i).date.getTime()));
				preparedStmt.setString(4, ((ProductOrder) archive.get(i)).from);
				preparedStmt.setString(5, ((ProductOrder) archive.get(i)).to);
				preparedStmt.setByte(6, (byte) 1);
				preparedStmt.execute();
			}

			for (int i = 0; i < smp.ordersFromOutside.size(); i++) {
				preparedStmt = conn.prepareStatement(sql);
				preparedStmt.setInt(1, smp.ordersFromOutside.get(i).productQuantity.product.id);
				preparedStmt.setInt(2, smp.ordersFromOutside.get(i).productQuantity.quantity);
				preparedStmt.setTimestamp(3, new Timestamp(smp.ordersFromOutside.get(i).date.getTime()));
				preparedStmt.setString(4, ((ProductOrder) smp.ordersFromOutside.get(i)).from);
				preparedStmt.setString(5, ((ProductOrder) smp.ordersFromOutside.get(i)).to);
				preparedStmt.setByte(6, (byte) 0);
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
		System.out.println("a.getFromDB");
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT a.product_id, a.quantity, a.datee, a.fromm, a.too, a.is_done, p.title, p.producent, p.expiration_date FROM storageArchive a, products p WHERE a.product_id=p.product_id ORDER BY datee ASC";
			ResultSet rs = stmt.executeQuery(sql);

			smp.ordersFromOutside.clear();
			archive.clear();

			int tmp = 0;
			while (rs.next()) {
				int id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				Date datee = rs.getDate("datee");
				String fromm = rs.getString("fromm");
				String too = rs.getString("too");
				String title = rs.getString("title");
				String producent = rs.getString("producent");
				Date expDate = rs.getDate("expiration_date");
				byte isDone = rs.getByte("is_done");

				if (isDone == 1)
					// this.addToArchive(new ProductOrder(Integer.toString(tmp),
					// new ProductQuantity(new Product(id, title, producent,
					// expDate), quantity), datee, fromm,
					// too));

					archive.add(new ProductOrder(Integer.toString(tmp),
							new ProductQuantity(new Product(id, title, producent, expDate), quantity), datee, fromm,
							too));
				else
					smp.ordersFromOutside.add(new ProductOrder(Integer.toString(tmp),
							new ProductQuantity(new Product(id, title, producent, expDate), quantity), datee, fromm,
							too));

				tmp++;
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
		System.out.println("a.refresh");
		archive.clear();
		smp.ordersFromOutside.clear();
		getFromDB();
	}
}
