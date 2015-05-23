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

import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.PacijentManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ModifikacijaPacijentaGUI {

	private JDialog frmModifikacijaPacijenta;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextPane textPane;
	private long pacijentId;
	private DateFormat dateFormat;
	/**
	 * Create the application.
	 * @param pacijentVM 
	 */
	public ModifikacijaPacijentaGUI(PacijentVM pacijentVM) {
		initialize();
		
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		pacijentId = pacijentVM.getId();
		
		textField.setText(pacijentVM.getImePrezime());
		textField_2.setText(pacijentVM.getBrojTelefona());
		textField_3.setText(dateFormat.format(pacijentVM.getDatumRodjenja()));
		textPane.setText(pacijentVM.getOpis());
		
		frmModifikacijaPacijenta.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifikacijaPacijenta = new JDialog();
		MainForma.Prekini(frmModifikacijaPacijenta);
		frmModifikacijaPacijenta.setModalityType(ModalityType.APPLICATION_MODAL);
		frmModifikacijaPacijenta.setResizable(false);
		frmModifikacijaPacijenta.setTitle("Modifikacija pacijenta");
		frmModifikacijaPacijenta.setBounds(100, 100, 393, 341);
		frmModifikacijaPacijenta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifikacijaPacijenta.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmModifikacijaPacijenta.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmModifikacijaPacijenta.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(153, 31, 196, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setBounds(29, 31, 86, 20);
		panel_1.add(lblImeIPrezime);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(153, 73, 196, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(153, 106, 196, 20);
		panel_1.add(textField_3);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setBounds(29, 73, 86, 20);
		panel_1.add(lblBrojTelefona);
		
		JLabel lblDatumRodjenja = new JLabel("Datum ro\u0111enja:");
		lblDatumRodjenja.setBounds(29, 106, 86, 20);
		panel_1.add(lblDatumRodjenja);
		
		JButton btnKreiraj = new JButton("Modifikuj");
		btnKreiraj.setBounds(192, 257, 157, 23);
		btnKreiraj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Session sess = null;
				try {
					String imeIPrezime = textField.getText();
					String brojTelefona = textField_2.getText();
					Date datumRodjenja = dateFormat.parse(textField_3.getText());
					String opis = textPane.getText();
					
					PacijentVM pacijent = new PacijentVM();
					pacijent.setId(pacijentId);
					pacijent.setImePrezime(imeIPrezime);
					pacijent.setBrojTelefona(brojTelefona);
					pacijent.setDatumRodjenja(datumRodjenja);
					pacijent.setOpis(opis);
					
					sess = HibernateUtil.getSessionFactory().openSession();
					
					PacijentManager pManager = new PacijentManager(sess);
					if(pManager.modificirajPacijenta(pacijent)){
						JOptionPane.showMessageDialog(frmModifikacijaPacijenta, "Pacijent uspješno modificiran", 
								"Obavještenje", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frmModifikacijaPacijenta, "Pacijent ne postoji",
								"Greška!",	JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(frmModifikacijaPacijenta, "Datum unesite u formatu: dd-mm-yyyy",
							"Greška!",	JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (Exception e2){
					JOptionPane.showMessageDialog(frmModifikacijaPacijenta, e2.getMessage(),
							"Greška!",	JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
					
				} finally {
					if(sess != null && sess.isOpen())
						sess.close();
				}
				
			}
		});
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(29, 257, 157, 23);
		btnOdustani.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				frmModifikacijaPacijenta.dispatchEvent(new WindowEvent(frmModifikacijaPacijenta, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_1.add(btnOdustani);
		
		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setBounds(29, 144, 86, 20);
		panel_1.add(lblOpis);
		
		textPane = new JTextPane();
		textPane.setBounds(153, 144, 196, 90);
		panel_1.add(textPane);
	}
}
