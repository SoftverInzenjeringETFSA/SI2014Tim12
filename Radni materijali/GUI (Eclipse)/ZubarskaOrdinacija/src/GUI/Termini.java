package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Window.Type;

import javax.swing.JSpinner;

import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Termini {

	private JDialog dlgR;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Termini window = new Termini();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Termini() {
		initialize();
		dlgR.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dlgR = new JDialog();
		MainForma.Prekini(dlgR);
		dlgR.setModalityType(ModalityType.APPLICATION_MODAL);
		dlgR.setTitle("Termini");
		dlgR.setBounds(100, 100, 465, 480);
		dlgR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgR.getContentPane().setLayout(null);
		dlgR.setLocationRelativeTo(null);
		JXMonthView monthView = new JXMonthView();
		monthView.setBounds(25, 30, 245, 176);
		dlgR.getContentPane().add(monthView);
		
		JLabel lblVrstaPregleda = new JLabel("Vrsta pregleda:");
		lblVrstaPregleda.setBounds(293, 31, 200, 22);
		dlgR.getContentPane().add(lblVrstaPregleda);
		
		JRadioButton rdbtnSedmicni = new JRadioButton("Sedmi\u010Dni");
		rdbtnSedmicni.setBounds(293, 73, 127, 25);
		dlgR.getContentPane().add(rdbtnSedmicni);
		
		JRadioButton rdbtnMjesecni = new JRadioButton("Mjese\u010Dni");
		rdbtnMjesecni.setBounds(293, 103, 127, 25);
		dlgR.getContentPane().add(rdbtnMjesecni);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(rdbtnMjesecni);
		grp.add(rdbtnSedmicni);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(293, 55, 95, 9);
		dlgR.getContentPane().add(separator);
		
		JButton btnNoviTermin = new JButton("Registruj novi termin");
		btnNoviTermin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NoviTermin();
			}
		});
		btnNoviTermin.setBounds(259, 393, 161, 25);
		dlgR.getContentPane().add(btnNoviTermin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 235, 395, 145);
		dlgR.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Vrijeme", "Pacijent", "Zubar"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(105);
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		scrollPane.setViewportView(table);		
	}
}
