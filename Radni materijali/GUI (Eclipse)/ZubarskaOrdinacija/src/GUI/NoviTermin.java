package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Window.Type;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.util.Date;
import java.util.Calendar;

import javax.swing.JButton;

import java.awt.Dialog.ModalityType;

public class NoviTermin {

	public JDialog frmRegistracijaNovogTermina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoviTermin window = new NoviTermin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NoviTermin() {
		initialize();
		frmRegistracijaNovogTermina.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistracijaNovogTermina = new JDialog();
		MainForma.Prekini(frmRegistracijaNovogTermina);
		frmRegistracijaNovogTermina.setModalityType(ModalityType.APPLICATION_MODAL);
		frmRegistracijaNovogTermina.setType(Type.NORMAL);
		frmRegistracijaNovogTermina.setTitle("Registracija novog termina");
		frmRegistracijaNovogTermina.setBounds(100, 100, 386, 245);
		frmRegistracijaNovogTermina.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmRegistracijaNovogTermina.getContentPane().setLayout(null);
		frmRegistracijaNovogTermina.setLocationRelativeTo(null);
		JLabel lblPacijent = new JLabel("Pacijent");
		lblPacijent.setBounds(25, 25, 55, 22);
		frmRegistracijaNovogTermina.getContentPane().add(lblPacijent);
		
		JLabel lblZubar = new JLabel("Zubar");
		lblZubar.setBounds(25, 53, 55, 22);
		frmRegistracijaNovogTermina.getContentPane().add(lblZubar);
		
		JLabel lblDatum = new JLabel("Datum i vrijeme");
		lblDatum.setBounds(25, 81, 91, 22);
		frmRegistracijaNovogTermina.getContentPane().add(lblDatum);
		
		JComboBox comboBoxPacijent = new JComboBox();
		lblPacijent.setLabelFor(comboBoxPacijent);
		comboBoxPacijent.setBounds(139, 25, 200, 22);
		frmRegistracijaNovogTermina.getContentPane().add(comboBoxPacijent);
		
		JComboBox comboBoxZubar = new JComboBox();
		lblZubar.setLabelFor(comboBoxZubar);
		comboBoxZubar.setBounds(139, 53, 200, 22);
		frmRegistracijaNovogTermina.getContentPane().add(comboBoxZubar);
		
		JSpinner spinner = new JSpinner();
		lblDatum.setLabelFor(spinner);
		spinner.setModel(new SpinnerDateModel(new Date(1461621602000L), null, null, Calendar.MONTH));
		spinner.setBounds(139, 81, 200, 22);
		frmRegistracijaNovogTermina.getContentPane().add(spinner);
		
		JButton btnRegistruj = new JButton("Registruj");
		btnRegistruj.setBounds(120, 140, 97, 25);
		frmRegistracijaNovogTermina.getContentPane().add(btnRegistruj);
	}
}
