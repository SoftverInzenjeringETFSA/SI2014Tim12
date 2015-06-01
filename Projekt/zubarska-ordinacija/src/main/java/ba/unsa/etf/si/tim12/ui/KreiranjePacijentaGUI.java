package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.PacijentManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviPacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.Validator;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class KreiranjePacijentaGUI {

	private JDialog frmRegistracijaNovogPacijenta;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextPane textPane;
	private String dateFormat;
	private static final Logger logger = Logger.getLogger(KreiranjePacijentaGUI.class);
	
	/**
	 * Create the application.
	 */
	public KreiranjePacijentaGUI() {
		initialize();
		dateFormat = "dd-MM-yyyy";
		frmRegistracijaNovogPacijenta.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistracijaNovogPacijenta = new JDialog();
		MainForma.Prekini(frmRegistracijaNovogPacijenta);
		frmRegistracijaNovogPacijenta.setModalityType(ModalityType.APPLICATION_MODAL);
		frmRegistracijaNovogPacijenta.setResizable(false);
		frmRegistracijaNovogPacijenta.setTitle("Registracija novog pacijenta");
		frmRegistracijaNovogPacijenta.setBounds(100, 100, 393, 341);
		frmRegistracijaNovogPacijenta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistracijaNovogPacijenta.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmRegistracijaNovogPacijenta.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmRegistracijaNovogPacijenta.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(153, 30, 196, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setBounds(29, 30, 86, 20);
		panel_1.add(lblImeIPrezime);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(153, 73, 196, 20);
		panel_1.add(textField_2);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(29, 73, 86, 20);
		panel_1.add(lblBrojTelefona);
		
		JLabel lblDatumRodjenja = new JLabel("Datum rođenja:"); //\u0111
		lblDatumRodjenja.setBounds(29, 104, 86, 20);
		panel_1.add(lblDatumRodjenja);
		
		JButton btnKreiraj = new JButton("Kreiraj");
		btnKreiraj.setBounds(192, 257, 157, 23);
		btnKreiraj.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().length()<3) {JOptionPane.showMessageDialog(null,
						"Unesite najmanje tri slova imena i prezimena", "Greška!",
						JOptionPane.ERROR_MESSAGE); return;}

				Session sess = null;
				try {
					String imeIPrezime = textField.getText();
					String brojTelefona = textField_2.getText();
					Date datumRodjenja = Validator.ValidAndParse(dateFormat, textField_3.getText());
					String opis = textPane.getText();

					if(imeIPrezime.isEmpty())
						throw new Exception("Morate unijeti ime i prezime!");
					Validator.ValidateName(imeIPrezime, true);
					if(brojTelefona.isEmpty())
						throw new Exception("Morate unijeti broj telefona!");
					if(!Validator.isPhoneNumber(brojTelefona))
						throw new Exception("Broj telefona mora imati minimalno 6 cifara i ne smije imati drugih znakova osim početnog +");
					if(datumRodjenja.after(new Date()))
						throw new Exception("Nije moguć datum rođenja u budućnosti.");
					
					NoviPacijentVM pacijent = new NoviPacijentVM();
					pacijent.setImeIPrezime(imeIPrezime);
					pacijent.setBrojTelefona(brojTelefona);
					pacijent.setDatumRodenja(datumRodjenja);
					pacijent.setOpis(opis);

					
					
					sess = HibernateUtil.getSessionFactory().openSession();

					PacijentManager pManager = new PacijentManager(sess);
					if (pManager.dodajNovogPacijenta(pacijent)) {
						JOptionPane
						.showMessageDialog(frmRegistracijaNovogPacijenta,
								"Pacijent uspješno dodan.",
								"Obavještenje",
								JOptionPane.INFORMATION_MESSAGE);
						
						ResetFields();
					} else {
						JOptionPane.showMessageDialog(frmRegistracijaNovogPacijenta,
								"Dodavanje pacijenta nije moguće.", "Greška!",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(frmRegistracijaNovogPacijenta,
							e1.getMessage(), "Greška!",
							JOptionPane.ERROR_MESSAGE);
					logger.debug("Pogrešno unesen datum", e1);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmRegistracijaNovogPacijenta,
							e2.getMessage(), "Greška!",
							JOptionPane.ERROR_MESSAGE);
					logger.debug("Greška", e2);
				} finally {
					if (sess != null && sess.isOpen())
						sess.close();
				}

			}

			
		});
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(29, 257, 157, 23);
		btnOdustani.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frmRegistracijaNovogPacijenta.dispatchEvent(new WindowEvent(frmRegistracijaNovogPacijenta,
						WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_1.add(btnOdustani);
		
		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setBounds(29, 144, 86, 20);
		panel_1.add(lblOpis);
		
		//Datum rođenja
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(153, 106, 196, 20);
		panel_1.add(textField_3);
		
		textPane = new JTextPane();
		textPane.setBounds(153, 144, 196, 90);
		panel_1.add(textPane);
		
		ResetFields();
	}
	
	private void ResetFields() {
		textField.setText("");
		textField_2.setText("");
		textField_3.setText("dd-mm-gggg");
		textPane.setText("");
	}
}
