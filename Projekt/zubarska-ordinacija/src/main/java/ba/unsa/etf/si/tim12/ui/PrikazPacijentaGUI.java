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
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PrikazPacijentaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PrikazPacijentaGUI {

	private JDialog frmPrikazPacijenta;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextPane textPane;
	private DateFormat dateFormat;
	static final Logger logger = Logger
			.getLogger(ModifikacijaPacijentaGUI.class);
	private JTable tablePosjete;
	private JTable tableTermini;

	/**
	 * Create the application.
	 * 
	 * @param pacijentVM
	 */
	public PrikazPacijentaGUI(PrikazPacijentaVM pacijentVM) {
		initialize();

		dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		textField.setText(pacijentVM.getImeIPrezime());
		textField_2.setText(pacijentVM.getBrojTelefona());
		textField_3.setText(dateFormat.format(pacijentVM.getDatumRodjenja()));
		textPane.setText(pacijentVM.getOpis());

		frmPrikazPacijenta.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazPacijenta = new JDialog();
		MainForma.Prekini(frmPrikazPacijenta);
		frmPrikazPacijenta
				.setModalityType(ModalityType.APPLICATION_MODAL);
		frmPrikazPacijenta.setResizable(false);
		frmPrikazPacijenta.setTitle("Prikaz pacijenta");
		frmPrikazPacijenta.setBounds(100, 100, 384, 660);
		frmPrikazPacijenta
				.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazPacijenta.setLocationRelativeTo(null);

		JPanel panel_1 = new JPanel();
		frmPrikazPacijenta.getContentPane().add(panel_1,
				BorderLayout.CENTER);
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
		lblDatumRodjenja.setBounds(29, 106, 112, 20);
		panel_1.add(lblDatumRodjenja);

		JButton btnZatvori = new JButton("Odustani");
		btnZatvori.setBounds(107, 589, 157, 23);
		btnZatvori.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				frmPrikazPacijenta.dispatchEvent(new WindowEvent(
						frmPrikazPacijenta, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_1.add(btnZatvori);

		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setBounds(29, 144, 86, 20);
		panel_1.add(lblOpis);

		textPane = new JTextPane();
		textPane.setBounds(153, 144, 196, 90);
		panel_1.add(textPane);
		
		JScrollPane scrollPaneTermini = new JScrollPane();
		scrollPaneTermini.setBounds(29, 448, 320, 128);
		panel_1.add(scrollPaneTermini);
		
		tableTermini = new JTable();
		scrollPaneTermini.setViewportView(tableTermini);
		tableTermini.setFillsViewportHeight(true);
		
		JScrollPane scrollPanePosjete = new JScrollPane();
		scrollPanePosjete.setBounds(29, 285, 320, 128);
		panel_1.add(scrollPanePosjete);
		
		tablePosjete = new JTable();
		tablePosjete.setFillsViewportHeight(true);
		scrollPanePosjete.setViewportView(tablePosjete);
		
		JLabel lblTermini = new JLabel("Termini");
		lblTermini.setBounds(29, 426, 56, 16);
		panel_1.add(lblTermini);
		
		JLabel lblPosjete = new JLabel("Posjete");
		lblPosjete.setBounds(29, 263, 56, 16);
		panel_1.add(lblPosjete);
	}
}
