package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalityType;


public class PrikazMaterijalaGUI {

	private JDialog frame;
	private JTable table;
	private JTextField textField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazMaterijalaGUI window = new PrikazMaterijalaGUI();
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
	public PrikazMaterijalaGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setTitle("Prikaz materijala");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setBounds(100, 100, 524, 364);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 483, 221);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			 "Ime materijala", "Jedinièna cijena", "Mjerna jedinica"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Pretra\u017Eivanje po imenu materijala:");
		lblPretraivanjePo.setBounds(10, 21, 204, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		textField = new JTextField();
		textField.setBounds(224, 20, 149, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Pretra\u017Ei");
		btnNewButton.setIcon(new ImageIcon(PrikazMaterijalaGUI.class.getResource("/GUI/SearchIcon.png")));
		btnNewButton.setBounds(394, 20, 99, 20);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnModifikacijaMaterijala = new JButton("Modifikacija");
		btnModifikacijaMaterijala.setBounds(240, 299, 121, 23);
		frame.getContentPane().add(btnModifikacijaMaterijala);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(372, 299, 121, 23);
		frame.getContentPane().add(btnOdustani);
	}
}
