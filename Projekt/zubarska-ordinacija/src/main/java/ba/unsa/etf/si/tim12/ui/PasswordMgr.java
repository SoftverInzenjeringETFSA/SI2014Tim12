package ba.unsa.etf.si.tim12.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

import java.awt.SystemColor;

import javax.swing.JPasswordField;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.KorisnikManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.LoginVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PromjenaPasswordaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class PasswordMgr extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private static final Logger logger = Logger.getLogger(PasswordMgr.class);


	/**
	 * Create the dialog.
	 */
	public PasswordMgr() {
		MainForma.Prekini(this);
		setBounds(100, 100, 381, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setTitle ("Promjena passworda");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setLocationRelativeTo(null);
		JLabel txtpnStariPassword = new JLabel();
		txtpnStariPassword.setText("Stari password");
		txtpnStariPassword.setBackground(SystemColor.control);
		txtpnStariPassword.setBounds(10, 11, 96, 20);
		contentPanel.add(txtpnStariPassword);
		
		JLabel txtpnNoviPasswword = new JLabel();
		txtpnNoviPasswword.setText("Novi password");
		txtpnNoviPasswword.setBackground(SystemColor.menu);
		txtpnNoviPasswword.setBounds(10, 42, 96, 20);
		contentPanel.add(txtpnNoviPasswword);
		
		JLabel txtpnPonovljeniNoviPassword = new JLabel();
		txtpnPonovljeniNoviPassword.setText("Ponovite novi password");
		txtpnPonovljeniNoviPassword.setBackground(SystemColor.menu);
		txtpnPonovljeniNoviPassword.setBounds(10, 73, 159, 20);
		contentPanel.add(txtpnPonovljeniNoviPassword);
		
		
		passwordField = new JPasswordField();
		
		passwordField.setBounds(181, 11, 173, 20);
		contentPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(181, 42, 173, 20);
		contentPanel.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(181, 73, 173, 20);
		final JDialog mee = this;
		contentPanel.add(passwordField_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				JButton cancelButton = new JButton("Iza\u0111i");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mee.dispatchEvent(new WindowEvent(mee, WindowEvent.WINDOW_CLOSING));
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e2) {
						String s = new String (passwordField.getPassword());
						Session sess = null;

						try {

							sess = HibernateUtil.getSessionFactory().openSession();
							KorisnikManager m = new KorisnikManager(sess);

							PromjenaPasswordaVM pm = new PromjenaPasswordaVM();

							pm.setUsername(loginGUI.DajUsername());
							pm.setStariPass(new String(passwordField.getPassword()));
							pm.setNoviPass(new String(passwordField_1.getPassword()));
							pm.setPonovoNoviPass(new String(passwordField_2.getPassword()));
							
							int i = m.promjeniPassword(pm);
							if (i == 0)
							{
								JOptionPane.showMessageDialog(null,
										"Password uspjesno promijenjen!", "Promjena passworda", JOptionPane.INFORMATION_MESSAGE);
								mee.dispatchEvent(new WindowEvent(mee, WindowEvent.WINDOW_CLOSING));
								return;
							}
							switch (i)
							{
							case 1:
								JOptionPane.showMessageDialog(null,
										"Uneseni novi password i ponovljeni password nisu isti!", "Greska", JOptionPane.ERROR_MESSAGE);
								break;
							case 2:
								//nece se nikad dogoditi, ali svejedno tretiramo i ovaj slucaj!
								JOptionPane.showMessageDialog(null,
										"Ne postoji korisnik sa navedenim korisnickim imenom!", "Greska", JOptionPane.ERROR_MESSAGE);
								break;
							case 3:
								JOptionPane.showMessageDialog(null,
										"Pogresan stari password!", "Greska", JOptionPane.ERROR_MESSAGE);
								break;																
								
							}
						} catch (Exception e) {
							logger.debug(e.getMessage(), e);
							return;
						} finally {
							if (sess != null)
								sess.close();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				setVisible(true);
			}
		}
	}
}
