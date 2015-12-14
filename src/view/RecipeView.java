package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

import projekt.Recipe;



public class RecipeView {

	private JFrame frame;
	Recipe recipe;

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			RecipeView window = new RecipeView(recipe);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the application.
	 */
	public RecipeView(Recipe r) {
		recipe = r;
		System.out.println(r.getName());
		System.out.println(r.getDescription());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Widok przepisu");
		frame.setBounds(100, 100, 450, 482);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelName = new JLabel(" ");
		labelName.setFont(new Font("Traditional Arabic", Font.PLAIN, 28));
		labelName.setBounds(10, 11, 300, 50);
		labelName.setText(recipe.getName());
		frame.getContentPane().add(labelName);
		
		JTextArea desc = new JTextArea();
		desc.setBounds(10,100, 170, 69);
		desc.setText(recipe.getDescription());
		frame.getContentPane().add(desc);
		
		for(int i=0; i<recipe.getMealFeatures().size(); i++) {
			System.out.println(recipe.getMealFeatures().get(i).getName());
		}
		
		JLabel lblWartoKaloryczna = new JLabel("Warto\u015B\u0107 kaloryczna (kcal):");
		lblWartoKaloryczna.setText(lblWartoKaloryczna.getText()+" "+recipe.getCalorificValue());
		lblWartoKaloryczna.setBounds(190, 100, 234, 14);
		frame.getContentPane().add(lblWartoKaloryczna);
		
		JLabel lblCechy = new JLabel("Cechy:");
		lblCechy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCechy.setBounds(194, 192, 230, 22);
		frame.getContentPane().add(lblCechy);
		
		
		
		JLabel lblProdukty = new JLabel("Produkty:");
		lblProdukty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProdukty.setBounds(10, 186, 170, 34);
		frame.getContentPane().add(lblProdukty);
		
		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOpis.setBounds(10, 60, 170, 34);
		frame.getContentPane().add(lblOpis);
		
		
		
		JList<String> list = new JList<String>();
		list.setBounds(190, 221, 234, 100);
		list.setListData(recipe.getFeatures());
		frame.getContentPane().add(list);
		
		JList<String> list_1 = new JList<String>();
		list_1.setBounds(10, 225, 170, 207);
		list_1.setListData(recipe.getProductArray());
		frame.getContentPane().add(list_1);
		
		JButton close = new JButton("Ok");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		close.setBounds(360, 390, 60, 34);
		frame.getContentPane().add(close);
		
	}

}
