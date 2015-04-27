package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PromjenaSifreGUI {

	private JFrame frmPromjenaLozinke;
	private final JButton btnPromjeni = new JButton("Promjeni");
	private JTextField txtStaraLozinka;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblStaraLozinka;
	private JLabel lblNovaLozinka;
	private JLabel lblPotvrdaNoveLozinke;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromjenaSifreGUI window = new PromjenaSifreGUI();
					window.frmPromjenaLozinke.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PromjenaSifreGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPromjenaLozinke = new JFrame();
		frmPromjenaLozinke.setResizable(false);
		frmPromjenaLozinke.setTitle("Promjena lozinke");
		frmPromjenaLozinke.setBounds(100, 100, 381, 220);
		frmPromjenaLozinke.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPromjenaLozinke.getContentPane().setLayout(null);
		btnPromjeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPromjeni.setBounds(238, 147, 106, 23);
		frmPromjenaLozinke.getContentPane().add(btnPromjeni);
		
		txtStaraLozinka = new JTextField();
		txtStaraLozinka.setForeground(Color.GRAY);
		txtStaraLozinka.setColumns(10);
		txtStaraLozinka.setBounds(163, 22, 181, 20);
		frmPromjenaLozinke.getContentPane().add(txtStaraLozinka);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 66, 181, 20);
		frmPromjenaLozinke.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(163, 113, 181, 20);
		frmPromjenaLozinke.getContentPane().add(passwordField_1);
		
		lblStaraLozinka = new JLabel("Stara lozinka:");
		lblStaraLozinka.setBounds(10, 28, 95, 14);
		frmPromjenaLozinke.getContentPane().add(lblStaraLozinka);
		
		lblNovaLozinka = new JLabel("Nova lozinka:");
		lblNovaLozinka.setBounds(10, 69, 95, 14);
		frmPromjenaLozinke.getContentPane().add(lblNovaLozinka);
		
		lblPotvrdaNoveLozinke = new JLabel("Potvrda nove lozinke:");
		lblPotvrdaNoveLozinke.setBounds(10, 116, 143, 14);
		frmPromjenaLozinke.getContentPane().add(lblPotvrdaNoveLozinke);
	}
}
