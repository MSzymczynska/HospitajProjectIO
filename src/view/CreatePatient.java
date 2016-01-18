package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projekt.DatabaseConnectionKartaPacjenta;
import projekt.Functions;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import view.PopupDialogBox;


public class CreatePatient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6842457123910807413L;
	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JTextField weight;
	private JTextField heiht;
	private JTextField age;
	private PopupDialogBox dialogBox;
	private JTextField phonenumber;
	private JTextField menu;

	public CreatePatient() {
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		firstName = new JTextField();
		firstName.setBounds(139, 8, 165, 20);
		contentPane.add(firstName);
		firstName.setColumns(10);

		lastName = new JTextField();
		lastName.setBounds(139, 39, 165, 20);
		contentPane.add(lastName);
		lastName.setColumns(10);
		
		email = new JTextField();
		email.setBounds(139, 75, 165, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		phonenumber = new JTextField();
		phonenumber.setColumns(10);
		phonenumber.setBounds(139, 106, 165, 20);
		contentPane.add(phonenumber);


		menu = new JTextField();
		menu.setColumns(10);
		menu.setBounds(139, 137, 165, 20);
		contentPane.add(menu);
		
		JLabel lblWybierzManuDnia = new JLabel("Wybierz menu dnia");
		lblWybierzManuDnia.setBounds(39, 140, 138, 14);
		contentPane.add(lblWybierzManuDnia);

		JLabel lblWpiszImi = new JLabel("Wpisz imi\u0119");
		lblWpiszImi.setBounds(39, 11, 138, 14);
		contentPane.add(lblWpiszImi);

		JLabel lblWpiszNazwisko = new JLabel("Wpisz nazwisko");
		lblWpiszNazwisko.setBounds(39, 42, 138, 14);
		contentPane.add(lblWpiszNazwisko);
		
		JLabel lblWpiszEmail = new JLabel("Wpisz Email");
		lblWpiszEmail.setBounds(39, 78, 138, 14);
		contentPane.add(lblWpiszEmail);
		
		JLabel lblWpisznumer = new JLabel("Wpisz numer tel");
		lblWpisznumer.setBounds(39, 109, 138, 14);
		contentPane.add(lblWpisznumer);
		


		JButton btnZaakceptuj = new JButton("Zaakceptuj");
		btnZaakceptuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!DatabaseConnectionKartaPacjenta.checkExistUser(firstName.getText(), lastName.getText()))
						DatabaseConnectionKartaPacjenta.createPatient(firstName.getText(), lastName.getText(), email.getText(), phonenumber.getText(), menu.getText());
														
					else {
						dialogBox = new PopupDialogBox(Functions.formatString("Istnieje ju¿ pacjent o wybranym imieniu i nazwisku"));
						dialogBox.setVisible(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnZaakceptuj.setBounds(250, 233, 174, 23);
		contentPane.add(btnZaakceptuj);
		
		
	}
}
