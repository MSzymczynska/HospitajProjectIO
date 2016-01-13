package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import projekt.FormField;
import projekt.HospitalPharmacy;
import projekt.Medicine;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

public class FormFieldDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField whenOrdered;
	private JTextField onWhenOrdered;
	private JTextField amount;
	SimpleDateFormat formatter;
	private int medicineId=0;
	private int patientId=0;
	private int form_field_id=0;
	
	public FormFieldDialog(Connection con, HospitalPharmacyWindow hospitalPharmacyWindow, FormField formField, HospitalPharmacy hospitalPharmacy, boolean isNew) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//hospitalPharmacyWindow.setVisible(true);
			}
		});
		int whoOrdered =101;
		setBounds(100, 100, 500, 400);
		setVisible(true);
		hospitalPharmacyWindow.setVisible(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblZamawiajcy = new JLabel("Zamawiaj\u0105cy");
		lblZamawiajcy.setBounds(10, 11, 70, 14);
		contentPanel.add(lblZamawiajcy);
		
		//to mo¿e uzupe³niaæ siê automatycznie pobieraj¹c nazwisko zamawiaj¹cego, albo jego id. lepiej chyba nazwisko
		JLabel whoOrderedL = new JLabel(Integer.toString(whoOrdered));
		whoOrderedL.setBounds(10, 36, 46, 14);
		contentPanel.add(whoOrderedL);
		
		JLabel lblLek = new JLabel("Lek");
		lblLek.setBounds(10, 78, 46, 14);
		contentPanel.add(lblLek);
		
		//mo¿emy pobieraæ nazwy leków z MedicineList i wrzuciæ je do combo boxa, ¿eby nie trzeba by³o pisaæ z palca
		//zmieniæ na diagramie klas z id na name
		ArrayList<Medicine> m = hospitalPharmacy.medicineList;
		String medName[] = new String[m.size()];
		for(int i=0; i<m.size(); i++) {
			medName[i] = m.get(i).name;
		}
		
		//String przyk³adowatablicaleki[]={"qwe","tyu","poi"};
		JComboBox comboBoxMedicine = new JComboBox(medName);
		if(!isNew) comboBoxMedicine.setSelectedIndex(formField.getOrderedMedicineId()-1);
		comboBoxMedicine.setBounds(10, 103, 299, 20);
		contentPanel.add(comboBoxMedicine);
		
		JLabel lblPacjent = new JLabel("Pacjent");
		lblPacjent.setBounds(125, 11, 46, 14);
		contentPanel.add(lblPacjent);
		
		String patients[];
		String curDate=null;
		PreparedStatement query1, query2, query3;
		try {
			query1 = (PreparedStatement) con.prepareStatement("select patient_id, first_name, last_name from patients");
			ResultSet result=(ResultSet) query1.executeQuery();
			
			query2 = (PreparedStatement) con.prepareStatement("select count(*) from patients");
			ResultSet result2=(ResultSet) query2.executeQuery();
			
			query3 = (PreparedStatement) con.prepareStatement("select CURDATE()");
			ResultSet result3=(ResultSet) query3.executeQuery();
			while(result3.next())
			curDate=result3.getString(1);
			int i=0;
			while(result2.next())
			i=Integer.parseInt(result2.getString(1));
			patients=new String[i];
			int j=0;
			while(result.next()){
				patients[j]=result.getString(2)+ " " + result.getString(3);
				j++;
			}
		} catch (SQLException e1) {
			patients=null;
			System.out.print(e1.getMessage());
		}
		
		JComboBox comboBoxPatient = new JComboBox(patients);
		if(!isNew) comboBoxPatient.setSelectedIndex(formField.getPatientId()-1);
		comboBoxPatient.setBounds(125, 33, 161, 20);
		contentPanel.add(comboBoxPatient);
		
		JLabel lblData = new JLabel("Data zam\u00F3wienia");
		lblData.setBounds(10, 156, 86, 14);
		contentPanel.add(lblData);
		
		whenOrdered = new JTextField();
		whenOrdered.setText(curDate);

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		//whenD = formField.getWhenOrderedDate();
		whenOrdered.setBounds(10, 181, 86, 20);
		contentPanel.add(whenOrdered);
		whenOrdered.setColumns(10);
		
		JLabel lblZmwienieNa = new JLabel("Zm\u00F3wienie na");
		lblZmwienieNa.setBounds(125, 156, 65, 14);
		contentPanel.add(lblZmwienieNa);
		
		onWhenOrdered = new JTextField();
		onWhenOrdered.setText(curDate);
		onWhenOrdered.setBounds(125, 181, 86, 20);
		contentPanel.add(onWhenOrdered);
		onWhenOrdered.setColumns(10);
		
		JLabel lblIlo = new JLabel("Ilo\u015B\u0107");
		lblIlo.setBounds(326, 78, 46, 14);
		contentPanel.add(lblIlo);
		
		amount = new JTextField();
		if(isNew)amount.setText("1");
		else amount.setText(String.valueOf(formField.getAmount()));
		amount.setBounds(326, 103, 86, 20);
		contentPanel.add(amount);
		amount.setColumns(10);
		
		JLabel lblStatus = new JLabel("Czy lek zosta\u0142 wydany:");
		lblStatus.setBounds(326, 156, 148, 14);
		contentPanel.add(lblStatus);
		
		String statusTable[] = {"nie", "tak"};
		JComboBox comboBoxIsDelivered = new JComboBox(statusTable);
		comboBoxIsDelivered.setBounds(326, 181, 86, 20);
		contentPanel.add(comboBoxIsDelivered);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(whenOrdered.getText().isEmpty() || onWhenOrdered.getText().isEmpty() || amount.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, 
									"Wype³nij wszystkie pola",
									"B³¹d!",
									JOptionPane.ERROR_MESSAGE);
						}
						else {
							String MedicineName;
							boolean status=false;
							String patientName;
							Date when=null;
							Date onWhen=null;
							try {

								when = formatter.parse(whenOrdered.getText());					
								onWhen = formatter.parse(onWhenOrdered.getText());
								status = false;
								if(comboBoxIsDelivered.getSelectedIndex()==1)
									status = true;
								MedicineName=comboBoxMedicine.getSelectedItem().toString();
								for(int i=0; i<hospitalPharmacy.medicineList.size();i++)
								{
									if(MedicineName==hospitalPharmacy.medicineList.get(i).name)
										medicineId=hospitalPharmacy.medicineList.get(i).id;
								}
								patientId = comboBoxPatient.getSelectedIndex()+1;
								patientName=comboBoxPatient.getSelectedItem().toString();

							} catch (NumberFormatException a) {
								JOptionPane.showMessageDialog(null, 
										"Podano niepoprawn¹ iloœæ",
										"B³¹d!",
										JOptionPane.ERROR_MESSAGE);
							} catch (Exception error) {
								JOptionPane.showMessageDialog(null, 
										"Podano niepoprawny format daty",
										"B³¹d!",
										JOptionPane.ERROR_MESSAGE);
							}
							finally{
								//System.out.print(formField.FormFieldId="medId"+medicineId+"whoOrder"+whoOrdered.getText()+when);
								//formField.FormFieldId=form_field_id;
								formField.setAmount(Integer.parseInt(amount.getText()));
								formField.setDelivered(status);
								formField.setOnWhenOrdered(formatter.format(onWhen));
								formField.setOrderedMedicineId(medicineId);
								formField.setPatientId(patientId);
								formField.setUserWhoOrdered(whoOrderedL.getText());
								formField.setWhenOrderedDate(formatter.format(when));
								if(!isNew){
									Statement stmt = null;
										PreparedStatement query1;									
											try {
												//stmt = (Statement) con.createStatement();
											      //String sql = "update form_field set on_when_ordered=\""+onWhenOrdered.getText()+"\", ordered_medicine_id="+formField.getOrderedMedicineId()+", patient_id="+formField.getPatientId()+", amount="+formField.getAmount()+", is_delivered="+formField.isDelivered()+", when_ordered_date=\""+whenOrdered.getText()+"\" where form_field_id="+form_field_id;
											      //stmt.executeUpdate(sql);
												int x;
												if(formField.isDelivered()) x=1;
												else x=0;
												System.out.println(onWhenOrdered.getText()+ " " + formField.getOrderedMedicineId() + " " +  formField.getPatientId() + formField.ffId);
												query1 = (PreparedStatement) con.prepareStatement("update form_field set on_when_ordered=\""+onWhenOrdered.getText()+"\", ordered_medicine_id="+formField.getOrderedMedicineId()+", patient_id="+formField.getPatientId()+", amount="+formField.getAmount()+", is_delivered="+x+", when_ordered_date=\""+whenOrdered.getText()+"\" where form_field_id="+formField.ffId);
												query1.executeUpdate();											
											} catch (SQLException e1) {
												System.out.print(e1.getMessage());
											}
									
								}
									
								if(isNew){ 
									
									PreparedStatement query1, query2;									
									
										try {
											query1 = (PreparedStatement) con.prepareStatement("select count(*) from form_field");
											ResultSet result=(ResultSet) query1.executeQuery();
											while(result.next()) form_field_id=Integer.parseInt(result.getString(1))+1;
											int x;
											if(formField.isDelivered()) x=1;
											else x=0;
											query2 = (PreparedStatement) con.prepareStatement("insert into form_field values ("+form_field_id+", "+ "\""+onWhenOrdered.getText()+"\""+", "+medicineId+", "+patientId+", "+formField.getAmount()+", "+x+", \""+whenOrdered.getText()+"\", "+whoOrdered+");");
											query2.executeUpdate();
										
										} catch (SQLException e1) {
											System.out.print(e1.getMessage());
										}
										
									hospitalPharmacy.addOrderedMedicine(formField);
									}
								
								if(formField.isDelivered()) {
									int quantity = hospitalPharmacy.getPharmacyMedicine().get(medicineId).quantity;
									if(quantity>=Integer.parseInt(amount.getText())) {
										//tu moznaby napisac tez updae w bazie ale nie bede tego robic zeby zawsze nam sie zmianialy dane tylko w programie
										hospitalPharmacy.getPharmacyMedicine().get(medicineId-1).quantity = quantity-Integer.parseInt(amount.getText());
										hospitalPharmacyWindow.setVisible(true);
										
										dispose();
									}
									else {
										JOptionPane.showMessageDialog(null, 
												"Lek nie mo¿e byæ wydany. Brak potrzebnej iloœci w aptece.",
												"B³¹d!",
												JOptionPane.ERROR_MESSAGE);
										formField.setDelivered(false);
									}
								}
								else {
								hospitalPharmacyWindow.setVisible(true);
								
								dispose();
								}
							}
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("WyjdŸ");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hospitalPharmacyWindow.setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("WyjdŸ");
				buttonPane.add(cancelButton);
			}
		}
	}
}
