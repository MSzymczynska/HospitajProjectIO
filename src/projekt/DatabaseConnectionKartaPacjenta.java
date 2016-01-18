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

	public static void createPatient(String firstName, String lastName, String email, String phone_number, String daily_menu_id) {
		Connection dbConnection = null;
		Statement statement = null;
		Statement statement2 = null;

		String query = "INSERT INTO patients  VALUES((SELECT MAX(patient_id) from patient_cards)+1,'" + firstName + "','" + lastName + "','"+email+ "','"+phone_number+ "',"+daily_menu_id+")";
		String query2 = "INSERT INTO patient_cards VALUES ((SELECT patient_id FROM patients WHERE first_name='"
				+firstName+"' AND last_name='"+lastName+"'),(SELECT patient_id FROM patients WHERE first_name='"
				+firstName+"' AND last_name='"+lastName+"'),'M',90.00,90.00,55)";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Dodalem pacjenta ale nie karte");
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

		String query = "SELECT last_name FROM patients WHERE first_name ='" + firstName + "' AND last_name='" + lastName + "'";

		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while (result.next()) {
				String first = result.getString("last_name");
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
	
	public static boolean checkExistDoctor(String firstName, String lastName) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT doctorsLastName FROM doctors WHERE doctorsFirstName ='" + firstName + "' AND doctorsLastName='" + lastName + "'";

		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while (result.next()) {
				String first = result.getString("doctorsLastName");
				System.out.println(first);
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Wylapalem na metodzie CheckExistDoctor.");
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

		String query = "SELECT I.interviewsDate, D.doctorsfirstName, D.doctorsLastName, I.information"+
					" FROM interviews I, patient_cards PC, patients P, doctors D"+
					" WHERE I.idPatientsCard=PC.idPatientsCard"+
					" AND PC.patient_id=P.patient_id AND P.first_name='"+firstName+"'"+
					" AND P.last_name='"+lastName+"' AND I.idDoctor=D.idDoctor";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while(result.next()){
				Date interviewDate = result.getDate("interviewsDate");
				String doctorsFirstName = result.getString("doctorsFirstName");
				String doctorsLastName = result.getString("doctorsLastName");
				String information = result.getString("information");
				list.add(new Interview(interviewDate,doctorsFirstName,doctorsLastName,information));
			}
		} catch (SQLException e) {
			System.out.println("Error - fillInterviews.");
			e.printStackTrace();
		}
		return list;
	}
	
	

	public static List<Patient> fillPatients(){
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Patient> list = new ArrayList<Patient>();

		String query = "select first_name, last_name, patient_id "+
					" from patients";
					
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while(result.next()){
				String patient_id = result.getString("patient_id");
				String first_name = result.getString("first_name");
				String last_name = result.getString("last_name");
				
				list.add(new Patient(first_name, last_name, patient_id));
			}
		} catch (SQLException e) {
			System.out.println("Error - fillPatients.");
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static String getContact(String id){
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String contact=null;

		String query = "select email, phone_number "+
					" from patients where patient_id="+id+";";
					
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while(result.next()){
				String email = result.getString("email");
				String phone_number = result.getString("phone_number");
				
				contact="adres email: "+email+"     \r\n numer telefonu:"+ phone_number;
			}
		} catch (SQLException e) {
			System.out.println("Error - contact.");
			e.printStackTrace();
		}
		return contact;
	}
	
	
	public static List<Appointment> fillAppointments(String firstName, String lastName){
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Appointment> list = new ArrayList<Appointment>();

		String query = "SELECT A.appointmentsDate, D.doctorsFirstName, D.doctorsLastName, D.doctorsSpecialization"+
				" FROM appointments A, patient_cards PC, patients P, doctors D"+
				" WHERE A.idPatientsCard=PC.idPatientsCard"+
				" AND PC.idPatientsCard=P.patient_id AND P.first_name='"+
				firstName+"' AND P.last_name='"+lastName+"' AND A.idDoctor=D.idDoctor";
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
	
	
	public static List<String> fillConditions(String firstName, String lastName){
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		List<String> list = new ArrayList<String>();
		String query = "SELECT C.cCondition"+
				" FROM patient_cards PC, patients P, patient_conditions C"+
				" WHERE C.idPatientsCard=PC.idPatientsCard"+
				" AND PC.patient_id=P.patient_id AND P.first_name='"+
				firstName+"' AND P.last_name='"+lastName+"'";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while(result.next()){
				String condition = result.getString("cCondition");
				list.add(condition);
			}
		} catch (SQLException e) {
			System.out.println("Error - fillConditions.");
			e.printStackTrace();
		}
		return list;
	}
	
	//USUN STAN PACJENTA//
	
	public static void deleteCondition(String tekst) throws SQLException{
		
		Connection dbConnection = null;
		Statement statement = null;
		String query = "DELETE FROM patient_conditions WHERE cCondition = '"+tekst+"';";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			statement.executeUpdate(query);
			}
		 catch (SQLException e) {
			System.out.println("Error - deleteConditions.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
//USUN WIZYTE//
	
public static void deleteAppointment(String date, String FirstNameDoctor, String LastNameDoctor, int id) throws SQLException{
		
		Connection dbConnection = null;
		Statement statement = null;
		int idDoctor;
		idDoctor=getIdDoctor(FirstNameDoctor,LastNameDoctor);
		String query = "delete from appointments where idDoctor="+idDoctor+" and appointmentsDate='"+date+"' and idPatientsCard="+id+";";
		try {
			System.out.println(date);
			System.out.println(id);
			System.out.println(idDoctor);
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			statement.executeUpdate(query);
			}
		 catch (SQLException e) {
			System.out.println("Error - deleteAppointment.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
//USUN PACJENTA//
public static void deletePatient(String id) throws SQLException{
	
	Connection dbConnection = null;
	Statement statement = null;
	String query = "DELETE FROM patients WHERE patient_id = "+id+";";
	try {
		dbConnection = dbConnection();
		statement = (Statement) dbConnection.createStatement();
		statement.executeUpdate(query);
		}
	 catch (SQLException e) {
		System.out.println("Error - deletePatients.");
		e.printStackTrace();
	}finally {
		if (statement != null) {
			statement.close();
		}
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
}
//USUN WYWIAD//
public static void deleteInterview(String date, String FirstNameDoctor, String LastNameDoctor, int id) throws SQLException{
	
	Connection dbConnection = null;
	Statement statement = null;
	int idDoctor;
	idDoctor=getIdDoctor(FirstNameDoctor,LastNameDoctor);
	String query = "delete from interviews where idDoctor="+idDoctor+" and interviewsDate='"+date+"' and idPatientsCard="+id+";";
	try {
		System.out.println(date);
		System.out.println(id);
		System.out.println(idDoctor);
		dbConnection = dbConnection();
		statement = (Statement) dbConnection.createStatement();
		statement.executeUpdate(query);
		}
	 catch (SQLException e) {
		System.out.println("Error - deleteInterview.");
		e.printStackTrace();
	}finally {
		if (statement != null) {
			statement.close();
		}
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
}
	//DODAJ STAN PACJENTA//
	
	public static int conditionMaxID() throws SQLException{		
		
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet wynik = null;
		int id=0;
		String query = "select condition_id from patient_conditions where condition_id=(select MAX(condition_id) from patient_conditions);";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			wynik = (ResultSet) statement.executeQuery(query);
			while (wynik.next()) {
			id=wynik.getInt("condition_id");
			System.out.println(id);
			}
		}
		 catch (SQLException e) {
			System.out.println("Error - MaxConditionId.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return id;
	}
	
	public static int getIdDoctor(String firstName, String lastName) throws SQLException{		
		
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet wynik = null;
		int id=0;
		String query = "select idDoctor from doctors where doctorsLastName='"+lastName+"' and doctorsFirstName='"+firstName+"';";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			wynik = (ResultSet) statement.executeQuery(query);
			while (wynik.next()) {
			id=wynik.getInt("idDoctor");
			System.out.println(id);
			}
		}
		 catch (SQLException e) {
			System.out.println("Error - IdDoctor.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return id;
	}
	
public static int getIdPatient(String firstName, String lastName) throws SQLException{		
		
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet wynik = null;
		int id=0;
		String query = "select patient_id from patients where first_name='"+firstName+"' and last_name='"+lastName+"';";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			wynik = (ResultSet) statement.executeQuery(query);
			while (wynik.next()) {
			id=wynik.getInt("patient_id");
			System.out.println(id);
			}
		}
		 catch (SQLException e) {
			System.out.println("Error - getIdPatient.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return id;
	}
	
	public static int interviewMaxID() throws SQLException{		
		
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet wynik = null;
		int id=0;
		String query = "select interview_id from interviews where interview_id=(select MAX(interview_id) from interviews);";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			wynik = (ResultSet) statement.executeQuery(query);
			while (wynik.next()) {
			id=wynik.getInt("interview_id");
			System.out.println("interview max id:"+id);
			}
		}
		 catch (SQLException e) {
			System.out.println("Error - MaxInterviewId.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return id;
	}
	
public static int appointmentMaxID() throws SQLException{		
		
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet wynik = null;
		int id=0;
		String query = "select idAppointment from appointments where idAppointment=(select MAX(idAppointment) from appointments);";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			wynik = (ResultSet) statement.executeQuery(query);
			while (wynik.next()) {
			id=wynik.getInt("idAppointment");
			System.out.println("appointment max id:"+id);
			}
		}
		 catch (SQLException e) {
			System.out.println("Error - MaxAppointmentId.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return id;
	}
	public static void addCondition(int id,String tekst,String firstName, String lastName) throws SQLException{
		
		Connection dbConnection = null;
		Statement statement = null;		
		String query = "INSERT into patient_conditions values ("+id+", (SELECT patient_id FROM patients WHERE first_name='"
				+firstName+"' AND last_name='"+lastName+"'),'"+tekst+"');";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			statement.executeUpdate(query);
			}			
		 catch (SQLException e) {
			System.out.println("Error - addConditions.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}
	
public static void addInterview(int id,int idDoctor, String date,  String firstName, String lastName, String information) throws SQLException{
		
		Connection dbConnection = null;
		Statement statement = null;		
		String query = "INSERT into interviews values ("+id+", (SELECT patient_id FROM patients WHERE first_name='"
				+firstName+"' AND last_name='"+lastName+"'),"+idDoctor+",'"+date+"','"+information+"');";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			System.out.println("idDoctor "+idDoctor);
			System.out.println(date);
			statement.executeUpdate(query);
			}			
		 catch (SQLException e) {
			System.out.println("Error - addInterview.");
			e.printStackTrace();
		}finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

public static void addApointment(int id,int idDoctor, String date,  String firstName, String lastName) throws SQLException{
	
	Connection dbConnection = null;
	Statement statement = null;		
	String query = "INSERT into appointments values ("+id+", (SELECT patient_id FROM patients WHERE first_name='"
			+firstName+"' AND last_name='"+lastName+"'),"+idDoctor+",'"+date+");";
	try {
		dbConnection = dbConnection();
		statement = (Statement) dbConnection.createStatement();
		System.out.println("idDoctor "+idDoctor);
		System.out.println(date);
		statement.executeUpdate(query);
		}			
	 catch (SQLException e) {
		System.out.println("Error - addAppointment.");
		e.printStackTrace();
	}finally {
		if (statement != null) {
			statement.close();
		}
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
}
	
	public static List<MedicineRequirement> fillMedicineRequirements(String firstName, String lastName){
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		List<MedicineRequirement> list = new ArrayList<MedicineRequirement>();
		String query = "SELECT PR.title, MR.amount, MR.application"+
				" from products PR, medicine_requirements MR, patient_cards PC, patients P, pharmacy_medicines PM"+
				" WHERE  P.first_name='"+firstName+"' AND P.last_name='"+lastName+"'"+
				" AND P.patient_id=PC.patient_id AND PC.idPatientsCard= MR.idPatientsCard"+
				" AND MR.medicine_id= PM.medicine_id AND PM.product_id=PR.product_id;";
		try {
			dbConnection = dbConnection();
			statement = (Statement) dbConnection.createStatement();
			result = (ResultSet) statement.executeQuery(query);
			while(result.next()){
				String title = result.getString("title");
				String amount = result.getString("amount");
				String application = result.getString("application");
				list.add(new MedicineRequirement(title,amount,application));
			}
		} catch (SQLException e) {
			System.out.println("Error - fillMedicineRequirements.");
			e.printStackTrace();
		}
		return list;
	}
}
