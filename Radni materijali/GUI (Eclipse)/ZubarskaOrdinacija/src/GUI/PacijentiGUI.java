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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PacijentiGUI {

	private JDialog frame;
	private JTable table;
	private JToolBar toolBar;
	private JButton btnNoviPacijent;
	private JTextField textField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacijentiGUI window = new PacijentiGUI();
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
	public PacijentiGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setBounds(100, 100, 518, 354);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 492, 197);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Ime", "Prezime", "Broj telefona"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setEnabled(false);
		toolBar.setBounds(0, 30, 514, 27);
		frame.getContentPane().add(toolBar);
		
		btnNoviPacijent = new JButton("Novi pacijent");
		btnNoviPacijent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new KreiranjePacijentaGUI();
			}
		});
		btnNoviPacijent.setIcon(new ImageIcon(PacijentiGUI.class.getResource("/GUI/AddIcon.png")));
		toolBar.add(btnNoviPacijent);
		
		JButton btnModifikacijaPacijenta = new JButton("Modifikacija pacijenta");
		btnModifikacijaPacijenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ModifikacijaPacijentaGUI();
			}
		});
		toolBar.add(btnModifikacijaPacijenta);
		
		JLabel lblPretraivanjePo = new JLabel("Pretra\u017Eivanje po:");
		lblPretraivanjePo.setBounds(10, 69, 103, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Prezimenu", "Imenu", "ID-u", "Opisu"}));
		comboBox.setBounds(116, 68, 118, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(244, 68, 149, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Pretra\u017Ei");
		btnNewButton.setIcon(new ImageIcon(PacijentiGUI.class.getResource("/GUI/SearchIcon.png")));
		btnNewButton.setBounds(403, 68, 99, 20);
		frame.getContentPane().add(btnNewButton);
	}
}
