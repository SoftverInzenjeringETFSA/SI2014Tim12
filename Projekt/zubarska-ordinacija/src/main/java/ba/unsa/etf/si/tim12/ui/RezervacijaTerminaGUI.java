package ba.unsa.etf.si.tim12.ui;


import javax.swing.JFrame;

import org.hibernate.Session;
import org.jdesktop.swingx.JXMonthView;

import ba.unsa.etf.si.tim12.bll.service.PacijentManager;
import ba.unsa.etf.si.tim12.bll.service.TerminManager;
//import ba.unsa.etf.si.tim12.bll.service.TerminManagerTest;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviTermin;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.Validator;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class RezervacijaTerminaGUI {

	private JDialog dlgR;
	private JTextField textDoktor;
	private JTextField textVrijeme;
	private JComboBox comboBoxPacijent;
	private JXMonthView monthView;
	private DateFormat formater = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	
	/**
	 * Create the application.
	 */
	public RezervacijaTerminaGUI() {
		initialize();
		Resetiraj();
		OsvjeziComboBox(false);
		dlgR.setVisible(true);
	}

	private void Resetiraj() {
		textVrijeme.setText("hh:mm");
		textDoktor.setText("");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dlgR = new JDialog();
		MainForma.Prekini(dlgR);
		dlgR.setResizable(false);
		dlgR.setModalityType(ModalityType.APPLICATION_MODAL);
		dlgR.setTitle("Zakazivanje termina");
		dlgR.setBounds(100, 100, 426, 479);
		dlgR.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgR.getContentPane().setLayout(null);
		dlgR.setLocationRelativeTo(null);
		monthView = new JXMonthView();
		monthView.setBounds(124, 155, 245, 176);
		monthView.setTraversable(true);
		
		dlgR.getContentPane().add(monthView);
		
		
		JButton btnNoviTermin = new JButton("Registruj novi termin");
		btnNoviTermin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrujTermin();
			}
		});
		btnNoviTermin.setBounds(208, 394, 161, 25);
		dlgR.getContentPane().add(btnNoviTermin);
		
		JLabel lblPacijent = new JLabel("Pacijent:");
		lblPacijent.setBounds(29, 23, 69, 14);
		dlgR.getContentPane().add(lblPacijent);
		
		JLabel lblDoktor = new JLabel("Doktor:");
		lblDoktor.setBounds(29, 65, 63, 14);
		dlgR.getContentPane().add(lblDoktor);
		
		textDoktor = new JTextField();
		textDoktor.setBounds(124, 60, 245, 25);
		dlgR.getContentPane().add(textDoktor);
		textDoktor.setColumns(10);
		textDoktor.setText("/");
		
		comboBoxPacijent = new JComboBox();
		comboBoxPacijent.setEditable(true);
		comboBoxPacijent.setBounds(124, 18, 245, 25);
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
		dlgR.getContentPane().add(comboBoxPacijent);
		
		JLabel lblVrijeme = new JLabel("Vrijeme:");
		lblVrijeme.setBounds(29, 108, 69, 14);
		dlgR.getContentPane().add(lblVrijeme);
		
		textVrijeme = new JTextField();
		textVrijeme.setColumns(10);
		textVrijeme.setBounds(124, 97, 245, 25);
		textVrijeme.setText("1");
		dlgR.getContentPane().add(textVrijeme);
		
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(29, 155, 69, 14);
		dlgR.getContentPane().add(lblDatum);
	}
	
	private void RegistrujTermin() {
		Session sess = null;
		
		if(!(comboBoxPacijent.getSelectedItem() instanceof PacijentVM)){
			JOptionPane.showMessageDialog(dlgR, "Odaberite pacijenta", 
					"Greška!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(monthView.getSelectionDate() == null){
			JOptionPane.showMessageDialog(dlgR, "Odaberite datum", 
					"Greška!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try{
			DateFormat form2 = new SimpleDateFormat("d-M-yyyy");
			Date vrijeme = null;
			try {
			vrijeme = Validator.ValidAndParse("d-M-yyyy HH:mm", form2.format(monthView.getSelectionDate()) + " " + textVrijeme.getText());
			}
			catch (ParseException ee)
			{
				JOptionPane.showMessageDialog(dlgR, ee.getMessage(), 
						"Greška!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			PacijentVM pacijent = (PacijentVM) comboBoxPacijent.getSelectedItem();
			
			
			NoviTermin vm = new NoviTermin();
			vm.setPacijentId(pacijent.getId());
			vm.setDoktor(textDoktor.getText());
			vm.setVrijeme(vrijeme);
			
			sess = HibernateUtil.getSessionFactory().openSession();
			
			TerminManager tManager = new TerminManager(sess);
			tManager.dodajNoviTermin(vm);
			
			JOptionPane.showMessageDialog(dlgR, "Uspješno registriran termin", 
					"Obavještenje!", JOptionPane.INFORMATION_MESSAGE);
			Resetiraj();
		}catch(ParseException e){
			
			JOptionPane.showMessageDialog(dlgR, "Unesite vrijeme u formatu hh:mm", 
					"Greška!", JOptionPane.ERROR_MESSAGE);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(dlgR, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
			
		}finally{
			if(sess != null)
				sess.close();
		}

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
			JOptionPane.showMessageDialog(dlgR, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
		
			//logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				sess.close();
		}
		
	}
}
