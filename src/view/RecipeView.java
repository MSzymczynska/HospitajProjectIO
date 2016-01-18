package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import projekt.DatabaseConnectionKuchnia;
import projekt.MealFeature;
import projekt.Product;
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
		desc.setBounds(10,100, 170, 25);
		desc.setEditable(false);
		desc.setText(recipe.getDescription());
		frame.getContentPane().add(desc);
			
		JLabel lblWartoKaloryczna = new JLabel("Warto\u015B\u0107 kaloryczna (kcal):");
		lblWartoKaloryczna.setText(lblWartoKaloryczna.getText()+" "+recipe.getCalorificValue());
		lblWartoKaloryczna.setBounds(190, 100, 234, 25);
		frame.getContentPane().add(lblWartoKaloryczna);
		
		JLabel lblCechy = new JLabel("Cechy:");
		lblCechy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCechy.setBounds(194, 192, 230, 22);
		frame.getContentPane().add(lblCechy);
			
		JLabel lblProdukty = new JLabel("Produkty:");
		lblProdukty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProdukty.setBounds(10, 186, 170, 34);
		frame.getContentPane().add(lblProdukty);
		
		JLabel lblOpis = new JLabel("ID:");
		lblOpis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOpis.setBounds(10, 60, 170, 20);
		frame.getContentPane().add(lblOpis);
		
		JList<String> list = new JList<String>();
		list.setBounds(190, 221, 234, 100);
		ArrayList<MealFeature> mf = DatabaseConnectionKuchnia.getMealFeatures(recipe);
		String mfs[] = new String[mf.size()];
		for(int i=0; i<mf.size(); i++) {
			mfs[i] = mf.get(i).toString();
		}
		list.setListData(mfs);
		frame.getContentPane().add(list);
				
		JList<String> list_1 = new JList<String>();
		ArrayList<Product> pr = DatabaseConnectionKuchnia.getIngredients(recipe);
		String prs[] = new String[pr.size()];
		for(int i=0; i<pr.size(); i++) {
			prs[i] = pr.get(i).getName();
		}
		list_1.setListData(prs);
		
		JScrollPane scrollPane2 = new JScrollPane(list_1);
		scrollPane2.setBounds(10, 225, 170, 207);
		frame.getContentPane().add(scrollPane2);
		
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
