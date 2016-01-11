package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import projekt.HospitalPharmacy;
import projekt.StorageMainPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel frame = new MainPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	StorageMainPanel storageMainPanel;
	public HospitalPharmacy hp;
	public Connection con;
	
	public MainPanel() {
		con=databaseConnection();
		hp = new HospitalPharmacy((com.mysql.jdbc.Connection) con);
		hp.przykladoweDane();
		storageMainPanel=new StorageMainPanel(hp);
		
//		try {
//			PreparedStatement cos=(PreparedStatement) con.prepareStatement("select * from products");
//			ResultSet result=(ResultSet) cos.executeQuery();
//			while(result.next())
//			{
//				System.out.println( result.getString(1) );
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnApteka = new JButton("Apteka");
		btnApteka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HospitalPharmacyWindow hpw = new HospitalPharmacyWindow(hp, storageMainPanel);
			
			}
		});
		btnApteka.setBounds(95, 38, 224, 23);
		contentPane.add(btnApteka);
		
		JButton btnMagazyn = new JButton("Magazyn");
		btnMagazyn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameStorage storage = new JFrameStorage(storageMainPanel);
			} 
		});
		btnMagazyn.setBounds(95, 80, 224, 23);
		contentPane.add(btnMagazyn);
		
		JButton btnPanelAdministratora = new JButton("Panel administratora");
		btnPanelAdministratora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminJFrame ajf=new adminJFrame();
			}
		});
		btnPanelAdministratora.setBounds(95, 122, 224, 23);
		contentPane.add(btnPanelAdministratora);
		
		JButton btnNewButton = new JButton("Kuchnia");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KitchenPanelView window = new KitchenPanelView();
			}
		});
		btnNewButton.setBounds(92, 165, 227, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Karta pacjenta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatientsCardWindow window = new PatientsCardWindow();
			}
		});
		btnNewButton_1.setBounds(95, 212, 224, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnRezerwacjeduuryurlopy = new JButton("Rezerwacje/du\u017Cury/urlopy");
		btnRezerwacjeduuryurlopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserInterface UIReservation = new UserInterface();
			}
		});
		btnRezerwacjeduuryurlopy.setBounds(95, 259, 224, 23);
		contentPane.add(btnRezerwacjeduuryurlopy);
	}
	
	public Connection databaseConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println( e.getMessage() );
			return null;
		}
		
		String host = "jdbc:mysql://s1.kolodziej.it:3306/Hospital";
	       String user = "hospital";
	       String password = "c@2ea(*1FsE10cd91F7h";
		try {
			
			Connection con = DriverManager.getConnection( host, user, password );
			return con;
		} catch (SQLException e) {
			//TODO 
			System.out.println( e.getMessage() );
			return null;
		}		
	}
}
