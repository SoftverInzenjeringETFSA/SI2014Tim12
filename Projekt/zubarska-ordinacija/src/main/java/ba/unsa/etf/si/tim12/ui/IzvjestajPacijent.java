package ba.unsa.etf.si.tim12.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IzvjestajPacijent {

	private JDialog frame;
	private JTable table;
	private JLabel lblPacijent;
	private JComboBox comboBox;


	/**
	 * Create the application.
	 */
	public IzvjestajPacijent() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Izvje\u0161taj o obavljenim posjetama pacijenta");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 364);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 90, 503, 185);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new UneditableTableModel(
			new Object[][] {
			},
			new String[] {
				"Opis zahvata", "Dijagnoza", "Doktor", "Vrijeme posjete"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
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
		
		lblPacijent = new JLabel("Pacijent:");
		lblPacijent.setBounds(22, 47, 78, 28);
		frame.getContentPane().add(lblPacijent);
		
		comboBox = new JComboBox();
		comboBox.setBounds(110, 51, 130, 20);
		frame.getContentPane().add(comboBox);
	}
}
