package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import projekt.DatabaseConnectionKuchnia;
import projekt.KitchenPanel;
import projekt.ListsOperations;
import projekt.MealFeature;
import projekt.Product;
import projekt.ProductListModel;
import projekt.ProductQuantity;
import projekt.Recipe;
import javax.swing.JComboBox; 
public class NewRecipeForm {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextArea textAreaOpis;
	private JLabel lblCalValue;
	private JTextField textField;
	private JLabel lblProducts;
	private JTextField textField_2;
	Recipe recipe = new Recipe();
	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			NewRecipeForm window = new NewRecipeForm();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the application.
	 */
	public NewRecipeForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Nowy przepis");
		frame.setBounds(100, 100, 450, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Nazwa");
		lblName.setBounds(21, 33, 134, 25);
		frame.getContentPane().add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(191, 30, 211, 25);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(21, 88, 134, 25);
		frame.getContentPane().add(lblOpis);
		

		textAreaOpis = new JTextArea();
		textAreaOpis.setBounds(191, 73, 211, 50);
		textAreaOpis.setWrapStyleWord(true);
		textAreaOpis.setLineWrap(true);
		
		frame.getContentPane().add(textAreaOpis);
		
		
		lblCalValue = new JLabel("Warto\u015B\u0107 kaloryczna");
		lblCalValue.setBounds(21, 154, 134, 25);
		frame.getContentPane().add(lblCalValue);
		
		textField = new JTextField();
		textField.setBounds(191, 151, 166, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblKcal = new JLabel("kcal");
		lblKcal.setBounds(378, 154, 46, 25);
		frame.getContentPane().add(lblKcal);
		
		lblProducts = new JLabel("Produkty");
		lblProducts.setBounds(21, 210, 134, 25);
		frame.getContentPane().add(lblProducts);
		
		JTextArea textAreaProducts = new JTextArea();
		textAreaProducts.setColumns(10);
		textAreaProducts.setBounds(191, 210, 211, 50);
		textAreaProducts.setEditable(false);
		frame.getContentPane().add(textAreaProducts);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("dla diabetyk\u00F3w");
		chckbxNewCheckBox.setBounds(191, 441, 144, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("bez glutenu");
		chckbxNewCheckBox_1.setBounds(191, 467, 144, 23);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("dla dzieci");
		chckbxNewCheckBox_2.setBounds(191, 363, 144, 23);
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("dla sercowc\u00F3w");
		chckbxNewCheckBox_3.setBounds(191, 389, 144, 23);
		frame.getContentPane().add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("dla kobiet w ci\u0105\u017Cy");
		chckbxNewCheckBox_4.setBounds(191, 415, 144, 23);
		frame.getContentPane().add(chckbxNewCheckBox_4);
		
		JLabel lblCechy = new JLabel("Cechy");
		lblCechy.setBounds(21, 419, 134, 25);
		frame.getContentPane().add(lblCechy);
		
		JLabel lblDodajProdukt = new JLabel("Dodaj produkt");
		lblDodajProdukt.setBounds(21, 302, 134, 20);
		frame.getContentPane().add(lblDodajProdukt);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(191, 299, 144, 25);	
		ProductListModel cbm = new ProductListModel();
		cbm.setProducts(new ListsOperations().productsToProductArray());
		comboBox.setModel(cbm);
		frame.getContentPane().add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(349, 299, 53, 25);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					// zbieranie danych.
					// nazwa, opis, kalorie
					Integer cal = new Integer(textField.getText());
					String name = textFieldName.getText();
					String desc = textAreaOpis.getText();
					recipe.setCalorificValue(cal);
					recipe.setName(name);
					recipe.setDescription(desc);
					
					
					if(chckbxNewCheckBox.isSelected()) {
						recipe.addMealFeature(new MealFeature("dla diabetyków"));
					}
					
					if(chckbxNewCheckBox_1.isSelected()) {
						recipe.addMealFeature(new MealFeature("bez glutenu"));
					}
					
					if(chckbxNewCheckBox_2.isSelected()) {
						recipe.addMealFeature(new MealFeature("dla dzieci"));
					}
					
					if(chckbxNewCheckBox_3.isSelected()) {
						recipe.addMealFeature(new MealFeature("dla sercowców"));
					}
					
					if(chckbxNewCheckBox_4.isSelected()) {
						recipe.addMealFeature(new MealFeature("dla ciê¿arnych"));
					}
					
				    
				    Object[] options = { "Tak", "Nie" };
				    int reply = JOptionPane.showOptionDialog(null, "Czy potwierdzasz dodanie przepisu?", 
				    		"Potwierdzenie", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
				    		null, options, options[0]);
				    if (reply == 0) {
				    	KitchenPanel.getInstance().addRecipe(recipe);
			        	frame.dispose();
				    }
					
					DatabaseConnectionKuchnia.uploadNewRecipe(recipe);
				} catch(NumberFormatException nfe) {}
				
			}
		});

		btnDodaj.setBounds(120, 536, 120, 40);
		frame.getContentPane().add(btnDodaj);
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		btnAnuluj.setBounds(250, 536, 120, 40);
		frame.getContentPane().add(btnAnuluj);
		
		JButton btnDodaj_1 = new JButton("Dodaj");
		btnDodaj_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Product p = (Product) comboBox.getSelectedItem();
				System.out.println(p);
				try {
					ProductQuantity pq = new ProductQuantity(p, new Integer(textField_2.getText()));
					recipe.addProduct(pq);
					textField_2.setText("");
				} catch(NumberFormatException nfe) {}
				
				
				textAreaProducts.setText(recipe.toString());
			}
		});
		btnDodaj_1.setBounds(317, 330, 87, 23);
		frame.getContentPane().add(btnDodaj_1);
		

	}
	}
