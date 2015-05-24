package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBoxMenuItem;

import java.awt.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.MaterijaliManager;
import ba.unsa.etf.si.tim12.bll.service.PacijentManager;
import ba.unsa.etf.si.tim12.bll.service.TipZahvataManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviOZahvatMaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviObavljeniZahvatVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataMaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.CijeneEditableTM;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;


public class DodavanjeZahvataGUI {

	private JDialog frmKreiranjeZahvata;
	private JTextField textKolicina;
	private JTextField textCijena;
	private NoviObavljeniZahvatVM noviZahvat;
	private JComboBox comboBoxZahvat;
	private JTable tableMaterijali;
	private JComboBox comboBoxMaterijal;
	private ArrayList<TipZahvataMaterijalVM> dataMaterijali;
	
	/**
	 * Create the application.
	 * @param noviZahvat 
	 */
	public DodavanjeZahvataGUI(NoviObavljeniZahvatVM noviZahvat) {
		initialize();
		this.noviZahvat = noviZahvat;
		Resetiraj();
		OsvjeziCBZahvat(false);
		OsvjeziCBMaterijal(false);
		frmKreiranjeZahvata.setVisible(true);
	}

	private void Resetiraj() {
		textCijena.setText("/");
		textKolicina.setText("");
		dataMaterijali = new ArrayList<TipZahvataMaterijalVM>();
		tableMaterijali.setModel(new CijeneEditableTM(
			new Object[][]{}, 
			new String[] {"Materijal", "Količina"}
		));
		tableMaterijali.getColumnModel().getColumn(1).setPreferredWidth(15);
		tableMaterijali.getColumnModel().getColumn(1).setMinWidth(5);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeZahvata = new JDialog();
		MainForma.Prekini(frmKreiranjeZahvata);
		frmKreiranjeZahvata.setModalityType(ModalityType.APPLICATION_MODAL);
		frmKreiranjeZahvata.setResizable(false);
		frmKreiranjeZahvata.setTitle("Dodavanje zahvata");
		frmKreiranjeZahvata.setBounds(100, 100, 358, 407);
		frmKreiranjeZahvata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeZahvata.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmKreiranjeZahvata.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmKreiranjeZahvata.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblIme = new JLabel("Zahvat:");
		lblIme.setBounds(10, 11, 86, 20);
		panel_1.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Cijena:");
		lblPrezime.setBounds(189, 260, 86, 20);
		panel_1.add(lblPrezime);
		
		JButton btnKreiraj = new JButton("Dodaj Zahvat");
		btnKreiraj.setBounds(225, 330, 109, 23);
		btnKreiraj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				DodajZahvat();
			}

		});
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(106, 330, 109, 23);
		btnOdustani.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				frmKreiranjeZahvata.dispatchEvent(new WindowEvent(frmKreiranjeZahvata,
						WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_1.add(btnOdustani);
		
		JLabel lblNewLabel = new JLabel("Materijali:");
		lblNewLabel.setBounds(10, 109, 65, 23);
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 112, 245, 137);
		
		tableMaterijali = new JTable();
		
		scrollPane.setViewportView(tableMaterijali);
		panel_1.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Materijal:");
		lblNewLabel_1.setBounds(10, 42, 76, 20);
		panel_1.add(lblNewLabel_1);
		
		comboBoxMaterijal = new JComboBox();
		comboBoxMaterijal.setBounds(89, 42, 109, 20);
		comboBoxMaterijal.setEditable(true);
		JTextComponent jtc = (JTextComponent) comboBoxMaterijal.getEditor().getEditorComponent();
		jtc.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						OsvjeziCBMaterijal(true);
					}
				});
			}
			public void insertUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {			
					public void run() {
						OsvjeziCBMaterijal(true);
					}
				});
			}
			public void changedUpdate(DocumentEvent e) {/*This never happens*/}
		});
		comboBoxMaterijal.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(comboBoxMaterijal.getSelectedItem() instanceof MaterijalVM){
					MaterijalVM vm = (MaterijalVM) comboBoxMaterijal.getSelectedItem();
					textKolicina.setText(Double.toString(vm.getCijena()));
				}
			}
		});
		panel_1.add(comboBoxMaterijal);
		
		JLabel lblKoliina = new JLabel("Koli\u010Dina:");
		lblKoliina.setBounds(218, 42, 57, 20);
		panel_1.add(lblKoliina);
		
		textKolicina = new JTextField();
		textKolicina.setBounds(277, 42, 57, 20);
		panel_1.add(textKolicina);
		textKolicina.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(89, 78, 89, 23);
		btnDodaj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodajMaterijal();
			}

			
		});
		panel_1.add(btnDodaj);
		
		comboBoxZahvat = new JComboBox();
		comboBoxZahvat.setEditable(true);
		comboBoxZahvat.setBounds(87, 11, 247, 20);
		JTextComponent jtc2 = (JTextComponent) comboBoxZahvat.getEditor().getEditorComponent();
		jtc2.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						OsvjeziCBZahvat(true);
					}
				});
			}
			public void insertUpdate(DocumentEvent e) {
				SwingUtilities.invokeLater(new Runnable() {			
					public void run() {
						OsvjeziCBZahvat(true);
					}
				});
			}
			public void changedUpdate(DocumentEvent e) {/*This never happens*/}
		});
		comboBoxZahvat.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(comboBoxZahvat.getSelectedItem() instanceof TipZahvataVM){
					TipZahvataVM t = (TipZahvataVM) comboBoxZahvat.getSelectedItem();
					textCijena.setText(Double.toString(Math.round(t.getCijena()*100)/100));
					
					OsvjeziTabeluMaterijala(t.getId());
				}
			}

		});
		panel_1.add(comboBoxZahvat);
		
		textCijena = new JTextField();
		textCijena.setBounds(248, 260, 86, 20);
		panel_1.add(textCijena);
		textCijena.setColumns(10);
	}

	
	private void OsvjeziCBZahvat(boolean expand) {
		Session sess = null;
		
		try {
			if ( comboBoxZahvat.getEditor().getItem() instanceof String){
				
				sess = HibernateUtil.getSessionFactory().openSession();
				TipZahvataManager tzmManager = new TipZahvataManager(sess);
				
				String ime = (String) comboBoxZahvat.getEditor().getItem();
				
				ArrayList<TipZahvataVM> data = tzmManager.nadjiPoImenu(ime);
				Object[] array = new Object[data.size() + 1];
				array[0] = ime;
				
				for(int i = 0; i < data.size(); i++){
					array[i+1] = data.get(i);
				}
				
				DefaultComboBoxModel model = new DefaultComboBoxModel(array); //(DefaultComboBoxModel) comboBoxPacijent.getModel();
				comboBoxZahvat.setModel(model);
				
				if(expand)
					comboBoxZahvat.getUI().setPopupVisible(comboBoxZahvat, true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmKreiranjeZahvata, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
		
			//logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				sess.close();
		}
	}

	private void OsvjeziCBMaterijal(boolean expand) {
		Session sess = null;
		
		try {
			if (comboBoxMaterijal.getEditor().getItem() instanceof String){
				
				sess = HibernateUtil.getSessionFactory().openSession();
				MaterijaliManager mManager = new MaterijaliManager(sess);
				
				String ime = (String) comboBoxMaterijal.getEditor().getItem();
				
				ArrayList<MaterijalVM> data = mManager.nadjiPoImenu(ime);
				Object[] array = new Object[data.size() + 1];
				array[0] = ime;
				
				for(int i = 0; i < data.size(); i++){
					array[i+1] = data.get(i);
				}
				
				DefaultComboBoxModel model = new DefaultComboBoxModel(array); //(DefaultComboBoxModel) comboBoxPacijent.getModel();
				comboBoxMaterijal.setModel(model);
				
				if(expand)
					comboBoxMaterijal.getUI().setPopupVisible(comboBoxMaterijal, true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmKreiranjeZahvata, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
		
			//logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				sess.close();
		}
	}
	
	private void OsvjeziTabeluMaterijala(long tipZahvataId) {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			dataMaterijali = mManager.nadjiPoTipuZahvata(tipZahvataId);
			
		}catch(Exception  e){
			JOptionPane.showMessageDialog(frmKreiranjeZahvata, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
		}finally{
			if(sess != null)
				sess.close();
		}
		
		if(dataMaterijali == null)
			dataMaterijali = new ArrayList<TipZahvataMaterijalVM>();
		

		Object[][] data = new Object[dataMaterijali.size()][];
		for (int i = 0; i < dataMaterijali.size(); i++) {
			TipZahvataMaterijalVM vm = dataMaterijali.get(i);
			data[i] = new Object[]{vm.getMaterijalIme(), Double.toString(vm.getKolicina())};
		}

		String[] columns = new String[] { "Materijal", "Količina" };

		tableMaterijali.setModel(new CijeneEditableTM(data, columns));
		tableMaterijali.getColumnModel().getColumn(1).setPreferredWidth(15);
		tableMaterijali.getColumnModel().getColumn(1).setMinWidth(5);
	}
	
	private void DodajZahvat() {
		
		if( !(comboBoxZahvat.getSelectedItem() instanceof TipZahvataVM)){
			JOptionPane.showMessageDialog(null, "Odaberite tip zahvata", 
					"Greška!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		noviZahvat.setZahvatId(((TipZahvataVM) comboBoxZahvat.getSelectedItem()).getId());
		
		try{
		
			noviZahvat.setCijena(Double.parseDouble(textCijena.getText()));
			if(noviZahvat.getCijena() < 0)
				throw new NumberFormatException("Cijena je negativna.");
		} catch (NumberFormatException e){
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Za cijenu unesite nenegativan decimalni broj", 
					"Greška", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		noviZahvat.setMaterijali(new ArrayList<NoviOZahvatMaterijalVM>());
		for(int i = 0; i < dataMaterijali.size(); i++){
			NoviOZahvatMaterijalVM novi = new NoviOZahvatMaterijalVM();
			novi.setMaterijalId(dataMaterijali.get(i).getMaterijalId());
			
			try{
				double kolicina = Double.parseDouble((String)tableMaterijali.getModel().getValueAt(i, 1));
				if(kolicina < 0){
					String msg = "Kolicina materijala " + dataMaterijali.get(i).getMaterijalIme() +
							" je negativna";
					
					JOptionPane.showMessageDialog(null, msg, 
							"Greška", JOptionPane.ERROR_MESSAGE);
				}
				novi.setKolicina(kolicina);
				
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Unesite decimalan broj za kolicine", 
						"Greška", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			noviZahvat.getMaterijali().add(novi);
		}
		
		frmKreiranjeZahvata.dispatchEvent(new WindowEvent(frmKreiranjeZahvata,
				WindowEvent.WINDOW_CLOSING));

	}
	
	private void DodajMaterijal() {
		if(!(comboBoxMaterijal.getSelectedItem() instanceof MaterijalVM )){
			JOptionPane.showMessageDialog(frmKreiranjeZahvata, "Odaberite materijal", 
					"Greška!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		MaterijalVM vm = (MaterijalVM) comboBoxMaterijal.getSelectedItem();
		TipZahvataMaterijalVM m = new TipZahvataMaterijalVM();
		m.setMaterijalId(vm.getId());
		m.setMaterijalIme(vm.getNaziv());
		dataMaterijali.add(m);
		
		((CijeneEditableTM) tableMaterijali.getModel()).addRow(new Object[]{vm.getNaziv(), textKolicina.getText()});
		//TODO: ovdje oprez... mislim da samo ovo treba, ali oprez
	}
}
