package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(100, 100, 234, 265);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JButton btnOtwrzKartPacjenta = new JButton("Otw\u00F3rz kart\u0119 pacjenta");
		btnOtwrzKartPacjenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoosePatientWindow newWindow = new ChoosePatientWindow();
				newWindow.setVisible(true);
			}
		});
		btnOtwrzKartPacjenta.setBounds(10, 11, 198, 50);
		frame.getContentPane().add(btnOtwrzKartPacjenta);


		JButton btnUsuPacjenta = new JButton("Otw\u00F3rz list\u0119 pacjent\u00F3w");
		btnUsuPacjenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientsListView list = new PatientsListView();
				list.setVisible(true);				
			}
		});
		btnUsuPacjenta.setBounds(10, 96, 198, 50);
		frame.getContentPane().add(btnUsuPacjenta);
	}
}
