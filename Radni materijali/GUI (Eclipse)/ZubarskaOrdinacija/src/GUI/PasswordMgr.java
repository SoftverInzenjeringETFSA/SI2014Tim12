package GUI;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class PasswordMgr extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PasswordMgr() {
		setBounds(100, 100, 381, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		JLabel txtpnStariPassword = new JLabel();
		txtpnStariPassword.setText("Stari password");
		txtpnStariPassword.setBackground(SystemColor.control);
		txtpnStariPassword.setBounds(10, 11, 96, 20);
		contentPanel.add(txtpnStariPassword);
		
		JLabel txtpnNoviPasswword = new JLabel();
		txtpnNoviPasswword.setText("Novi passwword");
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
		JDialog mee = this;
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
					public void actionPerformed(ActionEvent e) {
						String s = new String (passwordField.getPassword());
						if (!s.equals("s")) //stari password - izmijeniti
						{
							JOptionPane.showMessageDialog(null,
							"Stari password nije taèan! (za sada je 's')", "Greska!", JOptionPane.ERROR_MESSAGE);
							return;
						}
						String n = new String (passwordField_1.getPassword());
						String np = new String (passwordField_2.getPassword());
						if (!n.equals(np))
						{
							JOptionPane.showMessageDialog(null,
									"Passwordi nisu isti!", "Greska!", JOptionPane.ERROR_MESSAGE);
							return;
						}
						mee.dispatchEvent(new WindowEvent(mee, WindowEvent.WINDOW_CLOSING));
						//promjena passworda zauvijek
						//Snimi ("passwword", n);
						//U bazu ili gdje veæ
						
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
