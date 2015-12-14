package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	        	labels = new JLabel[hospitalPharmacy.getDeliveredMedicine().size()];
	        for(int i=0;i<hospitalPharmacy.getDeliveredMedicine().size();i++){
			labels[i] = new JLabel(hospitalPharmacy.getDeliveredMedicine().get(i).toString());			
			labels[i].setBounds(10, 40+20*i , 900, 14);
			contentPanel.add(labels[i]);
	        }}
	        else{
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
