package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SpinnerDateModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.IzvjestajManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.PotMaterijaliRowVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PotMaterijaliVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class IzvjestajPosjeteZahvati {

	private JDialog frame;
	private JTable table;
	private JLabel lblOd;
	private JLabel lblOddo;
	private JSpinner spinnerOd;
	private JSpinner spinnerDo;
	private static final Logger logger = Logger.getLogger(IzvjestajPosjeteZahvati.class);

	/**
	 * Create the application.
	 */
	public IzvjestajPosjeteZahvati() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Izvje\u0161taj o svim posjetama i zahvatima sa cijenama");
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
		UneditableTableModel tableModel = new UneditableTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Ime i prezime", "Zahvat", "Vrijeme posjete", "Cijena"
				}
			);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Odaberite vremenski interval:");
		lblPretraivanjePo.setBounds(22, 21, 204, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		JButton btnModifikacijaMaterijala = new JButton("Prikaži");
		btnModifikacijaMaterijala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date datumOd = (Date) spinnerOd.getValue();
				Date datumDo = (Date) spinnerDo.getValue();
				Session sesija = null;
				try {
			
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
		btnModifikacijaMaterijala.setBounds(262, 299, 121, 23);
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
		lblOddo.setBounds(297, 47, 27, 28);
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
