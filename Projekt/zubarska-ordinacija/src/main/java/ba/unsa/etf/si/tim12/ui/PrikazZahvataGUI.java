package ba.unsa.etf.si.tim12.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

import org.hibernate.Session;
import org.apache.log4j.Logger;

import ba.unsa.etf.si.tim12.bll.service.MaterijaliManager;
import ba.unsa.etf.si.tim12.bll.service.TipZahvataManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class PrikazZahvataGUI {

	private JDialog frame;
	private JTextField textField;
	private JButton btnNewButton;
	static final Logger logger = Logger.getLogger(PrikazZahvataGUI.class);
	ArrayList<TipZahvataVM> podaci;
	private JTable table;



	/**
	 * Create the application.
	 */
	public PrikazZahvataGUI() {
		initialize();
		frame.setVisible(true);
		podaci=null;
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naziv", "Cijena"
			}
		));
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(192, 20, 158, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Pretra\u017Ei");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session sess = null;
				// dodavanje pretrazenih materijala u tabelu
				try {
					sess = HibernateUtil.getSessionFactory().openSession();
					TipZahvataManager m = new TipZahvataManager(sess);
					podaci = m.nadjiPoImenu(textField.getText());
					// prvo praznjenje
					/*table.setModel(new UneditableTableModel(
							new Object[][] {
							},
							new String[] {
							 "Naziv", "Materijali", "Cijena"
							}
						));*/

				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				} finally {
					if (sess != null)
						sess.close();
				}
				if (podaci != null)
					PrikaziPodatke(podaci);
			}

			private void PrikaziPodatke(ArrayList<TipZahvataVM> zahvati) {
				if (zahvati == null)
					return;
				
				String[][] data = new String[zahvati.size()][];
				for (int i = 0; i < zahvati.size(); i++) {
					data[i] = TipZahvataVMtoObjectRow(zahvati.get(i));
				}
				
				String[] columns = new String[] {"Id", "Naziv", "Cijena"
				};
				table.setModel(new UneditableTableModel(data, columns));
				
				
				
			}
			private String[] TipZahvataVMtoObjectRow(TipZahvataVM z) { 
				String[] r = { Long.toString(z.getId()), z.getNaziv(),
						Double.toString(z.getCijena()) };
				return r;
			}
			
		});
		
		
		
		
		
		//ENIL: mijenjam ikonu, path
		btnNewButton.setIcon(new ImageIcon("src/main/resources/SearchIcon.png"));
		btnNewButton.setBounds(394, 20, 99, 20);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnOdustani.setBounds(372, 299, 121, 23);
		frame.getContentPane().add(btnOdustani);
		
		JButton btnIzmeniCjenuZa = new JButton("Izmeni cjenu za zahvat");
		btnIzmeniCjenuZa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Integer index = table.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(frame,
							"Odaberite pacijenta u tabeli", "Obavještenje",
							JOptionPane.INFORMATION_MESSAGE);
				}
				

				new ModifikacijaZahvataGUI(podaci.get(index));
			}
		});
		btnIzmeniCjenuZa.setBounds(150, 299, 200, 23);
		frame.getContentPane().add(btnIzmeniCjenuZa);
		
		JLabel lblPretraivanjePoImenu = new JLabel("Pretraživanje po imenu:");
		lblPretraivanjePoImenu.setBounds(22, 23, 159, 14);
		frame.getContentPane().add(lblPretraivanjePoImenu);
	}
}
