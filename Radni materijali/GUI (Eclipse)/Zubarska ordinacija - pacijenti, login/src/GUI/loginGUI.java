package GUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class loginGUI {

	private JFrame frmPrijava;
	private JTextField korisnickoImeTxt;
	private static JPasswordField sifraTxt;
	private JLabel lblKorisnikoIme;
	private JLabel lblLozinka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginGUI window = new loginGUI();
					window.frmPrijava.setVisible(true);
					window.frmPrijava.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrijava = new JFrame();
		frmPrijava.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
	            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
	            {
	                Prijava();
	            }
			}
		});
		frmPrijava.setResizable(false);
		frmPrijava.setTitle("Prijava");
		frmPrijava.setBounds(100, 100, 302, 153);
		frmPrijava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrijava.getContentPane().setLayout(null);
		
		korisnickoImeTxt = new JTextField();
		korisnickoImeTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
	            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
	            {
	                Prijava();
	            }
			}
		});
		korisnickoImeTxt.setForeground(Color.GRAY);
		korisnickoImeTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
				if (korisnickoImeTxt.getText().equals("KorisniÄ?ko ime")) {
					korisnickoImeTxt.setText(null);
					korisnickoImeTxt.setForeground(Color.BLACK);
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if (korisnickoImeTxt.getText().equals("")) {
					korisnickoImeTxt.setText("KorisniÄ?ko ime");
					korisnickoImeTxt.setForeground(Color.GRAY);
				}
				
			}
		});
		korisnickoImeTxt.setBounds(111, 22, 166, 20);
		frmPrijava.getContentPane().add(korisnickoImeTxt);
		korisnickoImeTxt.setColumns(10);
		
		sifraTxt = new JPasswordField("");
		sifraTxt.setToolTipText("");
		sifraTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
	            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
	            {
	                Prijava();
	            }
			}
		});
		sifraTxt.setForeground(Color.GRAY);
		sifraTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				char[] pass = sifraTxt.getPassword();  
				String passString = new String(pass); 
				if (passString.equals("Å ifra")) {
					sifraTxt.setEchoChar('*');
					sifraTxt.setText(null);
					sifraTxt.setForeground(Color.BLACK);
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				char[] pass = sifraTxt.getPassword();  
				String passString = new String(pass); 
				if (passString.equals("")) {
					sifraTxt.setEchoChar((char)0);
					sifraTxt.setText("Å ifra");
					sifraTxt.setForeground(Color.GRAY);
				}
				
			}
		});

		sifraTxt.setBounds(111, 53, 166, 20);
		frmPrijava.getContentPane().add(sifraTxt);
		sifraTxt.setColumns(10);
		
		JButton prijavaBtn = new JButton("Prijavi se");
		prijavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Prijava();
			}
		});
		prijavaBtn.setBounds(164, 85, 113, 23);
		frmPrijava.getContentPane().add(prijavaBtn);
		
		lblKorisnikoIme = new JLabel("Korisni\u010Dko ime:");
		lblKorisnikoIme.setBounds(10, 25, 95, 14);
		frmPrijava.getContentPane().add(lblKorisnikoIme);
		
		lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setBounds(10, 59, 79, 14);
		frmPrijava.getContentPane().add(lblLozinka);
		
	}
	
	// Implemetirati funkciju za prijavu 
	public void Prijava() {
		JOptionPane.showMessageDialog(frmPrijava,
			    "Nije implementirano.",
			    "ObavjeÅ¡tenje",
			    JOptionPane.INFORMATION_MESSAGE);
	}
}