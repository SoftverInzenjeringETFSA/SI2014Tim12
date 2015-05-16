package GUI;
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


public class ModifikacijaPacijentaGUI {

	private JDialog frmModifikacijaPacijenta;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifikacijaPacijentaGUI window = new ModifikacijaPacijentaGUI();
					window.frmModifikacijaPacijenta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModifikacijaPacijentaGUI() {
		initialize();
		frmModifikacijaPacijenta.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifikacijaPacijenta = new JDialog();
		MainForma.Prekini(frmModifikacijaPacijenta);
		frmModifikacijaPacijenta.setModalityType(ModalityType.APPLICATION_MODAL);
		frmModifikacijaPacijenta.setResizable(false);
		frmModifikacijaPacijenta.setTitle("Modifikacija pacijenta");
		frmModifikacijaPacijenta.setBounds(100, 100, 393, 341);
		frmModifikacijaPacijenta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifikacijaPacijenta.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmModifikacijaPacijenta.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmModifikacijaPacijenta.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(153, 11, 196, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(153, 42, 196, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(29, 11, 86, 20);
		panel_1.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(29, 42, 86, 20);
		panel_1.add(lblPrezime);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(153, 73, 196, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(153, 106, 196, 20);
		panel_1.add(textField_3);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(29, 73, 86, 20);
		panel_1.add(lblBrojTelefona);
		
		JLabel lblDatumRodjenja = new JLabel("Datum ro\u0111enja:");
		lblDatumRodjenja.setBounds(29, 106, 86, 20);
		panel_1.add(lblDatumRodjenja);
		
		JButton btnKreiraj = new JButton("Modifikuj");
		btnKreiraj.setBounds(192, 257, 157, 23);
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(29, 257, 157, 23);
		panel_1.add(btnOdustani);
		
		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setBounds(29, 144, 86, 20);
		panel_1.add(lblOpis);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(153, 144, 196, 90);
		panel_1.add(textPane);
	}
}
