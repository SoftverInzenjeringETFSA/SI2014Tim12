package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JSpinner;

public class Termini {

	private JFrame frmR;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Termini window = new Termini();
					window.frmR.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Termini() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmR = new JFrame();
		frmR.setType(Type.UTILITY);
		frmR.setTitle("Termini");
		frmR.setBounds(100, 100, 629, 446);
		frmR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmR.getContentPane().setLayout(null);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(79, 205, 88, 22);
		frmR.getContentPane().add(spinner);
		
		
	}
}
