package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.Dialog.ModalityType;


public class KolicinaMaterijalaGUI {

	private JDialog frmModifikacijaZahvata;
	private JTextField textField;



	/**
	 * Create the application.
	 */
	public KolicinaMaterijalaGUI() {
		initialize();
		frmModifikacijaZahvata.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifikacijaZahvata = new JDialog();
		MainForma.Prekini(frmModifikacijaZahvata);
		frmModifikacijaZahvata.setResizable(false);
		frmModifikacijaZahvata.setModalityType(ModalityType.APPLICATION_MODAL);
		frmModifikacijaZahvata.setTitle("Koli\u010Dina dostupnog materijala");
		frmModifikacijaZahvata.setBounds(100, 100, 393, 204);
		frmModifikacijaZahvata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifikacijaZahvata.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmModifikacijaZahvata.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmModifikacijaZahvata.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(153, 38, 196, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblIme = new JLabel("Koli\u010Dina");
		lblIme.setBounds(25, 38, 86, 20);
		panel_1.add(lblIme);
		
		JButton btnKreiraj = new JButton("Potvrdi");
		btnKreiraj.setBounds(192, 95, 157, 23);
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(25, 95, 157, 23);
		panel_1.add(btnOdustani);
	}
}
