package GUI;


import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.JDialog;
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

	private JDialog frmPrijava;
	private static JPasswordField sifraTxt;
	private JLabel lblLozinka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new loginGUI();

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
		frmPrijava.setVisible(true);
		frmPrijava.requestFocus();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrijava = new JDialog();
		frmPrijava.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
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
		frmPrijava.setBounds(100, 100, 302, 123);
		frmPrijava.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmPrijava.getContentPane().setLayout(null);
		frmPrijava.setLocationRelativeTo(null);
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
		sifraTxt.setForeground(Color.BLACK);

		sifraTxt.setBounds(111, 20, 166, 25);
		frmPrijava.getContentPane().add(sifraTxt);
		sifraTxt.setColumns(10);
		
		JButton prijavaBtn = new JButton("Prijavi se");
		prijavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Prijava();
			}
		});
		prijavaBtn.setBounds(164, 55, 113, 23);
		frmPrijava.getContentPane().add(prijavaBtn);
		
		lblLozinka = new JLabel("Password:");
		lblLozinka.setBounds(10, 25, 79, 14);
		frmPrijava.getContentPane().add(lblLozinka);
		
	}
	
	// Implemetirati funkciju za prijavu 
	public void Prijava() {
		JOptionPane.showMessageDialog(frmPrijava,
			    "Nije implementirano.",
			    "Obavještenje",
			    JOptionPane.INFORMATION_MESSAGE);
	}
}