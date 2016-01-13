package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import projekt.FormField;
import projekt.HospitalPharmacy;


public class SeeOrdersDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public SeeOrdersDialog(HospitalPharmacyWindow hospitalPharmacyWindow, HospitalPharmacy hospitalPharmacy, boolean isDelivered) {
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					hospitalPharmacyWindow.setVisible(true);
				}
			});
		Connection con=hospitalPharmacy.con;
		setBounds(100, 100, 900, 400);
		setVisible(true);
		hospitalPharmacyWindow.setVisible(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel zamowienia;
		if(isDelivered) zamowienia = new JLabel("Zrealizowane zamówienia: ");
		else zamowienia = new JLabel("Zamówienia w toku: ");
		zamowienia.setBounds(10, 10, 300, 34);
		contentPanel.add(zamowienia);

	        JLabel[] labels;//=new JLabel[hospitalPharmacy.getOrderedMedicine().size()];
	        if(isDelivered){
	        	
	        	
	    		
	    		PreparedStatement query4;
	    		try {
	    			query4 = (PreparedStatement) con.prepareStatement("select * from form_field where is_delivered=1");
	    			ResultSet result4=(ResultSet) query4.executeQuery();
	    			hospitalPharmacy.deliveredMedicines.clear();
	    			while(result4.next())
	    			{				
	    					FormField form=new FormField();
	    					form.FormFieldId=Integer.parseInt(result4.getString(1));
	    					form.setOnWhenOrdered(result4.getString(2));
	    					form.setOrderedMedicineId(Integer.parseInt(result4.getString(3)));
	    					form.setPatientId(Integer.parseInt(result4.getString(4)));
	    					form.setAmount(Integer.parseInt(result4.getString(5)));
	    					form.setDelivered(true);
	    					form.setWhenOrderedDate(result4.getString(7));
	    					form.setUserWhoOrdered(result4.getString(8));
	    					
	    					hospitalPharmacy.deliveredMedicines.add(form);
	    					
	    				}}
	    			catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    			}
	        	labels = new JLabel[hospitalPharmacy.deliveredMedicines.size()];
	        for(int i=0;i<hospitalPharmacy.deliveredMedicines.size();i++){
			labels[i] = new JLabel(hospitalPharmacy.deliveredMedicines.get(i).toString());			
			labels[i].setBounds(10, 40+20*i , 900, 14);
			contentPanel.add(labels[i]);
	        }}
	        else{
	    		
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
	        labels = new JLabel[hospitalPharmacy.getOrderedMedicine().size()];
        	for(int i=0;i<hospitalPharmacy.getOrderedMedicine().size();i++){
			labels[i] = new JLabel(hospitalPharmacy.getOrderedMedicine().get(i).toString());			
			labels[i].setBounds(10, 40+20*i , 900, 14);
			contentPanel.add(labels[i]);
	        }
	               
		}
		
	        JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					String id;
					public void actionPerformed(ActionEvent e) {
						hospitalPharmacyWindow.setVisible(true);
						dispose();						
						}					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		
	}
}
