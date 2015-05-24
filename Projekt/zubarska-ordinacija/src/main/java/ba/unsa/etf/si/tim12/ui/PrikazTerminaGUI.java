package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
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

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.MaterijaliManager;
import ba.unsa.etf.si.tim12.bll.service.PacijentManager;
import ba.unsa.etf.si.tim12.bll.service.TerminManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.PrikazPacijentaVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TerminVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;


public class PrikazTerminaGUI {

	private JDialog frame;
	private JButton btnNewButton;
	private JTable table;
	private JTextField textField_1;
    private Date now = new Date();
    private Date noww = new Date();
    final SpinnerDateModel model1 = new SpinnerDateModel(noww, null, noww,
            Calendar.DAY_OF_WEEK);	    
	JSpinner spinner = new JSpinner(model1);
    final SpinnerDateModel model2 = new SpinnerDateModel(now, null, now,
            Calendar.DAY_OF_WEEK);
    JSpinner spinner_1 = new JSpinner(model2);
	static final Logger logger = Logger.getLogger(PrikazTerminaGUI.class);
	ArrayList<TerminVM> lista = null;
	/**
	 * Create the application.
	 */
	public PrikazTerminaGUI() {
		initialize();
		frame.setVisible(true);
		IzlistajTermine("", new Date(), new Date(), true);
	}

	private void IzlistajTermine (String pattern, Date p, Date k, boolean svi)
	{
		Session sess = null;
		try {
			sess = HibernateUtil.getSessionFactory().openSession();
			TerminManager tm = new TerminManager(sess);
			
			ArrayList<TerminVM> ter = null;
			if (!svi)
				ter = tm.nadjiPoVremenu(p,  k);
			else ter = tm.dajSveTermine();
			
			if (ter.size() == 0)
				return;
			lista = ter;
			
			//JAVA :S
			DefaultTableModel dm = (DefaultTableModel) table.getModel();
			int rowCount = dm.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
			    dm.removeRow(i);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm");
			for (int i = 0; i < ter.size(); ++i)
				{
					if (!pattern.trim().equals("")) //ima i neki pattern pretrage po doktoru
					{
						if (!ter.get(i).getDoktor().toLowerCase().contains(pattern.toLowerCase()))
							continue; //preskoči ako pattern nije zadovoljen
						//posto TerminManager nema metodu getPoDoktoru :P
					}
					PacijentManager pm = new PacijentManager(sess);
					PrikazPacijentaVM pvm = pm.dajPacijenta(ter.get(i).getPacijentId());
					dm.addRow(new Object [] {sdf.format(ter.get(i).getVrijeme()), pvm.getImeIPrezime(), ter.get(i).getDoktor()});
				}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				sess.close();
		}

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

		//ENIL: mijenjam path ikone
		btnNewButton.setIcon(new ImageIcon("src/main/resources/SearchIcon.png"));
		btnNewButton.setBounds(403, 95, 99, 20);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 492, 151);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new UneditableTableModel(
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
		
		JButton btnOdkaiTermin = new JButton("Otka\u017Ei termin");
		btnOdkaiTermin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				
				if (table.getSelectedRows().length < 1)
				{
					JOptionPane.showMessageDialog(frame,
							"Niste odabrali niti jedan termin!", "Obavještenje",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Session sess = null;
				try {
					sess = HibernateUtil.getSessionFactory().openSession();
					TerminManager tm = new TerminManager(sess);
					if (tm.otkaziTermin((long)lista.get(table.getSelectedRow()).getId()))
					{
						JOptionPane.showMessageDialog(frame,
								"Odabrani termin je uspješno otkazan!", "Obavještenje",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(frame,
								"Desila se greška prilikom otkazivanja termina.", "Greška",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
				} finally {
					if (sess != null)
						sess.close();
				}
			}
		});
		btnOdkaiTermin.setBounds(362, 291, 140, 23);
		frame.getContentPane().add(btnOdkaiTermin);
		
		JLabel lblTerminiOdDatuma = new JLabel("Termini od datuma:");
		lblTerminiOdDatuma.setBounds(10, 25, 181, 19);
		frame.getContentPane().add(lblTerminiOdDatuma);
		
		JButton btnDodajPosjetu = new JButton("Dodaj posjetu");
		btnDodajPosjetu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Posjete();
				//nakon što doda, ponovo izlistaj termine 
				IzlistajTermine("", null, null, true);
			}
		});
		btnDodajPosjetu.setBounds(210, 291, 140, 23);
		frame.getContentPane().add(btnDodajPosjetu);
        now = new Date();
        noww = new Date();
        
        try {
			noww = new SimpleDateFormat("YYYY").parse("2000");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	    JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spinner,
	    		"dd-MM-YYYY");
	    spinner.setEditor(editor1);
		spinner.setBounds(166, 24, 192, 20);
		frame.getContentPane().add(spinner);
	    
	    		
	    JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner_1,"dd-MM-YYYY");
		spinner_1.setEditor(editor2);
		spinner_1.setBounds(166, 54, 192, 20);
		frame.getContentPane().add(spinner_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					IzlistajTermine(textField_1.getText().toLowerCase(),
							(Date)spinner.getValue(), (Date)spinner_1.getValue(), false);
			}
		});
	}
}
