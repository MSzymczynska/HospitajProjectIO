package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PatientsCardWindow {

	private JFrame frame;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PatientsCardWindow window = new PatientsCardWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public PatientsCardWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JButton btnOtwrzKartPacjenta = new JButton("Otw\u00F3rz kart\u0119 pacjenta");
		btnOtwrzKartPacjenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoosePatientWindow newWindow = new ChoosePatientWindow();
				newWindow.setVisible(true);
			}
		});
		btnOtwrzKartPacjenta.setBounds(10, 11, 188, 23);
		frame.getContentPane().add(btnOtwrzKartPacjenta);

		JButton btnDodajPacjenta = new JButton("Dodaj Pacjenta");
		btnDodajPacjenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatePatient card = new CreatePatient();
				card.setVisible(true);
			}
		});
		btnDodajPacjenta.setBounds(10, 45, 188, 23);
		frame.getContentPane().add(btnDodajPacjenta);

		JButton btnUsuPacjenta = new JButton("Usu\u0144 Pacjenta");
		btnUsuPacjenta.setBounds(10, 79, 188, 23);
		frame.getContentPane().add(btnUsuPacjenta);
	}
}
