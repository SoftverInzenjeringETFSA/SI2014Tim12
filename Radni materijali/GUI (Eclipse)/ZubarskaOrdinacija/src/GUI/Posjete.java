package GUI;

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

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Dialog.ModalityType;

import javax.swing.JTextPane;

public class Posjete {

	private JDialog dlgPosjeteRegistracija;
	private JTextField textFieldCijena;
	private JTextField textFieldUkCijena;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Posjete window = new Posjete();
					window.dlgPosjeteRegistracija.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		JLabel lblZahvat = new JLabel("Zahvat:");
		lblZahvat.setBounds(420, 61, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblZahvat);
		
		JComboBox comboBoxPacijent = new JComboBox();
		lblPacijent.setLabelFor(comboBoxPacijent);
		comboBoxPacijent.setEditable(true);
		comboBoxPacijent.setBounds(138, 58, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(comboBoxPacijent);
		
		JComboBox comboBoxZahvati = new JComboBox();
		lblZahvat.setLabelFor(comboBoxZahvati);
		comboBoxZahvati.setEditable(true);
		comboBoxZahvati.setBounds(527, 58, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(comboBoxZahvati);
		
		JLabel lblDijagnoza = new JLabel("Dijagnoza:");
		lblDijagnoza.setBounds(31, 165, 97, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblDijagnoza);
		
		JLabel lblCijena = new JLabel("Cijena:");
		lblCijena.setBounds(420, 162, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblCijena);
		
		textFieldCijena = new JTextField();
		lblCijena.setLabelFor(textFieldCijena);
		textFieldCijena.setColumns(10);
		textFieldCijena.setBounds(527, 159, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textFieldCijena);
		
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
		
		JLabel lblMaterijal = new JLabel("Materijal:");
		lblMaterijal.setBounds(420, 90, 56, 22);
		dlgPosjeteRegistracija.getContentPane().add(lblMaterijal);
		
		JComboBox comboBoxMaterijal = new JComboBox();
		lblMaterijal.setLabelFor(comboBoxMaterijal);
		comboBoxMaterijal.setEditable(true);
		comboBoxMaterijal.setBounds(527, 90, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(comboBoxMaterijal);
		
		JLabel lblKoliinaMaterijala = new JLabel("Koli\u010Dina materijala [mg]:");
		lblKoliinaMaterijala.setBounds(420, 124, 150, 22);
		dlgPosjeteRegistracija.getContentPane().add(lblKoliinaMaterijala);
		
		JSpinner spinnerKolicinaMaterijala = new JSpinner();
		spinnerKolicinaMaterijala.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(100)));
		spinnerKolicinaMaterijala.setBounds(642, 124, 116, 22);
		dlgPosjeteRegistracija.getContentPane().add(spinnerKolicinaMaterijala);
		
		JButton btnDodajZahvat = new JButton("Registruj posjetu");
		btnDodajZahvat.setBounds(527, 279, 231, 25);
		dlgPosjeteRegistracija.getContentPane().add(btnDodajZahvat);
		
		JLabel lblUkupnaCijena = new JLabel("Ukupna cijena:");
		lblUkupnaCijena.setBounds(420, 194, 95, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblUkupnaCijena);
		
		textFieldUkCijena = new JTextField();
		lblUkupnaCijena.setLabelFor(textFieldUkCijena);
		textFieldUkCijena.setColumns(10);
		textFieldUkCijena.setBounds(527, 191, 231, 22);
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
		dlgPosjeteRegistracija.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBoxPacijent, comboBoxZahvati, comboBoxMaterijal, spinnerKolicinaMaterijala, textFieldCijena, textFieldUkCijena, btnDodajZahvat, lblInformacijeOPacijentu, separator_pacijent, lblPacijent, lblDijagnoza, lblCijena, lblInformacijeOZahvatu, separator_zahvat, lblZahvat, lblMaterijal, lblKoliinaMaterijala, lblUkupnaCijena, dlgPosjeteRegistracija.getContentPane()}));
	}
}
