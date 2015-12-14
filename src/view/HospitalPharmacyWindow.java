package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projekt.FormField;
import projekt.HospitalPharmacy;
import projekt.StorageMainPanel;
import view.GetPharmacyStorage;
import view.SeeOrdersDialog;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HospitalPharmacyWindow extends JFrame{

	private JPanel contentPane;
	private HospitalPharmacyWindow hospitalPharmacyWindow = this;

	public HospitalPharmacyWindow(HospitalPharmacy hospitalPharmacy, StorageMainPanel storageMainPanel) {
		//hospitalPharmacy.przyk³adoweDane();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton newOrderButton = new JButton("Zam\u00F3w lek");
		newOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormField ff = new FormField();
				FormFieldDialog ffd = new FormFieldDialog(hospitalPharmacyWindow, ff, hospitalPharmacy, true);
			}
		});
		newOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		newOrderButton.setBounds(10, 11, 133, 54);
		contentPane.add(newOrderButton);
		
		JButton editOrderButton = new JButton("Edytuj zam\u00F3wienie");
		editOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditFormFieldDialog effd= new EditFormFieldDialog(hospitalPharmacyWindow,hospitalPharmacy);
				
			}
		});
		editOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		editOrderButton.setBounds(153, 11, 271, 54);
		contentPane.add(editOrderButton);
		
		JButton showOrderButton = new JButton("Przejrzyj zam\u00F3wienia");
		showOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeOrdersDialog sO= new SeeOrdersDialog(hospitalPharmacyWindow,hospitalPharmacy,false);
			}
		});
		showOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		showOrderButton.setBounds(10, 76, 271, 54);
		contentPane.add(showOrderButton);
		
		JButton showArchiveButton = new JButton("Przejrzyj archiwum");
		showArchiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeOrdersDialog sO= new SeeOrdersDialog(hospitalPharmacyWindow,hospitalPharmacy,true);
				
			}
		});
		showArchiveButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		showArchiveButton.setBounds(10, 141, 209, 54);
		contentPane.add(showArchiveButton);
		
		JButton makeMedicineButton = new JButton("Stw\u00F3rz lek");
		makeMedicineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MakeMedicine showInstructionDialog = new MakeMedicine(hospitalPharmacyWindow, hospitalPharmacy);
			}
		});
		makeMedicineButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		makeMedicineButton.setBounds(291, 76, 133, 54);
		contentPane.add(makeMedicineButton);
		
		JButton btnZobaczStanApteki = new JButton("Zobacz stan apteki");
		btnZobaczStanApteki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetPharmacyStorage gps= new GetPharmacyStorage(hospitalPharmacyWindow,hospitalPharmacy);
				
			}
		});
		btnZobaczStanApteki.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnZobaczStanApteki.setBounds(229, 141, 195, 54);
		contentPane.add(btnZobaczStanApteki);
		
		JButton btnPrzejdDoPanelu = new JButton("Wyjd\u017A");
		btnPrzejdDoPanelu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPrzejdDoPanelu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPrzejdDoPanelu.setBounds(215, 274, 209, 41);
		contentPane.add(btnPrzejdDoPanelu);
		
		JButton btnZamwZMagazynu = new JButton("Zam\u00F3w z magazynu");
		btnZamwZMagazynu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFromStorageDialog ofsd= new OrderFromStorageDialog(hospitalPharmacyWindow,hospitalPharmacy,storageMainPanel);
			}
		});
		btnZamwZMagazynu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnZamwZMagazynu.setBounds(10, 206, 195, 54);
		contentPane.add(btnZamwZMagazynu);
		
		repaint();
	}
}
