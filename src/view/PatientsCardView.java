package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projekt.PatientsCardFunctions;
import projekt.Appointment;
import projekt.AppointmentsTable;
import projekt.Condition;
import projekt.ConditionsTable;
import projekt.DatabaseConnectionKartaPacjenta;
import projekt.Interview;
import projekt.InterviewsTable;
import projekt.MedicineRequirement;
import projekt.MedicineRequirementsTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;

public class PatientsCardView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2321074602027007087L;
	
	public  String checkCondition;
	public 	Date checkDateAppointmentSQL;
	public 	String checkDateAppointment;
	public 	String checkDoctorsFirstName;
	public	String checkDoctorsLastName;
	
	public 	Date checkDateInterviewSQL;
	public 	String checkDateInterview;
	public 	String checkInterviewDoctorsFirstName;
	public	String checkInterviewDoctorsLastName;
	public  String checkInterviewInformation;
	private InterviewsTable modelOfInterviews;
	private List<Interview> listOfIntervies;

	private ConditionsTable modelOfConditions;
	private List<String> listOfConditions;

	private AppointmentsTable modelOfAppointments;
	private List<Appointment> listOfAppointments;

	private MedicineRequirementsTable modelOfMedicineRequirements;
	private List<MedicineRequirement> listOfMedicineRequirements;

	private String firstName;
	private String lastName;

	private JTable tableInterviews;
	private JTable tableAppointments;
	private JTable tableCondition;
	private JTable tableRequirements;
	
	private JTextField condition;
	
	private JTextField date;
	private JTextField doctorsFirst;
	private JTextField DoctorsLastName;
	private JTextField interviewInformation;
	private JTextField appointmentDate;
	private JTextField appointmentDoctorFirstName;
	private JTextField apointmentDoctorSecondName;
	
	private JLabel bladInterview;
	private JLabel bladAppointment;
	public PatientsCardView(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		JFrame jframe = new JFrame("Karta pacjenta: "+firstName +" "+lastName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setSize(959, 667);
		jframe.setVisible(true);
		jframe.getContentPane().setLayout(null);

		
		
		//WYWIADY//
		
		listOfIntervies = new ArrayList<Interview>();
		listOfIntervies = DatabaseConnectionKartaPacjenta.fillInterviews(firstName, lastName);
		modelOfInterviews = new InterviewsTable(listOfIntervies);
		
		tableInterviews = new JTable(modelOfInterviews);
		tableInterviews.setFillsViewportHeight(true);
		JScrollPane scrollPaneInterviews = new JScrollPane(tableInterviews);
		scrollPaneInterviews.setBounds(10, 42, 440, 94);
		jframe.getContentPane().add(scrollPaneInterviews);
		
		JLabel lblWywiady = new JLabel("Wywiady");
		lblWywiady.setBounds(10, 15, 440, 16);
		lblWywiady.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWywiady.setHorizontalAlignment(SwingConstants.CENTER);
		jframe.getContentPane().add(lblWywiady);
		
		
	
		
		//WIZYTY//
		
		listOfAppointments = new ArrayList<Appointment>();
		listOfAppointments = DatabaseConnectionKartaPacjenta.fillAppointments(firstName, lastName);
		modelOfAppointments = new AppointmentsTable(listOfAppointments);
		
		tableAppointments = new JTable(modelOfAppointments);
		tableAppointments.setFillsViewportHeight(true);
		JScrollPane scrollPaneAppointments = new JScrollPane(tableAppointments);
		scrollPaneAppointments.setBounds(10, 301, 440, 94);
		jframe.getContentPane().add(scrollPaneAppointments);
		
		JLabel lblWizyty = new JLabel("Wizyty");
		lblWizyty.setBounds(10, 274, 440, 16);
		lblWizyty.setHorizontalAlignment(SwingConstants.CENTER);
		lblWizyty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jframe.getContentPane().add(lblWizyty);
		
		
		//DODAJ WYWIAD//
		date = new JTextField();
		date.setBounds(10, 190, 110, 23);
		jframe.getContentPane().add(date);
		date.setColumns(10);
		
		doctorsFirst = new JTextField();
		doctorsFirst.setBounds(118, 190, 110, 23);
		jframe.getContentPane().add(doctorsFirst);
		doctorsFirst.setColumns(10);
		
		DoctorsLastName = new JTextField();
		DoctorsLastName.setBounds(227, 190, 110, 23);
		jframe.getContentPane().add(DoctorsLastName);
		DoctorsLastName.setColumns(10);
		
		interviewInformation = new JTextField();
		interviewInformation.setBounds(10, 224, 440, 23);
		jframe.getContentPane().add(interviewInformation);
		interviewInformation.setColumns(10);
		
		//STAN PACJENTA//
		
		listOfConditions= new ArrayList<String>();
		listOfConditions= DatabaseConnectionKartaPacjenta.fillConditions(firstName, lastName);
		modelOfConditions= new ConditionsTable(listOfConditions);
				
		tableCondition = new JTable(modelOfConditions);
		tableCondition.setFillsViewportHeight(true);
		JScrollPane scrollPaneConditions = new JScrollPane(tableCondition);
		scrollPaneConditions.setBounds(485, 301, 440, 94);
		jframe.getContentPane().add(scrollPaneConditions);
				
		JLabel lblCondition = new JLabel("Stan Pacjenta");
		lblCondition.setBounds(485, 274, 440, 16);
		lblCondition.setHorizontalAlignment(SwingConstants.CENTER);
		lblCondition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jframe.getContentPane().add(lblCondition);
		
		
		condition = new JTextField();
		condition.setBounds(485, 449, 440, 23);
		jframe.getContentPane().add(condition);
		condition.setColumns(10);
		
	//WYMAGANE LEKI//
		
		listOfMedicineRequirements= new ArrayList<MedicineRequirement>();
		listOfMedicineRequirements= DatabaseConnectionKartaPacjenta.fillMedicineRequirements(firstName, lastName);
		modelOfMedicineRequirements= new MedicineRequirementsTable(listOfMedicineRequirements);
				
		tableRequirements = new JTable(modelOfMedicineRequirements);
		tableRequirements.setFillsViewportHeight(true);
		JScrollPane scrollPaneRequirements = new JScrollPane(tableRequirements);
		scrollPaneRequirements .setBounds(485, 42, 440, 94);
		jframe.getContentPane().add(scrollPaneRequirements );
				
		JLabel lblRequirement = new JLabel("Zalecone leki: ");
		lblRequirement.setBounds(485, 15, 440, 16);
		lblRequirement.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequirement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jframe.getContentPane().add(lblRequirement);

//USUN ZAZNACZONY STAN PACJENTA//		
		
		JButton btnUsunStanPacjenta = new JButton("Usun stan pacjenta");
		btnUsunStanPacjenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DatabaseConnectionKartaPacjenta.deleteCondition(checkCondition);
					listOfConditions= DatabaseConnectionKartaPacjenta.fillConditions(firstName, lastName);
					modelOfConditions= new ConditionsTable(listOfConditions);										
					tableCondition = new JTable(modelOfConditions);
					tableCondition.revalidate();
					tableCondition.validate();
					tableCondition.repaint();
					jframe.getContentPane().repaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUsunStanPacjenta.setBounds(485, 400, 125, 23);
		jframe.getContentPane().add(btnUsunStanPacjenta);
		
//DODANIE STANU PACJENTA		
		
		JButton btnDodajStanPacjenta = new JButton("Dodaj stan pacjenta");
		btnDodajStanPacjenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id=0;
				try {
					id=(DatabaseConnectionKartaPacjenta.conditionMaxID())+1;
					DatabaseConnectionKartaPacjenta.addCondition(id,condition.getText(), firstName, lastName);					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDodajStanPacjenta.setBounds(772, 417, 153, 23);
		jframe.getContentPane().add(btnDodajStanPacjenta);
		
//DODANIE WYWIADU 	
		
				JButton btnDodajWizyte = new JButton("Dodaj wywiad");
				btnDodajWizyte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int id=0;
						int idDoctor=0;
						try {
							if(DatabaseConnectionKartaPacjenta.checkExistDoctor(doctorsFirst.getText(), DoctorsLastName.getText())==true){
								
							id=(DatabaseConnectionKartaPacjenta.interviewMaxID())+1;
							idDoctor=DatabaseConnectionKartaPacjenta.getIdDoctor(doctorsFirst.getText(), DoctorsLastName.getText());
							System.out.println("ajdiwywiadu:"+id);
							System.out.println("ajdidoktora"+idDoctor);
							DatabaseConnectionKartaPacjenta.addInterview(id, idDoctor, date.getText(), firstName, lastName, interviewInformation.getText());	
							bladInterview.setText("Dodano wywiad");
							bladInterview.setForeground(Color.GREEN);
							bladInterview.repaint();
							jframe.getContentPane().repaint();
							}
							else{
							
								bladInterview.setText("Bledne imie lub nazwisko doktora");
								bladInterview.setForeground(Color.RED);
								bladInterview.repaint();
								jframe.getContentPane().repaint();
							}
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				btnDodajWizyte.setBounds(347, 190, 103, 23);
				jframe.getContentPane().add(btnDodajWizyte);
				
				
//DODANIE WIZYTY 	
				
				
				JButton btnDodajWizyte_1 = new JButton("Dodaj wizyte");
				btnDodajWizyte_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int id=0;
						int idDoctor=0;
						try {
							if(DatabaseConnectionKartaPacjenta.checkExistDoctor(appointmentDoctorFirstName.getText(), apointmentDoctorSecondName.getText())==true){
							
								bladAppointment.setForeground(Color.GREEN);
								bladAppointment.setText("Wizyta dodana poprawnie");
								bladAppointment.repaint();
								jframe.getContentPane().repaint();
							id=(DatabaseConnectionKartaPacjenta.appointmentMaxID())+1;
							idDoctor=DatabaseConnectionKartaPacjenta.getIdDoctor(appointmentDoctorFirstName.getText(), apointmentDoctorSecondName.getText());
							System.out.println("ajdiwizyty:"+id);
							System.out.println("ajdidoktora"+idDoctor);
							DatabaseConnectionKartaPacjenta.addApointment(id, idDoctor, appointmentDate.getText(), firstName, lastName);	
							
							}
							else{

								bladAppointment.setForeground(Color.RED);
								bladAppointment.setText("Bledne imie lub nazwisko doktora");
								bladAppointment.repaint();
								jframe.getContentPane().repaint();
							}
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				
				bladAppointment = new JLabel();
				bladAppointment.setBounds(118, 406, 332, 14);				
				jframe.getContentPane().add(bladAppointment);
				
				bladInterview = new JLabel();
				bladInterview.setBounds(123, 147, 322, 14);
				jframe.getContentPane().add(bladInterview);
				
				btnDodajWizyte_1.setBounds(347, 449, 103, 23);
				jframe.getContentPane().add(btnDodajWizyte_1);
							
				appointmentDate = new JTextField();
				appointmentDate.setBounds(10, 449, 110, 23);
				jframe.getContentPane().add(appointmentDate);
				appointmentDate.setColumns(10);
				
				appointmentDoctorFirstName = new JTextField();
				appointmentDoctorFirstName.setBounds(118, 449, 110, 23);
				jframe.getContentPane().add(appointmentDoctorFirstName);
				appointmentDoctorFirstName.setColumns(10);
				
				apointmentDoctorSecondName = new JTextField();
				apointmentDoctorSecondName.setBounds(227, 449, 110, 23);
				jframe.getContentPane().add(apointmentDoctorSecondName);
				apointmentDoctorSecondName.setColumns(10);
		
//USUN ZAZNACZONA WIZYTE//
				JButton btnDeleteAppointment = new JButton("Usun wizyte");
				btnDeleteAppointment.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							int id = DatabaseConnectionKartaPacjenta.getIdPatient(firstName, lastName);
							DatabaseConnectionKartaPacjenta.deleteAppointment(checkDateAppointment, checkDoctorsFirstName, checkDoctorsLastName, id);
							bladAppointment.setForeground(Color.GREEN);
							bladAppointment.setText("Usunieto wizyte z dnia "+checkDateAppointment);
							bladAppointment.repaint();
							jframe.getContentPane().repaint();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
					}
				});
				btnDeleteAppointment.setBounds(10, 402, 103, 23);
				jframe.getContentPane().add(btnDeleteAppointment);
				
				
//USUN ZAZNACZONY WYWIAD//
				JButton btnDeleteInterview = new JButton("Usun wywiad");
				btnDeleteInterview.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							int id = DatabaseConnectionKartaPacjenta.getIdPatient(firstName, lastName);
							DatabaseConnectionKartaPacjenta.deleteInterview(checkDateInterview, checkInterviewDoctorsFirstName, checkInterviewDoctorsLastName, id);
							bladInterview.setForeground(Color.GREEN);
							bladInterview.setText("Usunieto wywiad z dnia "+checkDateInterview);
							bladInterview.repaint();
							jframe.getContentPane().repaint();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
					}
				});
				btnDeleteInterview.setBounds(10, 138, 103, 23);
				jframe.getContentPane().add(btnDeleteInterview);
				
				
				JLabel lblNewLabel = new JLabel("YYYY-MM-DD");
				lblNewLabel.setBounds(46, 437, 74, 14);
				jframe.getContentPane().add(lblNewLabel);
				
				JLabel label = new JLabel("YYYY-MM-DD");
				label.setBounds(46, 176, 143, 14);
				jframe.getContentPane().add(label);
				
				JLabel lblData = new JLabel("Data");
				lblData.setBounds(10, 176, 69, 14);
				jframe.getContentPane().add(lblData);
				
				JLabel lblImiDoktora = new JLabel("Imi\u0119 doktora");
				lblImiDoktora.setBounds(118, 176, 116, 14);
				jframe.getContentPane().add(lblImiDoktora);
				
				JLabel lblNazwiskoDoktora = new JLabel("Nazwisko doktora");
				lblNazwiskoDoktora.setBounds(227, 176, 110, 14);
				jframe.getContentPane().add(lblNazwiskoDoktora);
				
				JLabel lblOpis = new JLabel("Opis:");
				lblOpis.setBounds(10, 212, 46, 14);
				jframe.getContentPane().add(lblOpis);
				
				JLabel lblData_1 = new JLabel("Data");
				lblData_1.setBounds(10, 437, 46, 14);
				jframe.getContentPane().add(lblData_1);
				
				JLabel lblImiDoktora_1 = new JLabel("Imi\u0119 doktora");
				lblImiDoktora_1.setBounds(118, 437, 110, 14);
				jframe.getContentPane().add(lblImiDoktora_1);
				
				JLabel lblNazwiskoDoktora_1 = new JLabel("Nazwisko doktora");
				lblNazwiskoDoktora_1.setBounds(225, 437, 112, 14);
				jframe.getContentPane().add(lblNazwiskoDoktora_1);
				
				JLabel lblStanPacjenta = new JLabel("Stan pacjenta:");
				lblStanPacjenta.setBounds(485, 434, 110, 14);
				jframe.getContentPane().add(lblStanPacjenta);
				
				JButton btnOpis = new JButton("Opis");
				btnOpis.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PatientsCardDialogBox dialog = new PatientsCardDialogBox(PatientsCardFunctions.formatujString(checkInterviewInformation));
						dialog.setVisible(true);		
					}
				});
				btnOpis.setBounds(361, 138, 89, 23);
				jframe.getContentPane().add(btnOpis);
				
			
				
								
		
//ZAZNACZONY STAN PACJENTA///		
	
		tableCondition.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {							
							checkCondition = (String) tableCondition.getValueAt(
									tableCondition.getSelectedRow(), 0);					
							}
						}				
					}
				);			

//ZAZNACZ WIZYTE//
		
		tableAppointments.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {							
							checkDateAppointmentSQL = (Date) tableAppointments.getValueAt(
									tableAppointments.getSelectedRow(), 0);	
							checkDoctorsFirstName = (String) tableAppointments.getValueAt(
									tableAppointments.getSelectedRow(), 1);
							checkDoctorsLastName = (String) tableAppointments.getValueAt(
									tableAppointments.getSelectedRow(), 2);
							
							checkDateAppointment=checkDateAppointmentSQL.toString();
							System.out.println(checkDateAppointment);
							System.out.println(checkDoctorsFirstName);
							System.out.println(checkDoctorsLastName);
							}
						}				
					}
				);			
		
		//ZAZNACZ WYWIAD//
		
				tableInterviews.getSelectionModel().addListSelectionListener(
						new ListSelectionListener() {

							@Override
							public void valueChanged(ListSelectionEvent e) {
								if (!e.getValueIsAdjusting()) {							
									checkDateInterviewSQL = (Date) tableInterviews.getValueAt(
											tableInterviews.getSelectedRow(), 0);	
									checkInterviewDoctorsFirstName = (String) tableInterviews.getValueAt(
											tableInterviews.getSelectedRow(), 1);
									checkInterviewDoctorsLastName = (String) tableInterviews.getValueAt(
											tableInterviews.getSelectedRow(), 2);
									checkInterviewInformation= (String)  tableInterviews.getValueAt(
											tableInterviews.getSelectedRow(), 3);
									
									checkDateInterview=checkDateInterviewSQL.toString();
									System.out.println(checkDateInterview);
									System.out.println(checkInterviewDoctorsFirstName);
									System.out.println(checkInterviewDoctorsLastName);
									}
								}				
							}
						);			
	}
}
