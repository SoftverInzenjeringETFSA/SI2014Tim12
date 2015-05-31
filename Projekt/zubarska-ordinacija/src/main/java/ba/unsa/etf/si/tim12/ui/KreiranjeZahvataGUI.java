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
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.hibernate.Session;
import org.hibernate.loader.JoinWalker;

import ba.unsa.etf.si.tim12.bll.service.MaterijaliManager;
import ba.unsa.etf.si.tim12.bll.service.TipZahvataManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviOZahvatMaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviTipZahvataMaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviTipZahvataVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataMaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.CijeneEditableTM;


public class KreiranjeZahvataGUI {

	private JDialog frmKreiranjeZahvata;
	private JTextField textNaziv;
	private JTextField textCijena;
	private JTextField textKolicina;
	private JTable tableMaterijali;
	private JComboBox comboBoxMaterijal;
	private ArrayList<TipZahvataMaterijalVM> dataMaterijali;
	private JTextPane textOpis;
	

	/**
	 * Create the application.
	 */
	public KreiranjeZahvataGUI() {
		initialize();
		Resetiraj();
		OsvjeziCBMaterijal(false);
		
		frmKreiranjeZahvata.setVisible(true);
	}
	
	private void Resetiraj() {
		textNaziv.setText("");
		textCijena.setText("/");
		textKolicina.setText("");
		textOpis.setText("");
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
		frmKreiranjeZahvata.setTitle("Kreiranje zahvata");
		frmKreiranjeZahvata.setBounds(100, 100, 358, 407);
		frmKreiranjeZahvata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeZahvata.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frmKreiranjeZahvata.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frmKreiranjeZahvata.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textNaziv = new JTextField();
		textNaziv.setBounds(86, 11, 248, 20);
		panel_1.add(textNaziv);
		textNaziv.setColumns(10);
		
		
		textCijena = new JTextField();
		textCijena.setBounds(89, 204, 86, 20);
		panel_1.add(textCijena);
		textCijena.setColumns(10);
		textCijena.setText("0.0");
		
		JLabel lblIme = new JLabel("Naziv:");
		lblIme.setBounds(10, 11, 86, 20);
		panel_1.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Cijena:");
		lblPrezime.setBounds(10, 204, 86, 20);
		panel_1.add(lblPrezime);
		
		JButton btnKreiraj = new JButton("Kreiraj");
		btnKreiraj.setBounds(225, 330, 109, 23);
		btnKreiraj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				KreirajZahvat();
			}
			

		});
		panel_1.add(btnKreiraj);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmKreiranjeZahvata.dispose();
			}
		});
		btnOdustani.setBounds(106, 330, 109, 23);
		panel_1.add(btnOdustani);
		
		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setBounds(10, 235, 86, 20);
		panel_1.add(lblOpis);
		
		textOpis = new JTextPane();
		textOpis.setBounds(89, 235, 245, 78);
		panel_1.add(textOpis);
		
		JLabel lblNewLabel = new JLabel("Materijali:");
		lblNewLabel.setBounds(10, 109, 65, 23);
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 112, 245, 78);
		
		tableMaterijali = new JTable();
		
		scrollPane.setViewportView(tableMaterijali);
		panel_1.add(scrollPane);
		
		JLabel lblBam = new JLabel("BAM");
		lblBam.setBounds(185, 204, 86, 20);
		panel_1.add(lblBam);
		
		JLabel lblNewLabel_1 = new JLabel("Materijal:");
		lblNewLabel_1.setBounds(10, 42, 76, 20);
		panel_1.add(lblNewLabel_1);
		
		comboBoxMaterijal = new JComboBox();
		comboBoxMaterijal.setBounds(86, 42, 112, 20);
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
					textKolicina.setText("");
					textKolicina.requestFocus();
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
		btnDodaj.setBounds(86, 75, 89, 23);
		btnDodaj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodajMaterijal();
			}

			
		});
		panel_1.add(btnDodaj);
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
	
	private void DodajMaterijal() {
		if(!(comboBoxMaterijal.getSelectedItem() instanceof MaterijalVM )){
			JOptionPane.showMessageDialog(frmKreiranjeZahvata, "Odaberite materijal", 
					"Greška!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			String t = textKolicina.getText();
			if (t.trim().equals(""))
			{
				JOptionPane.showMessageDialog(frmKreiranjeZahvata, "Nije popunjeno polje 'Količina'!", 
						"Greška!", JOptionPane.ERROR_MESSAGE);
				return;	
			}
			
			double kolicina = Double.parseDouble(t);
			if (kolicina < 0)
			{
				JOptionPane.showMessageDialog(frmKreiranjeZahvata, "Količina ne može biti negativna'!", 
						"Greška!", JOptionPane.ERROR_MESSAGE);
				return;	
			}				
		}
		catch (NumberFormatException ee)
		{
			JOptionPane.showMessageDialog(frmKreiranjeZahvata, "Za količinu nije odabran validan broj!", 
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
	
	private void KreirajZahvat() {
		NoviTipZahvataVM vm = new NoviTipZahvataVM();
		
		String naziv = textNaziv.getText();
		if(naziv.isEmpty()){
			JOptionPane.showMessageDialog(null, "Unesite naziv materijala",
					"Greška", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
			
		Session sess = null;
				
		try {
			sess = HibernateUtil.getSessionFactory().openSession();
			
			TipZahvataManager tzManager = new TipZahvataManager(sess);
			
			//Provjera da li već postoji s istim imenom
			ArrayList<TipZahvataVM> lista = tzManager.nadjiPoImenu(naziv);
			
			for (TipZahvataVM tipZahvataVM : lista) {
				if(tipZahvataVM.getNaziv().toLowerCase().equals(naziv.toLowerCase())){
					throw new Exception("Već postoji zahvat s ovim imenom");
				}
			}
			vm.setNaziv(naziv);
			
			vm.setCijena(Double.parseDouble(textCijena.getText()));
			if (Double.parseDouble(textCijena.getText())<0) {
				throw new Exception("Unijeli ste negativnu cijenu zahata.");
			}
			vm.setOpis(textOpis.getText());
			
			vm.setMaterijali(new ArrayList<NoviTipZahvataMaterijalVM>());
			for(int i = 0; i < dataMaterijali.size(); i++){
				NoviTipZahvataMaterijalVM novi = new NoviTipZahvataMaterijalVM();
				novi.setMaterijalId(dataMaterijali.get(i).getMaterijalId());
				
				try{
					double kolicina = Double.parseDouble((String)tableMaterijali.getModel().getValueAt(i, 1));
					if(kolicina < 0)
						throw new Exception("Kolicina materijala " + dataMaterijali.get(i).getMaterijalIme() +
								" je negativna");
					
					novi.setKolicina(kolicina);
					
				} catch (NumberFormatException e){
					throw new Exception("Unesite decimalan broj za količinu materijala " + dataMaterijali.get(i).getMaterijalIme());
				}
				
				vm.getMaterijali().add(novi);
			}
		
			
			
			if(tzManager.dodajNoviTip(vm)){
			
			JOptionPane.showMessageDialog(null, "Uspješno kreiran zahvat", 
					"Obavještenje", JOptionPane.INFORMATION_MESSAGE);
				Resetiraj();
			} else {
				JOptionPane.showMessageDialog(null, "Kreiranje zahvata nije uspjelo.", 
						"Greška!", JOptionPane.ERROR_MESSAGE);
			}
			

		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Unesite decimalan broj za cijenu", 
					"Greška", JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Greška", JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
		} finally{
			if(sess != null)
				sess.close();
		}
	}
	
}
