package ba.unsa.etf.si.tim12.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



import java.awt.Component;
import java.awt.Dialog.ModalityType;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Posjete {

	private JDialog dlgPosjeteRegistracija;
	private JTextField textFieldUkCijena;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;



	/**
	 * Create the application.
	 */
	public Posjete() {
		initialize();
		dlgPosjeteRegistracija.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dlgPosjeteRegistracija = new JDialog();
		MainForma.Prekini(dlgPosjeteRegistracija);
		dlgPosjeteRegistracija.setModalityType(ModalityType.APPLICATION_MODAL);
		dlgPosjeteRegistracija.setTitle("Posjete - registracija");
		dlgPosjeteRegistracija.setBounds(100, 100, 829, 366);
		dlgPosjeteRegistracija.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgPosjeteRegistracija.getContentPane().setLayout(null);
		dlgPosjeteRegistracija.setLocationRelativeTo(null);
		JLabel lblPacijent = new JLabel("Pacijent:");
		lblPacijent.setBounds(31, 61, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblPacijent);
		
		JComboBox comboBoxPacijent = new JComboBox();
		comboBoxPacijent.setEditable(true);
		lblPacijent.setLabelFor(comboBoxPacijent);
		comboBoxPacijent.setBounds(138, 58, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(comboBoxPacijent);
		
		JLabel lblDijagnoza = new JLabel("Dijagnoza:");
		lblDijagnoza.setBounds(31, 165, 97, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblDijagnoza);
		
		JSeparator separator_pacijent = new JSeparator();
		separator_pacijent.setBounds(31, 46, 338, 2);
		dlgPosjeteRegistracija.getContentPane().add(separator_pacijent);
		
		JLabel lblInformacijeOPacijentu = new JLabel("Informacije o pacijentu");
		lblInformacijeOPacijentu.setBounds(31, 13, 137, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblInformacijeOPacijentu);
		
		JLabel lblInformacijeOZahvatu = new JLabel("Informacije o ura\u0111enim zahvatima");
		lblInformacijeOZahvatu.setBounds(420, 13, 208, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblInformacijeOZahvatu);
		
		JSeparator separator_zahvat = new JSeparator();
		separator_zahvat.setBounds(420, 46, 338, 2);
		dlgPosjeteRegistracija.getContentPane().add(separator_zahvat);
		
		JButton btnDodajZahvat = new JButton("Registruj posjetu");
		btnDodajZahvat.setBounds(527, 279, 231, 25);
		dlgPosjeteRegistracija.getContentPane().add(btnDodajZahvat);
		
		JLabel lblUkupnaCijena = new JLabel("Ukupna cijena:");
		lblUkupnaCijena.setBounds(422, 231, 95, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblUkupnaCijena);
		
		textFieldUkCijena = new JTextField();
		textFieldUkCijena.setEditable(false);
		textFieldUkCijena.setEnabled(false);
		lblUkupnaCijena.setLabelFor(textFieldUkCijena);
		textFieldUkCijena.setColumns(10);
		textFieldUkCijena.setBounds(527, 225, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textFieldUkCijena);
		
		JLabel lblDoktor = new JLabel("Doktor:");
		lblDoktor.setBounds(31, 93, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblDoktor);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(138, 91, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textField);
		
		JLabel lblVrijemePosjete = new JLabel("Vrijeme posjete:");
		lblVrijemePosjete.setBounds(31, 128, 107, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblVrijemePosjete);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(138, 125, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textField_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(138, 161, 231, 143);
		dlgPosjeteRegistracija.getContentPane().add(textPane);
		
		JButton btnDodajZahvat_1 = new JButton("Dodaj zahvat");
		btnDodajZahvat_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DodavanjeZahvataGUI();
			}
		});
		btnDodajZahvat_1.setBounds(651, 191, 107, 23);
		dlgPosjeteRegistracija.getContentPane().add(btnDodajZahvat_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(430, 61, 328, 118);
		dlgPosjeteRegistracija.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Zahvat", "Cijena"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(202);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		dlgPosjeteRegistracija.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBoxPacijent, textFieldUkCijena, btnDodajZahvat, lblInformacijeOPacijentu, separator_pacijent, lblPacijent, lblDijagnoza, lblInformacijeOZahvatu, separator_zahvat, lblUkupnaCijena, dlgPosjeteRegistracija.getContentPane()}));
	}
}