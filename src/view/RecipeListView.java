package view;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import projekt.KitchenPanel;
import projekt.Recipe;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecipeListView {

	private JFrame frame;
	private JTextArea list;

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			RecipeListView window = new RecipeListView();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Create the application.
	 */
	public RecipeListView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Lista przepisów");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		list = new JTextArea();
		ArrayList<Recipe> recipes = KitchenPanel.getInstance().getRecipeList();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<recipes.size(); i++) {		
			sb.append(i+1);
			sb.append(": ");
			sb.append(recipes.get(i).getName());
			sb.append('\n');
		}
		frame.getContentPane().setLayout(null);
		list.setText(sb.toString());
		
		list.setBounds(10, 11, 202, 239);		
		frame.getContentPane().add(list);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(222, 71, 202, 20);
		frame.getContentPane().add(comboBox);
		
		for(int i=0; i<recipes.size(); i++){
			comboBox.addItem(recipes.get(i).getName());
		}
		
		JLabel lblNewLabel = new JLabel("Wybierz przepis");
		lblNewLabel.setBounds(222, 35, 202, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Wy\u015Bwietl");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chosen = comboBox.getSelectedItem().toString();
				Recipe recipe = new Recipe();
				for(int i=0; i<recipes.size(); i++) {
					if(recipes.get(i).getName()==chosen) {
						recipe = recipes.get(i);
					}
				}
				RecipeView rv = new RecipeView(recipe);
				rv.run();
			}
		});
		btnNewButton.setBounds(222, 102, 202, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Zamknij");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setBounds(222, 227, 202, 23);
		frame.getContentPane().add(button);
	}
}
