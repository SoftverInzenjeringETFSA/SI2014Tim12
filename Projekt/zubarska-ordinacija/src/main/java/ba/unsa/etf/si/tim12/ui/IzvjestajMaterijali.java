package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IzvjestajMaterijali {

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



	/**
	 * Create the application.
	 */
	public IzvjestajMaterijali() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setTitle("Finansijski izvjestaj materijala");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		
		frame.setBounds(100, 100, 550, 364);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		MainForma.Prekini (frame);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 90, 503, 185);
		frame.getContentPane().add(scrollPane);
		frame.setLocationRelativeTo(null);
		table = new JTable();
		table.setModel(new UneditableTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Materijal", "Jed.cijena", "m.jedinica", "Koli\u010Dina", "Ukupna cijena"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Odaberite vremenski interval:");
		lblPretraivanjePo.setBounds(22, 21, 204, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		JButton btnModifikacijaMaterijala = new JButton("Prika\u017Ei");
		btnModifikacijaMaterijala.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
					    "Nije implementirano.",
					    "Obavje�tenje",
					    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnModifikacijaMaterijala.setBounds(262, 299, 121, 23);
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
		lblOd.setBounds(22, 47, 27, 28);
		frame.getContentPane().add(lblOd);
		
		lblOddo = new JLabel("Do:");
		lblOddo.setBounds(297, 47, 27, 28);
		frame.getContentPane().add(lblOddo);
		
		comboBox = new JComboBox();
		comboBox.setBounds(46, 51, 49, 20);
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(105, 51, 49, 20);
		frame.getContentPane().add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(164, 51, 68, 20);
		frame.getContentPane().add(comboBox_2);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setBounds(334, 51, 49, 20);
		frame.getContentPane().add(comboBox_3);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setBounds(398, 51, 49, 20);
		frame.getContentPane().add(comboBox_4);
		
		comboBox_5 = new JComboBox();
		comboBox_5.setBounds(457, 51, 68, 20);
		frame.getContentPane().add(comboBox_5);
	}
}
