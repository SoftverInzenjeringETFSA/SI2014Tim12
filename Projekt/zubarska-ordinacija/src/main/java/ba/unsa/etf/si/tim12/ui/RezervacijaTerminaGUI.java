package ba.unsa.etf.si.tim12.ui;


import javax.swing.JFrame;
import org.jdesktop.swingx.JXMonthView;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class RezervacijaTerminaGUI {

	private JDialog dlgR;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Create the application.
	 */
	public RezervacijaTerminaGUI() {
		initialize();
		dlgR.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dlgR = new JDialog();
		MainForma.Prekini(dlgR);
		dlgR.setResizable(false);
		dlgR.setModalityType(ModalityType.APPLICATION_MODAL);
		dlgR.setTitle("Zakazivanje termina");
		dlgR.setBounds(100, 100, 426, 479);
		dlgR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgR.getContentPane().setLayout(null);
		dlgR.setLocationRelativeTo(null);
		JXMonthView monthView = new JXMonthView();
		monthView.setBounds(124, 155, 245, 176);
		dlgR.getContentPane().add(monthView);
		
		ButtonGroup grp = new ButtonGroup();
		
		JButton btnNoviTermin = new JButton("Registruj novi termin");
		btnNoviTermin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNoviTermin.setBounds(208, 394, 161, 25);
		dlgR.getContentPane().add(btnNoviTermin);
		
		JLabel lblPacijent = new JLabel("Pacijent:");
		lblPacijent.setBounds(29, 23, 69, 14);
		dlgR.getContentPane().add(lblPacijent);
		
		JLabel lblDoktor = new JLabel("Doktor:");
		lblDoktor.setBounds(29, 65, 63, 14);
		dlgR.getContentPane().add(lblDoktor);
		
		textField = new JTextField();
		textField.setBounds(124, 60, 245, 25);
		dlgR.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(124, 18, 245, 25);
		dlgR.getContentPane().add(comboBox);
		
		JLabel lblVrijeme = new JLabel("Vrijeme:");
		lblVrijeme.setBounds(29, 108, 69, 14);
		dlgR.getContentPane().add(lblVrijeme);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(124, 97, 245, 25);
		dlgR.getContentPane().add(textField_1);
		
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(29, 155, 69, 14);
		dlgR.getContentPane().add(lblDatum);
	}
}
