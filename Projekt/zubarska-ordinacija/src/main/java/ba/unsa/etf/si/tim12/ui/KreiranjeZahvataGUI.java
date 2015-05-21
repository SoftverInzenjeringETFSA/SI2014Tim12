package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.Dialog.ModalityType;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBoxMenuItem;

import java.awt.List;

import javax.swing.JComboBox;


public class KreiranjeZahvataGUI {

	private JDialog frmKreiranjeZahvata;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * Create the application.
	 */
	public KreiranjeZahvataGUI() {
		initialize();
		frmKreiranjeZahvata.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeZahvata = new JDialog();
		MainForma.Prekini(frmKreiranjeZahvata);
		frmKreiranjeZahvata.setModalityType(ModalityType.APPLICATION_MODAL);
		frmKreiranjeZahvata.setResizable(false);
		frmKreiranjeZahvata.setTitle("Kreiranje zahvata");
		frmKreiranjeZahvata.setBounds(100, 100, 358, 407);
		frmKreiranjeZahvata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeZahvata.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmKreiranjeZahvata.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmKreiranjeZahvata.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(86, 11, 248, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(89, 204, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblIme = new JLabel("Naziv:");
		lblIme.setBounds(10, 11, 86, 20);
		panel_1.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Cijena:");
		lblPrezime.setBounds(10, 204, 86, 20);
		panel_1.add(lblPrezime);
		
		JButton btnKreiraj = new JButton("Kreiraj");
		btnKreiraj.setBounds(225, 330, 109, 23);
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(106, 330, 109, 23);
		panel_1.add(btnOdustani);
		
		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setBounds(10, 235, 86, 20);
		panel_1.add(lblOpis);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(89, 235, 245, 78);
		panel_1.add(textPane);
		
		JLabel lblNewLabel = new JLabel("Materijali:");
		lblNewLabel.setBounds(10, 109, 65, 23);
		panel_1.add(lblNewLabel);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(89, 112, 245, 78);
		panel_1.add(list);
		
		JLabel lblBam = new JLabel("BAM");
		lblBam.setBounds(185, 204, 86, 20);
		panel_1.add(lblBam);
		
		JLabel lblNewLabel_1 = new JLabel("Materijal:");
		lblNewLabel_1.setBounds(10, 42, 76, 20);
		panel_1.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(86, 42, 112, 20);
		panel_1.add(comboBox);
		
		JLabel lblKoliina = new JLabel("Koli\u010Dina:");
		lblKoliina.setBounds(218, 42, 57, 20);
		panel_1.add(lblKoliina);
		
		textField_2 = new JTextField();
		textField_2.setBounds(277, 42, 57, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(86, 75, 89, 23);
		panel_1.add(btnDodaj);
	}
}
