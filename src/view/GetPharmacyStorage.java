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
import projekt.Medicine;

public class GetPharmacyStorage extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public GetPharmacyStorage(HospitalPharmacyWindow hospitalPharmacyWindow, HospitalPharmacy hospitalPharmacy) {
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					hospitalPharmacyWindow.setVisible(true);
				}
			});
		setBounds(100, 100, 600, 400);
		setVisible(true);
		hospitalPharmacyWindow.setVisible(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel magazyn = new JLabel("Magazyn apteki: ");
		magazyn.setBounds(10, 10, 300, 34);
		contentPanel.add(magazyn);
		
		//sciagniemy kilka lekow z bazy jakos
		//na razie wstawie na sztywno
//			Medicine lek1= new Medicine();
//			lek1.name="lek1";
//			lek1.quantity=5;
//			hospitalPharmacy.medicineList.add(lek1);
//			Medicine lek2= new Medicine();
//			lek2.name="lek2";
//			lek2.quantity=10;
//			hospitalPharmacy.medicineList.add(lek2);
//			Medicine lek3= new Medicine();
//			lek3.name="lek3";
//			lek3.quantity=15;
//			hospitalPharmacy.medicineList.add(lek3);
		
			JLabel[] labels=new JLabel[hospitalPharmacy.medicineList.size()];
		 
	        for(int i=0;i<hospitalPharmacy.medicineList.size();i++){
			labels[i] = new JLabel("Nazwa leku: "+ hospitalPharmacy.medicineList.get(i).name+
					" iloœæ: "+hospitalPharmacy.medicineList.get(i).quantity);					
			labels[i].setBounds(10, 40+20*i , 900, 14);
			contentPanel.add(labels[i]);
	        }
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				String id;
				public void actionPerformed(ActionEvent e) {
					//hospitalPharmacy.medicineList.clear();
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
