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

public class Posjete {

	private JDialog dlgPosjeteRegistracija;
	private JTextField txtDijagnoza;
	private JTextField textFieldCijena;
	private JTextField textFieldUkCijena;

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
		dlgPosjeteRegistracija.setModalityType(ModalityType.APPLICATION_MODAL);
		dlgPosjeteRegistracija.setTitle("Posjete - registracija");
		dlgPosjeteRegistracija.setBounds(100, 100, 829, 366);
		dlgPosjeteRegistracija.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgPosjeteRegistracija.getContentPane().setLayout(null);
		
		JLabel lblPacijent = new JLabel("Pacijent");
		lblPacijent.setBounds(31, 61, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblPacijent);
		
		JLabel lblZahvat = new JLabel("Zahvat");
		lblZahvat.setBounds(420, 61, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblZahvat);
		
		JLabel lblSimptomi = new JLabel("Simptomi");
		lblSimptomi.setBounds(31, 90, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblSimptomi);
		
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
		
		JTextArea textArea = new JTextArea();
		lblSimptomi.setLabelFor(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setBounds(138, 92, 231, 105);
		dlgPosjeteRegistracija.getContentPane().add(textArea);
		
		JLabel lblDijagnoza = new JLabel("Dijagnoza");
		lblDijagnoza.setBounds(31, 213, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblDijagnoza);
		
		JLabel lblCijena = new JLabel("Cijena");
		lblCijena.setBounds(420, 162, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblCijena);
		
		txtDijagnoza = new JTextField();
		lblDijagnoza.setLabelFor(txtDijagnoza);
		txtDijagnoza.setBounds(138, 210, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(txtDijagnoza);
		txtDijagnoza.setColumns(10);
		
		textFieldCijena = new JTextField();
		lblCijena.setLabelFor(textFieldCijena);
		textFieldCijena.setColumns(10);
		textFieldCijena.setBounds(527, 159, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textFieldCijena);
		
		JButton btnRegistruj = new JButton("Registruj pacijenta");
		btnRegistruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRegistruj.setBounds(138, 256, 150, 25);
		dlgPosjeteRegistracija.getContentPane().add(btnRegistruj);
		
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
		
		JLabel lblMaterijal = new JLabel("Materijal");
		lblMaterijal.setBounds(420, 90, 56, 22);
		dlgPosjeteRegistracija.getContentPane().add(lblMaterijal);
		
		JComboBox comboBoxMaterijal = new JComboBox();
		lblMaterijal.setLabelFor(comboBoxMaterijal);
		comboBoxMaterijal.setEditable(true);
		comboBoxMaterijal.setBounds(527, 90, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(comboBoxMaterijal);
		
		JLabel lblKoliinaMaterijala = new JLabel("Koli\u010Dina materijala [mg]");
		lblKoliinaMaterijala.setBounds(420, 124, 150, 22);
		dlgPosjeteRegistracija.getContentPane().add(lblKoliinaMaterijala);
		
		JSpinner spinnerKolicinaMaterijala = new JSpinner();
		spinnerKolicinaMaterijala.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(100)));
		spinnerKolicinaMaterijala.setBounds(642, 124, 116, 22);
		dlgPosjeteRegistracija.getContentPane().add(spinnerKolicinaMaterijala);
		
		JButton btnDodajZahvat = new JButton("Dodaj zahvat");
		btnDodajZahvat.setBounds(548, 256, 107, 25);
		dlgPosjeteRegistracija.getContentPane().add(btnDodajZahvat);
		
		JLabel lblUkupnaCijena = new JLabel("Ukupna cijena");
		lblUkupnaCijena.setBounds(420, 194, 95, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblUkupnaCijena);
		
		textFieldUkCijena = new JTextField();
		lblUkupnaCijena.setLabelFor(textFieldUkCijena);
		textFieldUkCijena.setColumns(10);
		textFieldUkCijena.setBounds(527, 191, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textFieldUkCijena);
		dlgPosjeteRegistracija.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBoxPacijent, textArea, txtDijagnoza, btnRegistruj, comboBoxZahvati, comboBoxMaterijal, spinnerKolicinaMaterijala, textFieldCijena, textFieldUkCijena, btnDodajZahvat, lblInformacijeOPacijentu, separator_pacijent, lblPacijent, lblSimptomi, lblDijagnoza, lblCijena, lblInformacijeOZahvatu, separator_zahvat, lblZahvat, lblMaterijal, lblKoliinaMaterijala, lblUkupnaCijena, dlgPosjeteRegistracija.getContentPane()}));
	}
}
