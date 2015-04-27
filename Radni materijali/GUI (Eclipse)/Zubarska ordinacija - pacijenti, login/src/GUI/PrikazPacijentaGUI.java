package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class PrikazPacijentaGUI {

	private JFrame frmPrikazPacijenta;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazPacijentaGUI window = new PrikazPacijentaGUI();
					window.frmPrikazPacijenta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazPacijentaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazPacijenta = new JFrame();
		frmPrikazPacijenta.setResizable(false);
		frmPrikazPacijenta.setTitle("Prikaz pacijenta");
		frmPrikazPacijenta.setBounds(100, 100, 450, 403);
		frmPrikazPacijenta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrikazPacijenta.getContentPane().setLayout(null);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(10, 11, 126, 21);
		frmPrikazPacijenta.getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(10, 40, 126, 21);
		frmPrikazPacijenta.getContentPane().add(lblPrezime);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 72, 126, 21);
		frmPrikazPacijenta.getContentPane().add(lblId);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(10, 104, 126, 21);
		frmPrikazPacijenta.getContentPane().add(lblBrojTelefona);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(10, 136, 126, 21);
		frmPrikazPacijenta.getContentPane().add(lblEmail);
		
		JLabel lblPosjete = new JLabel("Posjete:");
		lblPosjete.setBounds(10, 168, 126, 21);
		frmPrikazPacijenta.getContentPane().add(lblPosjete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 424, 163);
		frmPrikazPacijenta.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
