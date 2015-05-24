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



import ba.unsa.etf.si.tim12.bll.service.TipZahvataManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ModifikacijaZahvataGUI {

	private JDialog frmModifikacijaZahvata;
	private JTextField textField;
	private long id;
	static final Logger logger = Logger
			.getLogger(ModifikacijaPacijentaGUI.class);



	/**
	 * Create the application.
	 */
	public ModifikacijaZahvataGUI(TipZahvataVM zahvat) {
		id=zahvat.getId();
		textField.setText(Double.toString(zahvat.getCijena()));
		initialize();
		frmModifikacijaZahvata.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifikacijaZahvata = new JDialog();
		MainForma.Prekini(frmModifikacijaZahvata);
		frmModifikacijaZahvata.setResizable(false);
		frmModifikacijaZahvata.setModalityType(ModalityType.APPLICATION_MODAL);
		frmModifikacijaZahvata.setTitle("Modifikacija cijene zahvata");
		frmModifikacijaZahvata.setBounds(100, 100, 393, 204);
		frmModifikacijaZahvata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifikacijaZahvata.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmModifikacijaZahvata.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmModifikacijaZahvata.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(153, 38, 196, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblIme = new JLabel("Nova cijena:");
		lblIme.setBounds(25, 38, 86, 20);
		panel_1.add(lblIme);
		
		JButton btnKreiraj = new JButton("Izmjeni");
		btnKreiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session sess = null;
				// dodavanje pretrazenih materijala u tabelu
				try {
					sess = HibernateUtil.getSessionFactory().openSession();
					TipZahvataManager m = new TipZahvataManager(sess);
					boolean promjenjena = m.promijeniCijenuZahvata(id,  Long.parseLong(textField.getText()));
					if(promjenjena) { JOptionPane
						.showMessageDialog(frmModifikacijaZahvata,
								"Cijena uspješno promjenjena",
								"Obavještenje",
								JOptionPane.INFORMATION_MESSAGE);}
					else {JOptionPane
						.showMessageDialog(frmModifikacijaZahvata,
								"Zahvat ne postoji",
								"Greška!",
								JOptionPane.INFORMATION_MESSAGE);}
					// prvo praznjenje
					/*table.setModel(new UneditableTableModel(
							new Object[][] {
							},
							new String[] {
							 "Naziv", "Materijali", "Cijena"
							}
						));*/

				} catch (Exception e2) {
					logger.debug(e2.getMessage(), e2);
				} finally {
					if (sess != null)
						sess.close();
				}
			}
		});
		btnKreiraj.setBounds(192, 95, 157, 23);
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(25, 95, 157, 23);
		panel_1.add(btnOdustani);
	}
}
