package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerDateModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.App;
import ba.unsa.etf.si.tim12.bll.service.IzvjestajManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.OdradjenePosjeteRowVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.OdradjenePosjeteVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaRowVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JRadioButton;


public class IzvjestajPosjeteDSM {
	private JDialog frame;
	private JTable table;
	private JSpinner spinnerOd;
	private JSpinner spinnerDo;
	private JRadioButton rdbtnDnevni;
	private JRadioButton rdbtnSedmini;
	private JRadioButton rdbtnMjeseni;
	private Calendar calendar;
	final static Logger logger = Logger.getLogger(IzvjestajPosjeteDSM.class);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajPosjeteDSM window = new IzvjestajPosjeteDSM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.debug("Došlo je do greške.", e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajPosjeteDSM() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Izvještaj o posjetama");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		
		frame.setBounds(100, 100, 550, 390);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 147, 503, 157);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new UneditableTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Ime i prezime", "Doktor", "Vrijeme posjete"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Odaberite vremenski interval:");
		lblPretraivanjePo.setBounds(22, 13, 176, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		JButton btnModifikacijaMaterijala = new JButton("Prikaži");
		btnModifikacijaMaterijala.setBounds(263, 317, 121, 23);
		btnModifikacijaMaterijala.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Session sesija = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				try {
					sesija = HibernateUtil.getSessionFactory().openSession();
					IzvjestajManager izvjestaji = new IzvjestajManager(sesija);
					ArrayList<OdradjenePosjeteRowVM> redovi = null;
					
					if(rdbtnDnevni.isSelected()) {
						OdradjenePosjeteVM posjete = izvjestaji.odradjenePosjetePoDanu((Date) spinnerOd.getValue());
						redovi = posjete.getOdradjenePosjete();					
					}
					
					if(rdbtnSedmini.isSelected() || rdbtnMjeseni.isSelected()) {
						OdradjenePosjeteVM posjete = izvjestaji.odradjenePosjete((Date) spinnerOd.getValue(), (Date) spinnerDo.getValue()); 
						redovi = posjete.getOdradjenePosjete();
					}
					
					Object[][] data = new Object[redovi.size()][];
					
					
					
					for(int i = 0; i < redovi.size(); i++){
						data[i] = new Object[]{ redovi.get(i).getId(), redovi.get(i).getIme(), 
								redovi.get(i).getDoktor(),sdf.format(redovi.get(i).getVrijeme())};
					}
					
					String[] columns = new String[]{"ID", "Ime i prezime", "Doktor", "Vrijeme posjete"};
					table.setModel(new UneditableTableModel(
						data,
						columns
					));
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
		btnOdustani.setBounds(404, 317, 121, 23);
		btnOdustani.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				frame.setVisible (false);
				frame.dispose();
				
			}
		});
		frame.getContentPane().add(btnOdustani);
		
		calendar = Calendar.getInstance();
		Date initDate = calendar.getTime();
		spinnerOd = new JSpinner();
		spinnerOd.setModel(new SpinnerDateModel(new Date(-2208992399907L), null, null, Calendar.DAY_OF_YEAR));
		spinnerOd.setEditor(new JSpinner.DateEditor(spinnerOd, "dd/MM/yyyy"));
		spinnerOd.setBounds(227, 47, 171, 22);
		frame.getContentPane().add(spinnerOd);
		
		spinnerDo = new JSpinner();
		spinnerDo.setBounds(225, 91, 171, 22);
		spinnerDo.setModel(new SpinnerDateModel(initDate, null, null, Calendar.DAY_OF_YEAR));
		spinnerDo.setEditor(new JSpinner.DateEditor(spinnerDo, "dd/MM/yyyy"));
		frame.getContentPane().add(spinnerDo);
		
		JLabel lblDo = new JLabel("Do:");
		lblDo.setBounds(194, 94, 28, 16);
		frame.getContentPane().add(lblDo);
		
		JLabel lblOd = new JLabel("Od:");
		lblOd.setBounds(194, 50, 28, 16);
		frame.getContentPane().add(lblOd);
		
		rdbtnDnevni = new JRadioButton("Dnevni");
		rdbtnDnevni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				spinnerDo.setValue(spinnerOd.getValue());
			}
		});
		rdbtnDnevni.setSelected(true);
		rdbtnDnevni.setBounds(22, 41, 72, 25);
		frame.getContentPane().add(rdbtnDnevni);
		
		rdbtnSedmini = new JRadioButton("Sedmični");
		rdbtnSedmini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date datumDo = (Date) spinnerOd.getValue();
				calendar.setTime(datumDo);
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 6);
				datumDo = calendar.getTime();
				spinnerDo.setValue(datumDo);
			}
		});
		rdbtnSedmini.setBounds(22, 71, 81, 25);
		frame.getContentPane().add(rdbtnSedmini);
		
		rdbtnMjeseni = new JRadioButton("Mjesečni");
		rdbtnMjeseni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date datumDo = (Date) spinnerOd.getValue();
				calendar.setTime(datumDo);
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
				datumDo = calendar.getTime();
				spinnerDo.setValue(datumDo);
			}
		});
		rdbtnMjeseni.setBounds(22, 101, 88, 25);
		frame.getContentPane().add(rdbtnMjeseni);
		
		ButtonGroup rbuttons = new ButtonGroup();
		rbuttons.add(rdbtnMjeseni);
		rbuttons.add(rdbtnSedmini);
		rbuttons.add(rdbtnDnevni);
	}
}
