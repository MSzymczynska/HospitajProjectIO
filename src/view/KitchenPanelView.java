package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import projekt.NewRecipeForm;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KitchenPanelView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					KitchenPanelView window = new KitchenPanelView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public KitchenPanelView() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Panel kuchni");
		frame.setBounds(100, 100, 450, 422);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewRecipe = new JButton("Dodaj przepis");
		btnNewRecipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewRecipeForm nrf = new NewRecipeForm();
				nrf.run();
				
				
			}
		});
		btnNewRecipe.setBounds(120, 60, 90, 25);
		btnNewRecipe.setSize(200,50);
		frame.getContentPane().add(btnNewRecipe);
		
		JButton btnViewRecipe = new JButton("Szukaj przepisu");
		btnViewRecipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RecipeListView rlv = new RecipeListView();
				rlv.run();
			}
		});
		btnViewRecipe.setBounds(120, 120, 200, 50);
		btnNewRecipe.setSize(200,50);
		frame.getContentPane().add(btnViewRecipe);
		
		JLabel lblSiemankoWitajW = new JLabel("Siemanko witaj w mojej kuchni");
		lblSiemankoWitajW.setBounds(120, 35, 200, 14);
		frame.getContentPane().add(lblSiemankoWitajW);
		
		JButton btnNewOrder = new JButton("Z\u0142\u00F3\u017C zam\u00F3wienie");
		btnNewOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				NewOrderView nov = new NewOrderView();
				nov.run();
			}
		});
		btnNewOrder.setBounds(120, 180, 200, 50);
		btnNewRecipe.setSize(200,50);
		frame.getContentPane().add(btnNewOrder);
		
		JButton btnResources = new JButton("Zasoby");
		btnResources.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResourcesView rv = new ResourcesView();
				rv.run();
			}
		});
		btnResources.setBounds(120, 240, 200, 50);
		frame.getContentPane().add(btnResources);
		
		JButton btnExit = new JButton("Wyjœcie");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		btnExit.setBounds(120, 300, 200, 50);
		frame.getContentPane().add(btnExit);
		
	}
}
