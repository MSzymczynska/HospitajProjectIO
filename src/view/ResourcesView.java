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
import projekt.ListsOperations;
import projekt.ProductQuantity;

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
		frame = new JFrame("Zasoby kuchni");
		frame.setBounds(100, 100, 450, 327);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 11, 414, 143);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
				Object columnNames[] = {"ID", "Nazwa", "Producent", "Data wa¿noœci", "Iloœæ"};
				Object rowData[][] = null;
				TableModel model = new DefaultTableModel(rowData, columnNames);
				
				// Uzupelniam tabele danymi
				List<ProductQuantity> quantities = DatabaseConnectionKuchnia.getProductQuantites();
				quantities = new ListsOperations().eraseMedicine(quantities);
				for(int i=0; i<quantities.size(); i++) {
					Product p = quantities.get(i).getProduct();
					Object nextRow[] = {p.getId(), p.getName(),
							p.getProducer(), p.getExpirationDate().toString(),
							quantities.get(i).getQuantity()};		
					((DefaultTableModel)model).addRow(nextRow);
				}
											
				table.setModel(model);		
				frame.repaint();
			}
		});
		
		JButton btnPokaZKrtk = new JButton("Poka\u017C z kr\u00F3tk\u0105 dat\u0105");
		btnPokaZKrtk.setBounds(234, 165, 190, 25);
		frame.getContentPane().add(btnPokaZKrtk);
		btnPokaZKrtk.addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent arg0) {
				List<ProductQuantity> quantities = DatabaseConnectionKuchnia.getProductQuantites();
				// dziala tylko jak przefiltruje sie dwa razy 
				quantities = new ListsOperations().eraseMedicine(quantities);
				quantities = new ListsOperations().getByDate(quantities);
				quantities = new ListsOperations().getByDate(quantities);
				
				Object columnNames[] = {"ID", "Nazwa", "Producent", "Data wa¿noœci", "Iloœæ"};
				Object rowData[][] = null;
				TableModel model = new DefaultTableModel(rowData, columnNames);
				for(int i=0; i<quantities.size(); i++) {
					Product p = quantities.get(i).getProduct();
					Object nextRow[] = {p.getId(), p.getName(),
							p.getProducer(), p.getExpirationDate().toString(),
							quantities.get(i).getQuantity()};		
					((DefaultTableModel)model).addRow(nextRow);
				}
											
				table.setModel(model);		
				frame.repaint();
			}
		}); 

		
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
		textField.setBounds(20, 202, 305, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Szukaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = textField.getText().toLowerCase();
				
				List<ProductQuantity> quantities = new ListsOperations().getSearched(s);
				Object columnNames[] = {"ID", "Nazwa", "Producent", "Data wa¿noœci", "Iloœæ"};
				Object rowData[][] = null;
				TableModel model = new DefaultTableModel(rowData, columnNames);
				for(int i=0; i<quantities.size(); i++) {
					Product p = quantities.get(i).getProduct();
					Object nextRow[] = {p.getId(), p.getName(),
							p.getProducer(), p.getExpirationDate().toString(),
							quantities.get(i).getQuantity()};		
					((DefaultTableModel)model).addRow(nextRow);
				}
											
				table.setModel(model);		
				frame.repaint();
				
			}
		});
		btnNewButton.setBounds(335, 201, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
