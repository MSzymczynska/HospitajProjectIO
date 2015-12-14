package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	
	public FormFieldDialog(HospitalPharmacyWindow hospitalPharmacyWindow, FormField formField, HospitalPharmacy hospitalPharmacy, boolean isNew) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				hospitalPharmacyWindow.setVisible(true);
			}
		});
		
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
		JLabel whoOrdered = new JLabel("osoba");
		whoOrdered.setBounds(10, 36, 46, 14);
		contentPanel.add(whoOrdered);
		
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
		if(!isNew) comboBoxMedicine.setSelectedIndex(formField.getOrderedMedicineId());
		comboBoxMedicine.setBounds(10, 103, 299, 20);
		contentPanel.add(comboBoxMedicine);
		
		JLabel lblPacjent = new JLabel("Pacjent");
		lblPacjent.setBounds(125, 11, 46, 14);
		contentPanel.add(lblPacjent);
		
		//mo¿e imiê i nazwisko pacjenta,¿eby nie wpisywaæ z palca. i na diagramie klas wtedy jeszcze zmieniæ z id na name
		String przyk³adowatablicapacjenci[] = {"Janusz", "Mietek", "Gertruda"};
		JComboBox comboBoxPatient = new JComboBox(przyk³adowatablicapacjenci);
		if(!isNew) comboBoxPatient.setSelectedIndex(formField.getPatientId());
		comboBoxPatient.setBounds(125, 33, 161, 20);
		contentPanel.add(comboBoxPatient);
		
		JLabel lblData = new JLabel("Data zam\u00F3wienia");
		lblData.setBounds(10, 156, 86, 14);
		contentPanel.add(lblData);
		
		whenOrdered = new JTextField();
		whenOrdered.setText("13-12-2015");
		//if(!isNew) whenOrdered.setText(formField.getWhenOrderedDate().toString());
		//else whenOrdered.setText(formField.getWhenOrderedDate().toString());
		//Date whenD;
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		//whenD = formField.getWhenOrderedDate();
		whenOrdered.setBounds(10, 181, 86, 20);
		contentPanel.add(whenOrdered);
		whenOrdered.setColumns(10);
		
		JLabel lblZmwienieNa = new JLabel("Zm\u00F3wienie na");
		lblZmwienieNa.setBounds(125, 156, 65, 14);
		contentPanel.add(lblZmwienieNa);
		
		onWhenOrdered = new JTextField();
		onWhenOrdered.setText("23-12-2015");
		//if(!isNew) onWhenOrdered.setText(formField.getOnWhenOrdered().toString());
		//else onWhenOrdered.setText(formField.getOnWhenOrdered().toString());
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
							
							boolean status=false;
							int medicineId=0;
							int patientId=0;
							String patientName;
							Date when=null;
							Date onWhen=null;
							try {
//								Date when=null;
//								Date onWhen=null;
								when = formatter.parse(whenOrdered.getText());					
								onWhen = formatter.parse(onWhenOrdered.getText());
								status = false;
								if(comboBoxIsDelivered.getSelectedIndex()==1)
									status = true;
								medicineId = comboBoxMedicine.getSelectedIndex();
								patientId = comboBoxPatient.getSelectedIndex();
								patientName=comboBoxPatient.getSelectedItem().toString();
//								formField.setAmount(Integer.parseInt(amount.getText()));
//								formField.setDelivered(status);
//								formField.setOnWhenOrdered(onWhen);
//								formField.setOrderedMedicineId(medicineId);
//								formField.setPatientId(patientId);
//								formField.setUserWhoOrdered(whoOrdered.getText());
//								formField.setWhenOrderedDate(when);
//								hospitalPharmacy.addOrderedMedicine(formField);
								//System.out.print(hospitalPharmacy.getOrderedMedicine().get(0).toString());
							//	FormField formField = new FormField(whoOrdered.getText(), when, medicineId, patientId, Integer.parseInt(amount.getText()), status, onWhen);
							//	hospitalPharmacyWindow.setVisible(true);
								//dispose();
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
								System.out.print(formField.FormFieldId="medId"+medicineId+"whoOrder"+whoOrdered.getText()+when);
								formField.setAmount(Integer.parseInt(amount.getText()));
								formField.setDelivered(status);
								formField.setOnWhenOrdered(formatter.format(onWhen));
								formField.setOrderedMedicineId(medicineId);
								formField.setPatientId(patientId);
								formField.setUserWhoOrdered(whoOrdered.getText());
								formField.setWhenOrderedDate(formatter.format(when));
							//	System.out.print(formField.toString());		
								if(isNew){ hospitalPharmacy.addOrderedMedicine(formField);}
								if(formField.isDelivered()) {
									int quantity = hospitalPharmacy.getPharmacyMedicine().get(medicineId).quantity;
									if(quantity>=Integer.parseInt(amount.getText())) {
										hospitalPharmacy.getPharmacyMedicine().get(medicineId).quantity = quantity-Integer.parseInt(amount.getText());
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
								//System.out.print(hospitalPharmacy.getOrderedMedicine().get(0).toString());
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
