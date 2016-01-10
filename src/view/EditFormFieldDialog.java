package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import projekt.FormField;
import projekt.HospitalPharmacy;

public class EditFormFieldDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public boolean status=false;
	public String data[]=null;
	public int ile=0;
	public EditFormFieldDialog(HospitalPharmacyWindow hospitalPharmacyWindow, HospitalPharmacy hospitalPharmacy) {
		Connection con=hospitalPharmacy.con;
		
		PreparedStatement query4;
		try {
			query4 = (PreparedStatement) con.prepareStatement("select * from form_field where is_delivered=0");
			ResultSet result4=(ResultSet) query4.executeQuery();
			hospitalPharmacy.orderedMedicine.clear();
			while(result4.next())
			{				
					FormField form=new FormField();
					form.FormFieldId=Integer.parseInt(result4.getString(1));
					form.setOnWhenOrdered(result4.getString(2));
					form.setOrderedMedicineId(Integer.parseInt(result4.getString(3)));
					form.setPatientId(Integer.parseInt(result4.getString(4)));
					form.setAmount(Integer.parseInt(result4.getString(5)));
					form.setDelivered(false);
					form.setWhenOrderedDate(result4.getString(7));
					form.setUserWhoOrdered(result4.getString(8));					
					hospitalPharmacy.orderedMedicine.add(form);
					
				}}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
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
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel wybierzZamowienie = new JLabel("Wybierz zamowienie ktore chcesz edytowac:");
		wybierzZamowienie.setBounds(10, 20, 300, 34);
		contentPanel.add(wybierzZamowienie);
		
		//tu bedziemy jakos pobierac imie i nazwisko usera
		//String username="osoba";
		
		ArrayList<Integer> integerList = new ArrayList<Integer>();

		

//		for(int i=0; i<hospitalPharmacy.getOrderedMedicine().size(); i++)
//		{
//			data[i] = hospitalPharmacy.getOrderedMedicine().get(i).toString();
//			if(hospitalPharmacy.getOrderedMedicine().get(i).getUserWhoOrdered()==username)
//			{
//				integerList.add(i);
//			}
//		}
		
		
		PreparedStatement query1, query2;									
		int whoOrdered =101;
		
			try {
				query1 = (PreparedStatement) con.prepareStatement("select count(*) from form_field");
				ResultSet result=(ResultSet) query1.executeQuery();
				while(result.next()) ile=Integer.parseInt(result.getString(1))+1;
				//if(ile!=0) status=true;
				
					data = new String[ile];
					//query2 = (PreparedStatement) con.prepareStatement("select f.form_field_id, p.last_name, p.first_name, f.who_ordered_id   from form_field f, patients p where f.patient_id=p.patient_id");
					query2 = (PreparedStatement) con.prepareStatement("select f.form_field_id, p.last_name, p.first_name, f.who_ordered_id   from form_field f, patients p where f.patient_id=p.patient_id and is_delivered=0");
					
					ResultSet result2=(ResultSet) query2.executeQuery();
					int i=0;
					while(result2.next()){
						if(whoOrdered==Integer.parseInt(result2.getString(4))) integerList.add(Integer.parseInt(result2.getString(1)));
						data[i]="Id="+result2.getString(1)+", pacjent="+result2.getString(2)+" "+result2.getString(3)+", id zamawiaj¹cego="+result2.getString(4);
						i++;					
				}
				
			
			} catch (SQLException e1) {
				System.out.print(e1.getMessage());
			}
		
		JComboBox comboBoxOrder = new JComboBox(data);
		comboBoxOrder.setBounds(20, 60, 400, 20);
		contentPanel.add(comboBoxOrder);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					int id;
					public void actionPerformed(ActionEvent e) {
						
						
						
							if(ile!=0 ){
								PreparedStatement query;
								try {
									query = (PreparedStatement) con.prepareStatement("select f.form_field_id, p.last_name, p.first_name, f.who_ordered_id   from form_field f, patients p where f.patient_id=p.patient_id and who_ordered_id="+whoOrdered);
									ResultSet result2=(ResultSet) query.executeQuery();
									while(result2.next()){
//										for(int i=0; i<data.length; i++ ){
										String x="Id="+result2.getString(1)+", pacjent="+result2.getString(2)+" "+result2.getString(3)+", id zamawiaj¹cego="+whoOrdered;
										String y=comboBoxOrder.getSelectedItem().toString().substring(0,5);
											System.out.println(y);
											System.out.println(x.substring(0,5));
										if(y.equals(x.substring(0,5))) {
											//id=hospitalPharmacy.getOrderedMedicine().get(Integer.parseInt(result2.getString(1))).FormFieldId;
											
											id=Integer.parseInt(result2.getString(1));
											System.out.println(id);
											status=true;	
											break;
								//			}
										}
									}
								
								} catch (SQLException e1) {
									System.out.print(e1.getMessage());
								}
								
							}																			
						

						
						if(status==false) {
							JOptionPane.showMessageDialog(null, 
									"Nie mo¿esz edytowaæ zamówienia którego nie sk³ada³eœ",
									"B³¹d!",
									JOptionPane.ERROR_MESSAGE);
						}
						else {
							
								for(int i=0; i< hospitalPharmacy.getOrderedMedicine().size(); i++)
								{
									if(hospitalPharmacy.getOrderedMedicine().get(i).FormFieldId==id)
									{
										FormFieldDialog ffd = new FormFieldDialog(hospitalPharmacy.con, hospitalPharmacyWindow, hospitalPharmacy.getOrderedMedicine().get(i), hospitalPharmacy, false);
									}
								}

								dispose();
								
							
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
