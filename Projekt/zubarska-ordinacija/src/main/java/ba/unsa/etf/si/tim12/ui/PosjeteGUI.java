package ba.unsa.etf.si.tim12.ui;


import javax.swing.JFrame;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;


public class PosjeteGUI {

	private JDialog frame;
	private JTable table;
	private JTextField textField;
	private JButton btnNewButton;


	/**
	 * Create the application.
	 */
	public PosjeteGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Posjete");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setBounds(100, 100, 518, 354);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Pacijent");
		scrollPane.setBounds(10, 118, 492, 197);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new UneditableTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Pacijent", "Doktor", "Dijagnoza", "Zahvat"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Pretra\u017Eivanje po dijagnozi:");
		lblPretraivanjePo.setBounds(10, 69, 181, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		textField = new JTextField();
		textField.setBounds(176, 68, 192, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Pretra\u017Ei");
		btnNewButton.setIcon(new ImageIcon("src/main/resources/SearchIcon.png"));
		btnNewButton.setBounds(403, 68, 99, 20);
		frame.getContentPane().add(btnNewButton);
	}
}
