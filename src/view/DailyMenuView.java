package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DailyMenuView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DailyMenuView frame = new DailyMenuView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DailyMenuView() {
		setTitle("Dzisiejsze menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblniadanie = new JLabel("\u015Aniadanie");
		lblniadanie.setBounds(58, 65, 46, 14);
		contentPane.add(lblniadanie);
		
		JLabel lblObiad = new JLabel("Obiad");
		lblObiad.setBounds(58, 104, 46, 14);
		contentPane.add(lblObiad);
		
		JLabel lblKolacja = new JLabel("Kolacja");
		lblKolacja.setBounds(58, 141, 46, 14);
		contentPane.add(lblKolacja);
		
		textField = new JTextField();
		textField.setBounds(158, 62, 203, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 101, 203, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(158, 138, 203, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Generuj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(58, 227, 303, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(58, 29, 46, 14);
		contentPane.add(lblData);
		
		textField_3 = new JTextField();
		textField_3.setBounds(158, 26, 203, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}
}
