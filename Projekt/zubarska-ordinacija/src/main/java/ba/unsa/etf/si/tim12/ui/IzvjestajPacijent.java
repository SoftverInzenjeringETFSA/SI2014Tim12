package ba.unsa.etf.si.tim12.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.IzvjestajManager;
import ba.unsa.etf.si.tim12.bll.service.PacijentManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazRowVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaRowVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.ui.components.UneditableTableModel;

import java.awt.JobAttributes;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;


public class IzvjestajPacijent {

	private JDialog frame;
	private JTable table;
	private JLabel lblPacijent;
	private JComboBox comboBox;
	private static final Logger logger = Logger.getLogger(IzvjestajPacijent.class);

	/**
	 * Create the application.
	 */
	public IzvjestajPacijent() {
		initialize();
		OsvjeziComboBox(false);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Izvje\u0161taj o obavljenim posjetama pacijenta");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 364);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 67, 503, 208);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new UneditableTableModel(
			new Object[][] {
			},
			new String[] {
				"Opis zahvata", "Dijagnoza", "Doktor", "Vrijeme posjete"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JButton btnModifikacijaMaterijala = new JButton("Prikaži");
		btnModifikacijaMaterijala.setBounds(262, 299, 121, 23);
		btnModifikacijaMaterijala.addActionListener(new ActionListener() {
			//TODO LUKA
			public void actionPerformed(ActionEvent e) {
				PrikaziIzvjestaj();
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
		
		lblPacijent = new JLabel("Pacijent:");
		lblPacijent.setBounds(22, 26, 78, 28);
		frame.getContentPane().add(lblPacijent);
		
		comboBox = new JComboBox();
		comboBox.setBounds(112, 30, 150, 20);
		comboBox.setEditable(true);
		JTextComponent jtc = (JTextComponent) comboBox.getEditor().getEditorComponent();
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
		frame.getContentPane().add(comboBox);
	}
	
	
	void PrikaziIzvjestaj(){
		
		if(!(comboBox.getSelectedItem() instanceof PacijentVM)){
			JOptionPane.showMessageDialog(frame, "Odaberite pacijenta", "Greška", JOptionPane.ERROR_MESSAGE);
			return;
		}
		PacijentVM pacijent = (PacijentVM) comboBox.getSelectedItem();
		
		Session sesija = null;
		
		try {
			sesija = HibernateUtil.getSessionFactory().openSession();
			
			IzvjestajManager izvjestaji = new IzvjestajManager(sesija);
			PosjetePacijentaVM vm = izvjestaji.posjetePacijenta(pacijent.getId());
			ArrayList<PosjetePacijentaRowVM> redovi = vm.getPosjete();
			
			Object[][] data = new Object[redovi.size()][]
					;
			for(int i = 0; i < redovi.size(); i++){
				data[i] = new Object[]{ redovi.get(i).getOpiszahvata(), redovi.get(i).getDijagnoza(), 
						redovi.get(i).getDoktor(),redovi.get(i).getVrijeme()};
			}
			
			String[] columns = new String[]{"Opis zahvata", "Dijagnoza", "Doktor32", "Vrijeme posjete"};
			table.setModel(new UneditableTableModel(
				data,
				columns
			));
			
			JOptionPane.showMessageDialog(null, "ovdje");
			
			/*
			for(int i = 0; i < redovi.size(); i++) {
				table.getModel().setValueAt(redovi.get(i).getOpiszahvata(), i, 0);
				table.getModel().setValueAt(redovi.get(i).getDijagnoza(), i, 1);
				table.getModel().setValueAt(redovi.get(i).getDoktor(), i, 2);
				table.getModel().setValueAt(redovi.get(i).getVrijeme(), i, 3);
			}*/					
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(frame, e1.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
			logger.debug(e1.getMessage(), e1);
		} 
		finally {
			if (sesija != null)
				sesija.close();
		}
	}

	private void OsvjeziComboBox(boolean expand) {
		Session sess = null;
		
		try {
			if ( comboBox.getEditor().getItem() instanceof String){
				
				sess = HibernateUtil.getSessionFactory().openSession();
				PacijentManager pManager = new PacijentManager(sess);
				
				String ime = (String) comboBox.getEditor().getItem();
				

				ArrayList<PacijentVM> data = pManager.nadjiPoImenu(ime);
				Object[] array = new Object[data.size() + 1];
				array[0] = ime;
				
				for(int i = 0; i < data.size(); i++){
					array[i+1] = data.get(i);
				}
				
				DefaultComboBoxModel model = new DefaultComboBoxModel(array); //(DefaultComboBoxModel) comboBoxPacijent.getModel();
				comboBox.setModel(model);
				
				if(expand)
					comboBox.getUI().setPopupVisible(comboBox, true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage(), 
					"Greška!", JOptionPane.ERROR_MESSAGE);
		
			//logger.debug(e.getMessage(), e);
		} finally {
			if (sess != null)
				sess.close();
		}
		
	}

}
