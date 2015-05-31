package ba.unsa.etf.si.tim12.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.KorisnikManager;
import ba.unsa.etf.si.tim12.bll.service.PacijentManager;
import ba.unsa.etf.si.tim12.bll.service.PosjetaManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.LoginVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NovaPosjetaVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviObavljeniZahvatVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviPacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PrikazPacijentaVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TerminVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;
import ba.unsa.etf.si.tim12.ui.components.Validator;

public class Posjete {

	private JDialog dlgPosjeteRegistracija;
	private JTextField textFieldUkCijena;
	private JTextField textFieldDoktor;
	private JTextField textFieldVrijeme;
	private JTable table;
	private JComboBox comboBoxPacijent;
	private JTextPane textPane;
	private DateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	private ArrayList<NoviObavljeniZahvatVM> obavljeniZahvati;
		
	/**
	 * Create the application.
	 */
	public Posjete() {
		initialize();
		ResetujPolja();
		
		dlgPosjeteRegistracija.setVisible(true);
	}
	public Posjete(TerminVM termin) {
		initialize();
		ResetujPolja();
		
		textFieldDoktor.setText(termin.getDoktor());
		textFieldVrijeme.setText(formater.format(termin.getVrijeme()));
		
		PostaviUsera(termin.getPacijentId());
		
		dlgPosjeteRegistracija.setVisible(true);
	}

	private void PostaviUsera(long pacijentId) {
		Session sess = null;
		try{
			
			sess = HibernateUtil.getSessionFactory().openSession();
			
			PacijentManager pm = new PacijentManager(sess);
			PrikazPacijentaVM ppvm = pm.dajPacijenta(pacijentId);
			
			PacijentVM vm = new PacijentVM();
			vm.setId(ppvm.getId());
			vm.setImePrezime(ppvm.getImeIPrezime());
			vm.setBrojTelefona(ppvm.getBrojTelefona());
			
			Object[] array = new Object[]{vm};
			DefaultComboBoxModel model = new DefaultComboBoxModel(array); //(DefaultComboBoxModel) comboBoxPacijent.getModel();
			comboBoxPacijent.setModel(model);
			
			comboBoxPacijent.getUI().setPopupVisible(comboBoxPacijent, true);
			
		} catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(dlgPosjeteRegistracija, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
		}finally{
			if(sess != null)
				sess.close();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dlgPosjeteRegistracija = new JDialog();
		MainForma.Prekini(dlgPosjeteRegistracija);
		dlgPosjeteRegistracija.setModalityType(ModalityType.APPLICATION_MODAL);
		dlgPosjeteRegistracija.setTitle("Posjete - registracija");
		dlgPosjeteRegistracija.setBounds(100, 100, 829, 366);
		dlgPosjeteRegistracija.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgPosjeteRegistracija.getContentPane().setLayout(null);
		dlgPosjeteRegistracija.setLocationRelativeTo(null);
		JLabel lblPacijent = new JLabel("Pacijent:");
		lblPacijent.setBounds(31, 61, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblPacijent);
		
		comboBoxPacijent = new JComboBox();
		comboBoxPacijent.setEditable(true);
		lblPacijent.setLabelFor(comboBoxPacijent);
		comboBoxPacijent.setBounds(138, 58, 231, 22);
		JTextComponent jtc = (JTextComponent) comboBoxPacijent.getEditor().getEditorComponent();
		jtc.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						OsvjeziComboBox(true);
					}
				});
			}
			
			public void insertUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					public void run() {
						OsvjeziComboBox(true);
					}
				});
				
			}
			
			public void changedUpdate(DocumentEvent e) {
				//This never happens
			}
		});
		dlgPosjeteRegistracija.getContentPane().add(comboBoxPacijent);
		
		JLabel lblDijagnoza = new JLabel("Dijagnoza:");
		lblDijagnoza.setBounds(31, 165, 97, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblDijagnoza);
		
		JSeparator separator_pacijent = new JSeparator();
		separator_pacijent.setBounds(31, 46, 338, 2);
		dlgPosjeteRegistracija.getContentPane().add(separator_pacijent);
		
		JLabel lblInformacijeOPacijentu = new JLabel("Informacije o pacijentu");
		lblInformacijeOPacijentu.setBounds(31, 13, 137, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblInformacijeOPacijentu);
		
		JLabel lblInformacijeOZahvatu = new JLabel("Informacije o ura\u0111enim zahvatima");
		lblInformacijeOZahvatu.setBounds(420, 13, 208, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblInformacijeOZahvatu);
		
		JSeparator separator_zahvat = new JSeparator();
		separator_zahvat.setBounds(420, 46, 338, 2);
		dlgPosjeteRegistracija.getContentPane().add(separator_zahvat);
		
		JButton btnDodajPosjetu = new JButton("Registruj posjetu");
		btnDodajPosjetu.setBounds(527, 279, 231, 25);
		btnDodajPosjetu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodajPosjetu();	 
			}
		});
		dlgPosjeteRegistracija.getContentPane().add(btnDodajPosjetu);
		
		JLabel lblUkupnaCijena = new JLabel("Ukupna cijena:");
		lblUkupnaCijena.setBounds(422, 231, 95, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblUkupnaCijena);
		
		textFieldUkCijena = new JTextField();
		textFieldUkCijena.setEditable(false);
		textFieldUkCijena.setEnabled(false);
		lblUkupnaCijena.setLabelFor(textFieldUkCijena);
		textFieldUkCijena.setColumns(10);
		textFieldUkCijena.setBounds(527, 225, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textFieldUkCijena);
		
		JLabel lblDoktor = new JLabel("Doktor:");
		lblDoktor.setBounds(31, 93, 56, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblDoktor);
		
		textFieldDoktor = new JTextField();
		textFieldDoktor.setColumns(10);
		textFieldDoktor.setBounds(138, 91, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textFieldDoktor);
		
		JLabel lblVrijemePosjete = new JLabel("Vrijeme posjete:");
		lblVrijemePosjete.setBounds(31, 128, 107, 16);
		dlgPosjeteRegistracija.getContentPane().add(lblVrijemePosjete);
		
		textFieldVrijeme = new JTextField();
		textFieldVrijeme.setColumns(10);
		textFieldVrijeme.setBounds(138, 125, 231, 22);
		dlgPosjeteRegistracija.getContentPane().add(textFieldVrijeme);
		
		textPane = new JTextPane();
		textPane.setBounds(138, 161, 231, 143);
		dlgPosjeteRegistracija.getContentPane().add(textPane);
		
		JButton btnDodajZahvat_1 = new JButton("Dodaj zahvat");
		btnDodajZahvat_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NoviObavljeniZahvatVM noviZahvat = new NoviObavljeniZahvatVM();
				new DodavanjeZahvataGUI(noviZahvat);
				//JOptionPane.showMessageDialog(null, "" + noviZahvat.getZahvatId() + " -> " + noviZahvat.getMaterijali().size());
				if(noviZahvat.getZahvatId() == 0)
					return;
				
				else {
					obavljeniZahvati.add(noviZahvat);
					OsvjeziTable();
					OsvjeziUkupnuCijenu();
				}
				
			}

			
		});
		btnDodajZahvat_1.setBounds(651, 191, 107, 23);
		dlgPosjeteRegistracija.getContentPane().add(btnDodajZahvat_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(430, 61, 328, 118);
		dlgPosjeteRegistracija.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new UneditableTableModel(
			new Object[][] {
			},
			new String[] {
				"Zahvat", "Cijena"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(202);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		dlgPosjeteRegistracija.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{comboBoxPacijent, textFieldUkCijena, btnDodajPosjetu, lblInformacijeOPacijentu, separator_pacijent, lblPacijent, lblDijagnoza, lblInformacijeOZahvatu, separator_zahvat, lblUkupnaCijena, dlgPosjeteRegistracija.getContentPane()}));
	}
	
	private void OsvjeziComboBox(boolean expand) {
		Session sess = null;
		
		try {
			if ( comboBoxPacijent.getEditor().getItem() instanceof String){
				
				sess = HibernateUtil.getSessionFactory().openSession();
				PacijentManager pManager = new PacijentManager(sess);
				
				String ime = (String) comboBoxPacijent.getEditor().getItem();
				

				ArrayList<PacijentVM> data = pManager.nadjiPoImenu(ime);
				Object[] array = new Object[data.size() + 1];
				array[0] = ime;
				
				for(int i = 0; i < data.size(); i++){
					array[i+1] = data.get(i);
				}
				
				DefaultComboBoxModel model = new DefaultComboBoxModel(array); //(DefaultComboBoxModel) comboBoxPacijent.getModel();
				comboBoxPacijent.setModel(model);
				
				if(expand)
					comboBoxPacijent.getUI().setPopupVisible(comboBoxPacijent, true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dlgPosjeteRegistracija, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
		
			//logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				sess.close();
		}
		
	}
	
	private void ResetujPolja(){
		OsvjeziComboBox(false); 
		textFieldDoktor.setText("");
		textFieldVrijeme.setText("dd-mm-gggg hh:mm");
		PrikaziUkupnuCijenu(0.0);
		textPane.setText("");
		obavljeniZahvati = new ArrayList<NoviObavljeniZahvatVM>();
	}
	
	private void PrikaziUkupnuCijenu(double cijena){
		textFieldUkCijena.setText(Double.toString(Math.round(100*cijena)/100));
	}
	
	private void DodajPosjetu() {
		Session sess = null;
		try {
			
			if(!(comboBoxPacijent.getSelectedItem() instanceof PacijentVM)){
				JOptionPane.showMessageDialog(dlgPosjeteRegistracija, "Niste odabrali pacijenta!", 
						"Greška!", JOptionPane.ERROR_MESSAGE);
				
				return;
			}
			Date d = null;
			try {
			d = Validator.ValidAndParse ("d-M-yyyy HH:mm", textFieldVrijeme.getText());
			}
			catch (ParseException ee)
			{
				JOptionPane.showMessageDialog(dlgPosjeteRegistracija, ee.getMessage(), 
						"Greška!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (d.after(new Date()))
			{
				JOptionPane.showMessageDialog(dlgPosjeteRegistracija, "Ne možete registrirati posjetu u budućnosti!?", 
						"Greška!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			NovaPosjetaVM posjeta = new NovaPosjetaVM();
			
			posjeta.setPacijentId(((PacijentVM) comboBoxPacijent.getSelectedItem()).getId());
			posjeta.setDoktor(textFieldDoktor.getText());
			
			if(posjeta.getDoktor().isEmpty())
				throw new Exception("Morate unijeti ime doktora.");
			
			posjeta.setVrijeme(formater.parse(textFieldVrijeme.getText()));
			posjeta.setDijagnoza(textPane.getText());
			
			posjeta.setObavljeniZahvati(obavljeniZahvati);
			
			sess = HibernateUtil.getSessionFactory().openSession();
			PosjetaManager pManager = new PosjetaManager(sess);
			
			pManager.dodajNovuPosjetu(posjeta);
			ResetujPolja();
			OsvjeziTable();
			JOptionPane.showMessageDialog(dlgPosjeteRegistracija, "Uspješno dodana posjeta", 
					"Obavještenje", JOptionPane.INFORMATION_MESSAGE);
			
		}  catch (ParseException e1) {
			JOptionPane.showMessageDialog(dlgPosjeteRegistracija,
					"Datum unesite u formatu: dd-mm-gggg hh:mm. (dani-mjeseci-godine sati:minute)", "Greška!",
					JOptionPane.ERROR_MESSAGE);
			
			//logger.debug("Pogrešno unesen datum", e1);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(dlgPosjeteRegistracija,
					e2.getMessage(), "Greška!",
					JOptionPane.ERROR_MESSAGE);
			//logger.debug("Greška", e2);
		} finally {
			if (sess != null && sess.isOpen())
				sess.close();
		}
	}
	
	void OsvjeziTable(){
		
		Object[][] data = new Object[obavljeniZahvati.size()][];
		for(int i = 0; i < obavljeniZahvati.size(); i++){
			data[i] = new Object[]{obavljeniZahvati.get(i).getZahvatime(), obavljeniZahvati.get(i).getCijena()};
		}
		
		table.setModel(new UneditableTableModel(
				data, new String[] {"Zahvat", "Cijena"}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(202);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
	
	}
	
	void OsvjeziUkupnuCijenu(){
		double suma = 0;
		for (NoviObavljeniZahvatVM item : obavljeniZahvati) {
			suma += item.getCijena();
		}
		
		if(obavljeniZahvati.size() == 0)
			PrikaziUkupnuCijenu(0.0);
		else 
			PrikaziUkupnuCijenu(suma);
		
	}
}
