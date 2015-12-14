package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class adminJFrame extends JFrame {

	private JPanel contentPane;
	 private JTabbedPane tabbedPane;
	    private JPanel adminPanel;

	/**
	 * Create the frame.
	 */
	public adminJFrame() {
		
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setTitle("System zarz¹dzania szpitalem");
        
        tabbedPane = new JTabbedPane();
        adminPanel = new AdminPanel(); 

        tabbedPane.addTab("Zarz¹dzanie u¿ytkownikami", adminPanel);
        this.add(tabbedPane, BorderLayout.NORTH);
        
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 950, 600);
		

	}

}
