package view;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResourcesView {

	private JFrame frame;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			ResourcesView window = new ResourcesView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the application.
	 */
	public ResourcesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 327);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 11, 414, 143);
		frame.getContentPane().add(table);
		
		JButton btnPokaWszystkie = new JButton("Poka\u017C wszystkie");
		btnPokaWszystkie.setBounds(20, 165, 190, 25);
		frame.getContentPane().add(btnPokaWszystkie);
		
		JButton btnPokaZKrtk = new JButton("Poka\u017C z kr\u00F3tk\u0105 dat\u0105");
		btnPokaZKrtk.setBounds(234, 165, 190, 25);
		frame.getContentPane().add(btnPokaZKrtk);
		
		JButton btnWyjd = new JButton("Wyjd\u017A");
		btnWyjd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		btnWyjd.setBounds(175, 254, 89, 23);
		frame.getContentPane().add(btnWyjd);
		
		textField = new JTextField();
		textField.setBounds(20, 202, 305, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Szukaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(335, 201, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
