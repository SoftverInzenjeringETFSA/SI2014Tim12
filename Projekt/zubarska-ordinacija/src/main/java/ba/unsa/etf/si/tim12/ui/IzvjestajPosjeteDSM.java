package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IzvjestajPosjeteDSM {

	private JDialog frame;
	private JTable table;
	private JLabel lblOd;
	private JLabel lblOddo;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JLabel lblVrstaPregleda;
	private JComboBox comboBox_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajPosjeteDSM window = new IzvjestajPosjeteDSM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajPosjeteDSM() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Izvje\u0161taj o posjetama");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		
		frame.setBounds(100, 100, 550, 364);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 118, 503, 157);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Ime", "Prezime", "Doktor", "Vrijeme posjete"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Odaberite vremenski interval:");
		lblPretraivanjePo.setBounds(22, 57, 204, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		JButton btnModifikacijaMaterijala = new JButton("Prika\u017Ei");
		btnModifikacijaMaterijala.setBounds(262, 299, 121, 23);
		btnModifikacijaMaterijala.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
					    "Nije implementirano.",
					    "Obavje�tenje",
					    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		frame.getContentPane().add(btnModifikacijaMaterijala);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(404, 299, 121, 23);
		btnOdustani.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				frame.setVisible (false);
				frame.dispose();
				
			}
		});
		frame.getContentPane().add(btnOdustani);
		
		lblOd = new JLabel("Od:");
		lblOd.setBounds(22, 83, 27, 28);
		frame.getContentPane().add(lblOd);
		
		lblOddo = new JLabel("Do:");
		lblOddo.setBounds(294, 83, 27, 28);
		frame.getContentPane().add(lblOddo);
		
		comboBox = new JComboBox();
		comboBox.setBounds(60, 87, 49, 20);
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(127, 87, 49, 20);
		frame.getContentPane().add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(196, 87, 68, 20);
		frame.getContentPane().add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setBounds(334, 87, 49, 20);
		frame.getContentPane().add(comboBox_3);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setBounds(398, 87, 49, 20);
		frame.getContentPane().add(comboBox_4);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setBounds(457, 87, 68, 20);
		frame.getContentPane().add(comboBox_5);
		
		lblVrstaPregleda = new JLabel("Vrsta pregleda:");
		lblVrstaPregleda.setBounds(22, 24, 110, 14);
		frame.getContentPane().add(lblVrstaPregleda);
		
		comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"Dnevni", "Sedmi\u010Dni", "Mjese\u010Dni"}));
		comboBox_6.setBounds(132, 18, 94, 20);
		frame.getContentPane().add(comboBox_6);
	}
}
