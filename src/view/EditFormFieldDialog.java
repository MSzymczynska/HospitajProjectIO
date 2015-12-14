package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projekt.HospitalPharmacy;

public class EditFormFieldDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public EditFormFieldDialog(HospitalPharmacyWindow hospitalPharmacyWindow, HospitalPharmacy hospitalPharmacy) {
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
		String username="osoba";
		
		ArrayList<Integer> integerList = new ArrayList<Integer>();

		
		String data[] = new String[ hospitalPharmacy.getOrderedMedicine().size()];
		for(int i=0; i<hospitalPharmacy.getOrderedMedicine().size(); i++)
		{
			data[i] = hospitalPharmacy.getOrderedMedicine().get(i).toString();
			if(hospitalPharmacy.getOrderedMedicine().get(i).getUserWhoOrdered()==username)
			{
				integerList.add(i);
			}
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
					String id;
					public void actionPerformed(ActionEvent e) {
						
						boolean status=false;
						for(int i=0; i<data.length; i++ ){
							if(integerList.size()!=0 ){
							if(comboBoxOrder.getSelectedIndex()==integerList.get(i)) 
								id=hospitalPharmacy.getOrderedMedicine().get(i).FormFieldId;
								status=true;	
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
										FormFieldDialog ffd = new FormFieldDialog(hospitalPharmacyWindow, hospitalPharmacy.getOrderedMedicine().get(i), hospitalPharmacy, false);
									}
								}
								//EditFormField effd= new EditFormField(hospitalPharmacyWindow,hospitalPharmacy);
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
