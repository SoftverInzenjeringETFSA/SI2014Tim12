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

import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class KreiranjeMaterijalaGUI {

	private JDialog frmDodavanjeMaterijala;
	private JTextField materijal;
	private JTextField jed_cijena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KreiranjeMaterijalaGUI window = new KreiranjeMaterijalaGUI();
					window.frmDodavanjeMaterijala.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KreiranjeMaterijalaGUI() {
		initialize();
		frmDodavanjeMaterijala.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodavanjeMaterijala = new JDialog();
		MainForma.Prekini(frmDodavanjeMaterijala);
		frmDodavanjeMaterijala.setModalityType(ModalityType.APPLICATION_MODAL);
		frmDodavanjeMaterijala.setResizable(false);
		frmDodavanjeMaterijala.setTitle("Dodavanje novog materijala");
		frmDodavanjeMaterijala.setBounds(100, 100, 336, 204);
		frmDodavanjeMaterijala.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodavanjeMaterijala.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmDodavanjeMaterijala.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmDodavanjeMaterijala.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		materijal = new JTextField();
		materijal.setBounds(141, 11, 167, 20);
		panel_1.add(materijal);
		materijal.setColumns(10);
		
		JLabel lblIme = new JLabel("Ime materijala:");
		lblIme.setBounds(29, 11, 86, 20);
		panel_1.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Mjerna jedinica:");
		lblPrezime.setBounds(29, 42, 102, 20);
		panel_1.add(lblPrezime);
		
		jed_cijena = new JTextField();
		jed_cijena.setColumns(10);
		jed_cijena.setBounds(141, 73, 167, 20);
		panel_1.add(jed_cijena);
		
		JLabel lblBrojTelefona = new JLabel("Jedini\u010Dna cijena:");
		lblBrojTelefona.setHorizontalAlignment(SwingConstants.LEFT);
		lblBrojTelefona.setBounds(29, 73, 102, 20);
		panel_1.add(lblBrojTelefona);
		
		JButton btnKreiraj = new JButton("Kreiraj");
		btnKreiraj.setBounds(181, 124, 127, 23);
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(29, 124, 127, 23);
		panel_1.add(btnOdustani);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"miligram", "gram", "kilogram", "komad", "mililitar", "litar"}));
		comboBox.setBounds(141, 42, 167, 23);
		panel_1.add(comboBox);
	}
}
