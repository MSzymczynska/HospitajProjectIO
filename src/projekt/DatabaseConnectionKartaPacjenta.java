package projekt;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;


public class DatabaseConnectionKartaPacjenta {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/kartaPacjenta";
	private static final String DB_USER = "lekarz";
	private static final String DB_PASS = "lekarz";

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

	public static void createPatient(String firstName, String lastName) {
		Connection dbConnection = null;
		Statement statement = null;
		Statement statement2 = null;

		String query = "INSERT INTO PATIENTS (FIRSTNAME, LASTNAME) VALUES" + "('" + firstName + "','" + lastName + "')";
		String query2 = "INSERT INTO PATIENTSCARDS (IDPATIENT) VALUES ((SELECT IDPATIENT FROM PATIENTS WHERE FIRSTNAME='"
				+firstName+"' AND LASTNAME='"+lastName+"'))";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			statement.executeUpdate(query);
			statement2 = (Statement) dbConnection.createStatement();
			statement2.executeUpdate(query2);
			System.out.println("Dodano nowego pacjenta oraz jego kartê.");
		} catch (SQLException e) {
			System.out.println("B³ad podczas dodawania pacjenta.");
			e.printStackTrace();
		}
	}

	public static boolean checkExistUser(String firstName, String lastName) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT FIRSTNAME FROM PATIENTS WHERE FIRSTNAME ='" + firstName + "' AND LASTNAME='" + lastName + "'";

		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while (result.next()) {
				String first = result.getString("firstName");
				System.out.println(first);
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Wylapalem na metodzie CheckExistUser.");
			return false;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
	
	public static List<Interview> fillInterviews(String firstName, String lastName){
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Interview> list = new ArrayList<Interview>();

		String query = "SELECT I.interviewsDate, D.doctorsfirstName, D.doctorsLastName"+
					" FROM INTERVIEWS I, PATIENTSCARDS PC, PATIENTS P, DOCTORS D"+
					" WHERE I.IDPATIENTSCARD=PC.IDPATIENTSCARD"+
					" AND PC.IDPATIENT=P.IDPATIENT AND P.FIRSTNAME='"+firstName+"'"+
					" AND P.LASTNAME='"+lastName+"' AND I.IDDOCTOR=D.IDDOCTOR";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while(result.next()){
				Date interviewDate = result.getDate("interviewsDate");
				String doctorsFirstName = result.getString("doctorsFirstName");
				String doctorsLastName = result.getString("doctorsLastName");
				String information = "x"; /////////////////////////////FIXING
				list.add(new Interview(interviewDate,doctorsFirstName,doctorsLastName,information));
			}
		} catch (SQLException e) {
			System.out.println("Error - fillInterviews.");
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Appointment> fillAppointments(String firstName, String lastName){
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Appointment> list = new ArrayList<Appointment>();

		String query = "SELECT A.appointmentsDate, D.doctorsfirstName, D.doctorsLastName, D.doctorsSpecialization"+
				" FROM APPOINTMENTS A, PATIENTSCARDS PC, PATIENTS P, DOCTORS D"+
				" WHERE A.IDPATIENTSCARD=PC.IDPATIENTSCARD"+
				" AND PC.IDPATIENT=P.IDPATIENT AND P.FIRSTNAME='"+
				firstName+"' AND P.LASTNAME='"+lastName+"' AND A.IDDOCTOR=D.IDDOCTOR";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while(result.next()){
				Date appointmentDate = result.getDate("appointmentsDate");
				String doctorsFirstName = result.getString("doctorsFirstName");
				String doctorsLastName = result.getString("doctorsLastName");
				String doctorsSpecialization = result.getString("doctorsSpecialization");
				list.add(new Appointment(appointmentDate,doctorsFirstName,doctorsLastName,doctorsSpecialization));
			}
		} catch (SQLException e) {
			System.out.println("Error - fillAppointments.");
			e.printStackTrace();
		}
		return list;
	}
}
