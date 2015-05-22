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

import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.KorisnikManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.LoginVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;


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
	public boolean Prijava() {
		Session sess = null;
		try{
		sess = HibernateUtil.getSessionFactory().openSession();
		KorisnikManager m= new KorisnikManager(sess);
		LoginVM lgvm = new LoginVM();
		lgvm.setUsername(textField.getText());
		lgvm.setPassword(passwordField.getPassword().toString());
		return m.provjeriPassword(lgvm);
		
		
		} catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			if(sess != null)
				sess.close();
		}



	}
}