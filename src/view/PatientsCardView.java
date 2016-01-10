package view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import projekt.Appointment;
import projekt.AppointmentsTable;
import projekt.Condition;
import projekt.ConditionsTable;
import projekt.DatabaseConnectionKartaPacjenta;
import projekt.Interview;
import projekt.InterviewsTable;
import projekt.MedicineRequirement;
import projekt.MedicineRequirementsTable;

public class PatientsCardView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2321074602027007087L;
	
	
	private InterviewsTable modelOfInterviews;
	private List<Interview> listOfIntervies;

	private ConditionsTable modelOfConditions;
	private List<Condition> listOfConditions;

	private AppointmentsTable modelOfAppointments;
	private List<Appointment> listOfAppointments;

	private MedicineRequirementsTable modelOfMedicineRequirements;
	private List<MedicineRequirement> listOfMedicineRequirements;

	private String firstName;
	private String lastName;

	private JTable tableInterviews;
	private JTable tableAppointments;

	public PatientsCardView(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		JFrame jframe = new JFrame("Karta pacjenta: "+firstName +" "+lastName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setSize(600, 750);
		jframe.setVisible(true);
		jframe.getContentPane().setLayout(null);
		
		listOfIntervies = new ArrayList<Interview>();
		listOfIntervies = DatabaseConnectionKartaPacjenta.fillInterviews(firstName, lastName);
		modelOfInterviews = new InterviewsTable(listOfIntervies);
		
		tableInterviews = new JTable(modelOfInterviews);
		tableInterviews.setFillsViewportHeight(true);
		JScrollPane scrollPaneInterviews = new JScrollPane(tableInterviews);
		scrollPaneInterviews.setBounds(0, 42, 582, 169);
		jframe.getContentPane().add(scrollPaneInterviews);
		
		JLabel lblWywiady = new JLabel("Wywiady");
		lblWywiady.setBounds(12, 13, 558, 16);
		lblWywiady.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWywiady.setHorizontalAlignment(SwingConstants.CENTER);
		jframe.getContentPane().add(lblWywiady);
		
		listOfAppointments = new ArrayList<Appointment>();
		listOfAppointments = DatabaseConnectionKartaPacjenta.fillAppointments(firstName, lastName);
		modelOfAppointments = new AppointmentsTable(listOfAppointments);
		
		tableAppointments = new JTable(modelOfAppointments);
		tableAppointments.setFillsViewportHeight(true);
		JScrollPane scrollPaneAppointments = new JScrollPane(tableAppointments);
		scrollPaneAppointments.setBounds(0, 300, 582, 169);
		jframe.getContentPane().add(scrollPaneAppointments);
		
		JLabel lblWizyty = new JLabel("Wizyty");
		lblWizyty.setBounds(0, 271, 582, 16);
		lblWizyty.setHorizontalAlignment(SwingConstants.CENTER);
		lblWizyty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jframe.getContentPane().add(lblWizyty);
	}
}
