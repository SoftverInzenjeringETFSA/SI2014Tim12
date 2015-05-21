package ba.unsa.etf.si.tim12.ui;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
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
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class loginGUI {

	private JDialog frmPrijava;
	private JLabel lblLozinka;
	private JPasswordField passwordField;
	private JTextField textField;



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
					if (Prijava());
					{
						frmPrijava.dispatchEvent(new WindowEvent(frmPrijava, WindowEvent.WINDOW_CLOSING));
						frmPrijava.setVisible (false);
						frmPrijava.dispose();
					}
	            }
			}
		});
		frmPrijava.setResizable(false);
		frmPrijava.setTitle("Prijava");
		frmPrijava.setBounds(100, 100, 307, 149);
		frmPrijava.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		frmPrijava.getContentPane().setLayout(null);
		frmPrijava.setLocationRelativeTo(null);
		
		JButton prijavaBtn = new JButton("Prijavi se");
		prijavaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Prijava());
					{
						frmPrijava.dispatchEvent(new WindowEvent(frmPrijava, WindowEvent.WINDOW_CLOSING));
						frmPrijava.setVisible (false);
						frmPrijava.dispose();
					}
			}
		});
		prijavaBtn.setBounds(164, 86, 113, 23);
		frmPrijava.getContentPane().add(prijavaBtn);
		
		lblLozinka = new JLabel("Password:");
		lblLozinka.setBounds(10, 60, 79, 14);
		frmPrijava.getContentPane().add(lblLozinka);
		
		passwordField = new JPasswordField("");
		passwordField.setToolTipText("");
		passwordField.setForeground(Color.GRAY);
		passwordField.setColumns(10);
		passwordField.setBounds(111, 55, 166, 20);
		frmPrijava.getContentPane().add(passwordField);
		
		JLabel label = new JLabel("Korisni\u010Dko ime:");
		label.setBounds(10, 24, 95, 14);
		frmPrijava.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setForeground(Color.GRAY);
		textField.setColumns(10);
		textField.setBounds(111, 21, 166, 20);
		frmPrijava.getContentPane().add(textField);
		
	}
	
	// Implemetirati funkciju za prijavu 
	public Boolean Prijava() {
		JOptionPane.showMessageDialog(frmPrijava,
			    "Nije implementirano.",
			    "Obavje�tenje",
			    JOptionPane.INFORMATION_MESSAGE);
		return true; //neka za sada stoji da je korisnik logovan

	}
}