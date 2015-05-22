package ba.unsa.etf.si.tim12.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PrikazZahvataGUI {

	private JDialog frame;
	private JTable table;
	private JTextField textField;
	private JButton btnNewButton;



	/**
	 * Create the application.
	 */
	public PrikazZahvataGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Prikaz zahvata");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setBounds(100, 100, 524, 364);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 67, 471, 221);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new UneditableTableModel(
			new Object[][] {
			},
			new String[] {
			 "Naziv", "Materijali", "Cijena"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Pretra\u017Eivanje po imenu zahvata:");
		lblPretraivanjePo.setBounds(22, 21, 204, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		textField = new JTextField();
		textField.setBounds(226, 20, 158, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Pretra\u017Ei");
		//ENIL: mijenjam ikonu, path
		btnNewButton.setIcon(new ImageIcon("src/main/resources/SearchIcon.png"));
		btnNewButton.setBounds(394, 20, 99, 20);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnModifikacijaMaterijala = new JButton("Obri\u0161i");
		btnModifikacijaMaterijala.setBounds(240, 299, 121, 23);
		frame.getContentPane().add(btnModifikacijaMaterijala);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(372, 299, 121, 23);
		frame.getContentPane().add(btnOdustani);
		
		JButton button = new JButton("Modifikacija cijene");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifikacijaZahvataGUI();
			}
		});
		button.setBounds(85, 299, 145, 23);
		frame.getContentPane().add(button);
	}
}
