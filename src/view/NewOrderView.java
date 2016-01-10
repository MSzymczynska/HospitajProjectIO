package view;


import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewOrderView {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			NewOrderView window = new NewOrderView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public NewOrderView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Nowe zamówienie");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		List list = new List();
		list.setBounds(195, 29, 136, 88);
		frame.getContentPane().add(list);
		
		JLabel lblProdukt = new JLabel("Produkt");
		lblProdukt.setBounds(51, 56, 67, 14);
		frame.getContentPane().add(lblProdukt);
		
		textField = new JTextField();
		textField.setBounds(195, 156, 136, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIlo = new JLabel("Ilo\u015B\u0107");
		lblIlo.setBounds(51, 159, 46, 14);
		frame.getContentPane().add(lblIlo);
		
		JButton btnZZamwienie = new JButton("Z\u0142\u00F3\u017C zam\u00F3wienie");
		btnZZamwienie.setBounds(65, 227, 146, 23);
		frame.getContentPane().add(btnZZamwienie);
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnuluj.setBounds(221, 227, 146, 23);
		frame.getContentPane().add(btnAnuluj);
	}
}
