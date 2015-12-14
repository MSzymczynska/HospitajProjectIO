package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import projekt.DatabaseConnectionKartaPacjenta;
import projekt.Functions;


public class ChoosePatientWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8735152495373764686L;
	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private PopupDialogBox dialogBox;
	private PatientsCardView PatientsCardView;

	public ChoosePatientWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		firstName = new JTextField();
		firstName.setBounds(205, 64, 165, 20);
		contentPane.add(firstName);
		firstName.setColumns(10);

		lastName = new JTextField();
		lastName.setBounds(205, 109, 165, 20);
		contentPane.add(lastName);
		lastName.setColumns(10);

		JLabel lblWpiszImi = new JLabel("Wpisz imi\u0119");
		lblWpiszImi.setBounds(39, 67, 138, 14);
		contentPane.add(lblWpiszImi);

		JLabel lblWpiszNazwisko = new JLabel("Wpisz nazwisko");
		lblWpiszNazwisko.setBounds(39, 112, 138, 14);
		contentPane.add(lblWpiszNazwisko);

		JButton btnZaakceptuj = new JButton("Zaakceptuj");
		btnZaakceptuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (DatabaseConnectionKartaPacjenta.checkExistUser(firstName.getText(), lastName.getText())){
						PatientsCardView card = new PatientsCardView(firstName.getText(), lastName.getText());
						card.setVisible(true);
					}
					else {
						dialogBox = new PopupDialogBox(Functions.formatString("Nie ma takiego pacjenta"));
						dialogBox.setVisible(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnZaakceptuj.setBounds(115, 164, 174, 23);
		contentPane.add(btnZaakceptuj);
	}

}
