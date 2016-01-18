package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import projekt.ConditionsTable;
import projekt.DatabaseConnectionKartaPacjenta;
import projekt.Interview;
import projekt.InterviewsTable;
import projekt.Patient;
import projekt.PatientsCardFunctions;
import projekt.PatientsTable;

public class PatientsListView extends JFrame {

	private String checkPatient;
	private JTable tablePatients;
	private PatientsTable modelOfPatients;
	private List<Patient> listOfPatients;
	private JScrollPane scrollPanePatients;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientsListView frame = new PatientsListView();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PatientsListView() {
		JFrame jframe = new JFrame("Lista pacjentow:");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setSize(527, 469);
		jframe.setVisible(true);
		jframe.getContentPane().setLayout(null);
		
	//WYSWIETLANIE LISTY//
		
		listOfPatients = new ArrayList<Patient>();
		listOfPatients = DatabaseConnectionKartaPacjenta.fillPatients();
		modelOfPatients = new PatientsTable(listOfPatients);
		
		tablePatients = new JTable(modelOfPatients);
		tablePatients.setFillsViewportHeight(true);
		scrollPanePatients = new JScrollPane(tablePatients);
		scrollPanePatients.setBounds(37, 42, 440, 250);
		jframe.getContentPane().add(scrollPanePatients);
		
		JLabel lblPatients = new JLabel("Pacjenci");
		lblPatients.setBounds(37, 15, 440, 16);
		lblPatients.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatients.setHorizontalAlignment(SwingConstants.CENTER);
		jframe.getContentPane().add(lblPatients);
		
	//USUWANIE PACJENTA//
		
		JButton btnUsunPacjenta = new JButton("Usun  pacjenta");
		btnUsunPacjenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DatabaseConnectionKartaPacjenta.deletePatient(checkPatient);
					listOfPatients=DatabaseConnectionKartaPacjenta.fillPatients();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUsunPacjenta.setBounds(352, 312, 125, 23);
		jframe.getContentPane().add(btnUsunPacjenta);
		
	//KONTAKT//
		
		JButton btnContact = new JButton("Kontakt");
		btnContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String kontakt=null;
				kontakt=DatabaseConnectionKartaPacjenta.getContact(checkPatient);
				PatientsCardDialogBox dialog = new PatientsCardDialogBox(PatientsCardFunctions.formatujString(kontakt));
				dialog.setVisible(true);
			}
		});
		btnContact.setBounds(193, 312, 125, 23);
		jframe.getContentPane().add(btnContact);
	//DODAJ PACJENTA

		JButton btnDodajPacjenta = new JButton("Dodaj Pacjenta");
		btnDodajPacjenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatePatient card = new CreatePatient();
				card.setVisible(true);
			}
		});
		btnDodajPacjenta.setBounds(37, 312, 125, 23);
		jframe.getContentPane().add(btnDodajPacjenta);
		btnContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			/////
				jframe.getContentPane().remove(scrollPanePatients);
			////
			
			}
		});
		
	
	
	//ZAZNACZONY STAN PACJENTA///		
	
		tablePatients.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							if (!e.getValueIsAdjusting()) {							
								checkPatient = (String) tablePatients.getValueAt(
										tablePatients.getSelectedRow(), 2);					
								}
							}				
						}
					);			

}}
