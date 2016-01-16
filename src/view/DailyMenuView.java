package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projekt.DatabaseConnectionKuchnia;
import projekt.ListsOperations;
import projekt.Menu;
import projekt.Product;
import projekt.ProductQuantity;
import projekt.Recipe;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;

public class DailyMenuView extends JFrame {

	private JPanel contentPane;
	private Label textField;
	private Label textField_1;
	private Label textField_2;
	private Label textField_3;
	private ArrayList<Recipe> allMeals;
	Menu menu;

	/**
	 * Launch the application.
	 */

	public void run() {
		allMeals = DatabaseConnectionKuchnia.getRecipes();
		try {
			DailyMenuView frame = new DailyMenuView();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		lblniadanie.setBounds(10, 65, 94, 14);
		contentPane.add(lblniadanie);
		
		JLabel lblObiad = new JLabel("Obiad");
		lblObiad.setBounds(10, 104, 94, 14);
		contentPane.add(lblObiad);
		
		JLabel lblKolacja = new JLabel("Kolacja");
		lblKolacja.setBounds(10, 141, 94, 14);
		contentPane.add(lblKolacja);
		
		textField = new Label();
		textField.setBounds(158, 62, 203, 20);
		contentPane.add(textField);
		
		textField_1 = new Label();
		textField_1.setBounds(158, 101, 203, 20);
		contentPane.add(textField_1);
		
		textField_2 = new Label();
		textField_2.setBounds(158, 138, 203, 20);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Uzupe\u0142nij menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillMenu();
			}
		});
		btnNewButton.setBounds(58, 227, 303, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 29, 94, 14);
		contentPane.add(lblData);
		
		textField_3 = new Label();
		textField_3.setBounds(158, 26, 203, 20);
		contentPane.add(textField_3);
	}
	
	public void fillMenu() {
		menu = DatabaseConnectionKuchnia.getDailyMenu();
		if(menu == null) {
			generateMenu();
		}
		else {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = Calendar.getInstance().getTime();
			String today = df.format(date);
			textField_3.setText(today);
			System.out.println(menu.getBreakfast());
			textField.setText(menu.getBreakfast());
			textField_1.setText(menu.getLunch());
			textField_2.setText(menu.getDinner());
			contentPane.repaint();
		}
	}
	
	public void generateMenu() {
		allMeals = DatabaseConnectionKuchnia.getRecipes();
		menu = new Menu();
		int size = allMeals.size();
		Random random = new Random();
		int x = Math.abs(random.nextInt(size-1));
		menu.setBreakfast(allMeals.get(x));
		x = Math.abs(random.nextInt(size-1));
		menu.setLunch(allMeals.get(x));
		x = Math.abs(random.nextInt(size-1));
		menu.setDinner(allMeals.get(x));
		DatabaseConnectionKuchnia.insertDailyMenu(menu);
		
	}
	

}
