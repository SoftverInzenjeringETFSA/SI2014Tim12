package ba.unsa.etf.si.tim12.ui;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.MaterijaliManager;
import ba.unsa.etf.si.tim12.bll.service.PosjetaManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PosjetaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class PosjeteGUI {

	private JDialog frame;
	private JTable table;
	private JTextField textField;
	private JButton btnNewButton;
	static final Logger logger = Logger.getLogger(PrikazMaterijalaGUI.class);


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
				"ID", "Pacijent", "Doktor", "Dijagnoza"
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session sess = null;
				// dodavanje pretrazenih materijala u tabelu
				try {
					sess = HibernateUtil.getSessionFactory().openSession();
					PosjetaManager m = new PosjetaManager(sess);
					ArrayList<PosjetaVM> nadjenePosjete = m.nadjiPoDijagnozi(textField.getText());
					// prvo praznjenje
					table.setModel(new UneditableTableModel(
							new Object[][] {},
							new String[] { "ID", "Pacijent", "Doktor", "Dijagnoza"
							}));
					UneditableTableModel model = (UneditableTableModel) table
							.getModel();
					for (PosjetaVM posjeta : nadjenePosjete) {
						model.addRow(new Object[] { posjeta.getId(),
								posjeta.getPacijenti(), posjeta.getDoktor(),
								posjeta.getDijagnoza() });
					}

				} catch (Exception ex) {
					logger.debug(ex.getMessage(), ex);
				} finally {
					if (sess != null)
						sess.close();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon("src/main/resources/SearchIcon.png"));
		btnNewButton.setBounds(403, 68, 99, 20);
		frame.getContentPane().add(btnNewButton);
	}
}
