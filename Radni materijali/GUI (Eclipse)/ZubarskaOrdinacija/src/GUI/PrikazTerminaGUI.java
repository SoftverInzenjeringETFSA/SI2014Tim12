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


public class PrikazTerminaGUI {

	private JDialog frame;
	private JButton btnNewButton;
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazTerminaGUI window = new PrikazTerminaGUI();
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
	public PrikazTerminaGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Termini");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setBounds(100, 100, 518, 354);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JLabel lblPretraivanjePo = new JLabel("Termini do datuma:");
		lblPretraivanjePo.setBounds(10, 55, 181, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		btnNewButton = new JButton("Pretra\u017Ei");
		btnNewButton.setIcon(new ImageIcon(PrikazTerminaGUI.class.getResource("/GUI/SearchIcon.png")));
		btnNewButton.setBounds(403, 95, 99, 20);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 492, 151);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Vrijeme", "Pacijent", "Doktor"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblDoktor = new JLabel("Doktor:");
		lblDoktor.setBounds(10, 96, 181, 19);
		frame.getContentPane().add(lblDoktor);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(166, 96, 192, 19);
		frame.getContentPane().add(textField_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(165, 54, 193, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnOdkaiTermin = new JButton("Otka\u017Ei termin");
		btnOdkaiTermin.setBounds(362, 291, 140, 23);
		frame.getContentPane().add(btnOdkaiTermin);
		
		JLabel lblTerminiOdDatuma = new JLabel("Termini od datuma:");
		lblTerminiOdDatuma.setBounds(10, 25, 181, 19);
		frame.getContentPane().add(lblTerminiOdDatuma);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(165, 23, 193, 20);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnDodajPosjetu = new JButton("Dodaj posjetu");
		btnDodajPosjetu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Posjete();
			}
		});
		btnDodajPosjetu.setBounds(210, 291, 140, 23);
		frame.getContentPane().add(btnDodajPosjetu);
	}
}
