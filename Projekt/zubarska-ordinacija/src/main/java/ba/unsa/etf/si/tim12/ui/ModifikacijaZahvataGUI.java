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
		initialize();
		
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
				
				if (textField.getText().isEmpty()) {JOptionPane.showMessageDialog(null,
						"Unesite cijenu", "Greška!",
						JOptionPane.ERROR_MESSAGE); return;}
				
				
				Session sess = null;
				// dodavanje pretrazenih materijala u tabelu
				try {
					if (Double.parseDouble(textField.getText())<0) {JOptionPane.showMessageDialog(null,
							"Unešena cijena je negativna", "Greška!",
							JOptionPane.ERROR_MESSAGE); return;}
					
					sess = HibernateUtil.getSessionFactory().openSession();
					TipZahvataManager m = new TipZahvataManager(sess);
					boolean promjenjena = m.promijeniCijenuZahvata(id,  Double.parseDouble(textField.getText()));
					if(promjenjena) { JOptionPane
						.showMessageDialog(frmModifikacijaZahvata,
								"Cijena uspješno promjenjena",
								"Obavještenje",
								JOptionPane.INFORMATION_MESSAGE);}
					else {JOptionPane
						.showMessageDialog(frmModifikacijaZahvata, "Zahvat ne postoji",	"Greška!",
								JOptionPane.ERROR_MESSAGE);}
					
					frmModifikacijaZahvata.dispose();
				} catch (NumberFormatException e1){
					JOptionPane
					.showMessageDialog(frmModifikacijaZahvata,"Unesite decimalan broj za cijenu",
							"Greška!",	JOptionPane.ERROR_MESSAGE);
					
					logger.debug(e1.getMessage(), e1);
				}catch (Exception e2) {
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
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmModifikacijaZahvata.dispose();
			}
		});
		btnOdustani.setBounds(25, 95, 157, 23);
		panel_1.add(btnOdustani);
	}
}
