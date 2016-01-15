package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import projekt.DatabaseConnectionKuchnia;
import projekt.Product;

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
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setBounds(10, 11, 414, 143);	
		frame.getContentPane().add(scrollPane);
				
		JButton btnPokaWszystkie = new JButton("Poka\u017C wszystkie");
		btnPokaWszystkie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// sciagamy produkty z bazy i wrzucamy je do tabelki
				
			}
		});
		btnPokaWszystkie.setBounds(20, 165, 190, 25);
		frame.getContentPane().add(btnPokaWszystkie);
		btnPokaWszystkie.addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent arg0) {
				Object columnNames[] = {"ID", "Nazwa", "Producent", "Data wa¿noœci"};
				Object rowData[][] = { {"ID", "Nazwa", "Producent", "Data wa¿noœci"} };
				TableModel model = new DefaultTableModel(rowData, columnNames);
				
				new DatabaseConnectionKuchnia();
				// Uzupelniam tabele danymi
				List<Product> products = DatabaseConnectionKuchnia.getProducts();
				for(int i=0; i<products.size(); i++) {
					Object nextRow[] = {products.get(i).getId(), products.get(i).getName(),
							products.get(i).getProducer(), products.get(i).getExpirationDate().toString()};		
					((DefaultTableModel)model).addRow(nextRow);
				}
				
							
				table.setModel(model);
				
				frame.repaint();
			}
		});
		
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
