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

import ba.unsa.etf.si.tim12.bll.service.IzvjestajManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazRowVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;


public class IzvjestajUlazi {

	private JDialog frame;
	private JTable table;
	private JLabel lblOd;
	private JLabel lblOddo;
	private JSpinner spinnerOd;
	private JSpinner spinnerDo;
	private static final Logger logger = Logger.getLogger(IzvjestajUlazi.class);

	/**
	 * Create the application.
	 */
	public IzvjestajUlazi() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Finansijski izvještaj o svim ulazima");
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
		table.setFillsViewportHeight(true);
		table.setModel(new UneditableTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Ime i prezime", "Zahvat", "Vrijeme posjete", "Cijena"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Odaberite vremenski interval:");
		lblPretraivanjePo.setBounds(22, 21, 204, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		JButton btnModifikacijaMaterijala = new JButton("PrikaĹľi");
		btnModifikacijaMaterijala.setBounds(262, 299, 121, 23);
		btnModifikacijaMaterijala.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {				
				Date datumOd = (Date) spinnerOd.getValue();
				Date datumDo = (Date) spinnerDo.getValue();
				Session sesija = null;
				try {
					sesija = HibernateUtil.getSessionFactory().openSession();
					IzvjestajManager izvjestaji = new IzvjestajManager(sesija);
					FinancijskiUlazVM ulazi = izvjestaji.financijskiUlaz(datumOd, datumDo);
					ArrayList<FinancijskiUlazRowVM> redovi = ulazi.getListaFinancijskiUlazRowVM();
					for(int i = 0; i < redovi.size(); i++) {
						table.getModel().setValueAt(redovi.get(i).getId(), i, 0);
						table.getModel().setValueAt(redovi.get(i).getIme(), i, 1);
						table.getModel().setValueAt(redovi.get(i).getImeZahvata(), i, 2);
						table.getModel().setValueAt(redovi.get(i).getVrijemePosjete(), i, 3);
						table.getModel().setValueAt(redovi.get(i).getCijena(), i, 4);
					}					
				}
				catch (Exception e1) {
					logger.debug(e1.getMessage(), e1);
				} 
				finally {
					if (sesija != null)
						sesija.close();
				}
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
		
		lblOd = new JLabel("Od:");
		lblOd.setBounds(22, 47, 27, 28);
		frame.getContentPane().add(lblOd);
		
		lblOddo = new JLabel("Do:");
		lblOddo.setBounds(315, 47, 27, 28);
		frame.getContentPane().add(lblOddo);
		
		Calendar calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();
		spinnerOd = new JSpinner();
		spinnerOd.setModel(new SpinnerDateModel(new Date(-2208992399907L), null, null, Calendar.DAY_OF_YEAR));
		spinnerOd.setEditor(new JSpinner.DateEditor(spinnerOd, "dd/MM/yyyy"));
		spinnerOd.setBounds(73, 50, 171, 22);
		frame.getContentPane().add(spinnerOd);
		
		spinnerDo = new JSpinner();
		spinnerDo.setBounds(354, 50, 171, 22);
		spinnerDo.setModel(new SpinnerDateModel(initDate, null, null, Calendar.DAY_OF_YEAR));
		spinnerDo.setEditor(new JSpinner.DateEditor(spinnerDo, "dd/MM/yyyy"));
		frame.getContentPane().add(spinnerDo);
	}
}
