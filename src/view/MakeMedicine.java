package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import projekt.HospitalPharmacy;
import projekt.Medicine;

public class MakeMedicine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String description;
	String instruction;
	String composition;

	public MakeMedicine(HospitalPharmacyWindow hospitalPharmacyWindow, HospitalPharmacy hospitalPharmacy) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				hospitalPharmacyWindow.setVisible(true);
			}
		});
		setBounds(100, 100, 450, 370);
		setVisible(true);
		hospitalPharmacyWindow.setVisible(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		ArrayList<Medicine> m = hospitalPharmacy.medicineList;
		ArrayList<Medicine> m2 = new ArrayList<>();

		for(int i=0; i<m.size(); i++) {
			if(m.get(i).isCanCreate()) {
				m2.add(m.get(i));
			}
		}
		String medName[] = new String[m2.size()];
		for(int i=0; i<m2.size(); i++) {
			medName[i] = m2.get(i).name;
		}
		
		JLabel lblOpisLeku = new JLabel("Opis leku:");
		lblOpisLeku.setBounds(10, 42, 88, 14);
		contentPanel.add(lblOpisLeku);
		
		JTextPane txtpnOpisleku = new JTextPane();
		JTextPane txtpnInstrukcjatworzenia = new JTextPane();
		JTextPane txtpnSkladniki = new JTextPane();
		
		JComboBox comboBox = new JComboBox(medName);
		comboBox.setBounds(10, 11, 340, 20);
		contentPanel.add(comboBox);
		comboBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtpnOpisleku.setText(m2.get(comboBox.getSelectedIndex()).getDescription());
				txtpnInstrukcjatworzenia.setText(m2.get(comboBox.getSelectedIndex()).getInstructions());
				txtpnSkladniki.setText(m2.get(comboBox.getSelectedIndex()).getComposition());
			}
		});
		
		int selectedMed = comboBox.getSelectedIndex();
		description = m2.get(selectedMed).getDescription();
		instruction = m2.get(selectedMed).getInstructions();
		composition = m2.get(selectedMed).getComposition();
		
		txtpnOpisleku.setText(m2.get(0).getDescription());
		txtpnOpisleku.setBounds(10, 67, 414, 52);
		contentPanel.add(txtpnOpisleku);
		
		JLabel lblInstrukcjaTworzenia = new JLabel("Instrukcja tworzenia:");
		lblInstrukcjaTworzenia.setBounds(10, 130, 150, 14);
		contentPanel.add(lblInstrukcjaTworzenia);
		
		txtpnInstrukcjatworzenia.setText( m2.get(0).getInstructions());
		txtpnInstrukcjatworzenia.setBounds(10, 155, 414, 52);
		contentPanel.add(txtpnInstrukcjatworzenia);
		
		JLabel lblSkadniki = new JLabel("Sk\u0142adniki:");
		lblSkadniki.setBounds(10, 218, 150, 14);
		contentPanel.add(lblSkadniki);
		
		txtpnSkladniki.setText(m2.get(0).getComposition());
		txtpnSkladniki.setBounds(10, 243, 414, 52);
		contentPanel.add(txtpnSkladniki);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Stwórz");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String medicineName = comboBox.getSelectedItem().toString();
						for(int i=0; i<m.size();i++)
						{
							if(medicineName==m.get(i).name)
								m.get(i).quantity += 1;
						}
						hospitalPharmacyWindow.setVisible(true);
						System.out.print(hospitalPharmacy.madeMedicinesToString());
						dispose();
					}
				});
				okButton.setActionCommand("Stwórz");
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
