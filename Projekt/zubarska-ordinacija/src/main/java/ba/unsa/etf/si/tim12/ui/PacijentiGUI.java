package ba.unsa.etf.si.tim12.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.PacijentManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PrikazPacijentaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PacijentiGUI {

	private JDialog frame;
	private JTable table;
	private JToolBar toolBar;
	private JButton btnNoviPacijent;
	private JTextField textField;
	private JButton btnNewButton;
	private JComboBox comboBox;
	ArrayList<PacijentVM> podaci;
	static final Logger logger = Logger.getLogger(PacijentiGUI.class);

	/**
	 * Create the application.
	 */
	public PacijentiGUI() {
		initialize();
		frame.setVisible(true);
		podaci = null;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setTitle("Pacijenti");
		frame.setResizable(false);
		frame.setBounds(100, 100, 518, 354);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 492, 197);

		frame.getContentPane().add(scrollPane);

		table = new JTable();

		table.setModel(new UneditableTableModel(new Object[][] {},
				new String[] { "ID", "Ime i prezime", "Broj telefona",
						"Datum rođenja", "Opis" }));

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
			public void actionPerformed(ActionEvent arg0) {
				new KreiranjePacijentaGUI();
				if(podaci != null)
					Pretrazi();
			}
		});
		// btnNoviPacijent.setIcon(new
		// ImageIcon(PacijentiGUI.class.getResource("/GUI/AddIcon.png")));
		// ENIL: mijenjam ovo zbog integracije
		btnNoviPacijent
				.setIcon(new ImageIcon("src/main/resources/AddIcon.png"));
		toolBar.add(btnNoviPacijent);

		JButton btnModifikacijaPacijenta = new JButton("Modifikacija pacijenta");
		btnModifikacijaPacijenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Integer index = table.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(frame,
							"Odaberite pacijenta u tabeli", "Obavještenje",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				new ModifikacijaPacijentaGUI(podaci.get(index));
				PrikaziPodatke(podaci);
			}
		});
		toolBar.add(btnModifikacijaPacijenta);
		JButton BtnInfo = new JButton("Informacije o pacijentu");
		BtnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Integer index = table.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(frame,
							"Odaberite pacijenta u tabeli", "Obavještenje",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				PacijentVM pp = podaci.get(index);
				Session sess = null;
				//podaci = null;
				try {
					String opcija = (String) comboBox.getSelectedItem();
					String text = textField.getText();

					sess = HibernateUtil.getSessionFactory().openSession();
					PacijentManager pm = new PacijentManager(sess);
					PrikazPacijentaVM pvm = pm.dajPacijenta(pp.getId());
					new PrikazPacijentaGUI(pvm);
				}
				catch (Exception er) {

					logger.debug(er.getMessage(), er);
					JOptionPane.showMessageDialog(frame, er.getMessage(),
							"Greška", JOptionPane.INFORMATION_MESSAGE);

				} finally {
					if (sess != null)
						sess.close();
				}
				PrikaziPodatke(podaci);
			}
		});
		toolBar.add (BtnInfo);
		JLabel lblPretraivanjePo = new JLabel("Pretra\u017Eivanje po:");
		lblPretraivanjePo.setBounds(10, 69, 103, 19);
		frame.getContentPane().add(lblPretraivanjePo);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Imenu i prezimenu", "ID-u", "Opisu" }));
		comboBox.setBounds(116, 68, 118, 20);
		frame.getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setBounds(244, 68, 149, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		btnNewButton = new JButton("Pretra\u017Ei");
		// btnNewButton.setIcon(new
		// ImageIcon(PacijentiGUI.class.getResource("/GUI/SearchIcon.png")));
		// ENIL: mijenjam...
		btnNewButton
				.setIcon(new ImageIcon("src/main/resources/SearchIcon.png"));
		btnNewButton.setBounds(403, 68, 99, 20);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Pretrazi();
			}

		});
		frame.getContentPane().add(btnNewButton);
	}

	protected void Pretrazi() {

		Session sess = null;
		podaci = null;
		try {
			String opcija = (String) comboBox.getSelectedItem();
			String text = textField.getText();

			sess = HibernateUtil.getSessionFactory().openSession();
			PacijentManager pManager = new PacijentManager(sess);
			if (opcija.equals("Imenu i prezimenu")) {
				podaci = pManager.nadjiPoImenu(text);
			} else if (opcija.equals("ID-u")) {

				podaci = pManager.nadjiPoIdu(Integer.parseInt(text));

			} else if (opcija.equals("Opisu")) {

				podaci = pManager.nadjiPoOpisu(text);

			} else {
				JOptionPane.showMessageDialog(frame,
						"Odaberite način pretrage", "Obavještenje",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NumberFormatException ex) {

			JOptionPane.showMessageDialog(frame,
					"Za pretraživanje unesite broj", "Greška",
					JOptionPane.ERROR_MESSAGE);

		} catch (Exception er) {

			logger.debug(er.getMessage(), er);
			JOptionPane.showMessageDialog(frame, er.getMessage(),
					"Greška", JOptionPane.INFORMATION_MESSAGE);

		} finally {
			if (sess != null)
				sess.close();
		}

		if (podaci != null)
			PrikaziPodatke(podaci);

	}

	private void PrikaziPodatke(ArrayList<PacijentVM> pacijenti) {
		if (pacijenti == null)
			return;

		String[][] data = new String[pacijenti.size()][];
		for (int i = 0; i < pacijenti.size(); i++) {
			data[i] = PacijentVMtoObjectRow(pacijenti.get(i));
		}

		String[] columns = new String[] { "ID", "Ime i prezime",
				"Broj telefona", "Datum rođenja", "Opis" };

		table.setModel(new UneditableTableModel(data, columns));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
	}

	private String[] PacijentVMtoObjectRow(PacijentVM p) {
		DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		String[] r = { Long.toString(p.getId()), p.getImePrezime(),
				p.getBrojTelefona(), f.format(p.getDatumRodjenja()),
				p.getOpis() };
		return r;
	}
}
