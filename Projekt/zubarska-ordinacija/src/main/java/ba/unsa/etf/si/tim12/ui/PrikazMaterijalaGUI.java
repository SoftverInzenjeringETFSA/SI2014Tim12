package ba.unsa.etf.si.tim12.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.service.KorisnikManager;
import ba.unsa.etf.si.tim12.bll.service.MaterijaliManager;
import ba.unsa.etf.si.tim12.bll.viewmodel.LoginVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class PrikazMaterijalaGUI {

	private JDialog frame;
	private JTable table;
	private JTextField textField;
	private JButton btnNewButton;

	
	/**
	 * Create the application.
	 */
	public PrikazMaterijalaGUI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		MainForma.Prekini(frame);
		frame.setTitle("Prikaz materijala");
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setBounds(100, 100, 524, 364);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 483, 221);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Ime materijala", "Jedini\u010Dna cijena", "Mjerna jedinica"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(29);
		table.getColumnModel().getColumn(1).setPreferredWidth(91);
		table.getColumnModel().getColumn(1).setMinWidth(2);
		scrollPane.setViewportView(table);
		
		JLabel lblPretraivanjePo = new JLabel("Pretra\u017Eivanje po imenu materijala:");
		lblPretraivanjePo.setBounds(10, 21, 204, 19);
		frame.getContentPane().add(lblPretraivanjePo);
		
		textField = new JTextField();
		textField.setBounds(224, 20, 149, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Pretra\u017Ei");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session sess = null;
				
				try{
					sess = HibernateUtil.getSessionFactory().openSession();
					MaterijaliManager m= new MaterijaliManager(sess);
					ArrayList<MaterijalVM> nadjeniMaterijali=m.nadjiPoImenu(textField.getText());
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					for (MaterijalVM materijal :nadjeniMaterijali){ 
						 model.addRow(new Object[]{materijal.getId(),materijal.getNaziv() ,materijal.getCijena(),materijal.getMjernaJedinica()});
					}
					

					
				
				} catch(Exception e){
					e.printStackTrace();
				} finally{
					if(sess != null)
						sess.close();
				}
			}
		});
		//ENIL: mijenjam ikonu
		btnNewButton.setIcon(new ImageIcon("src/main/resources/SearchIcon.png"));
		btnNewButton.setBounds(394, 20, 99, 20);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnModifikacijaMaterijala = new JButton("Obri\u0161i");
		btnModifikacijaMaterijala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Session sess = null;
				
				try{
					sess = HibernateUtil.getSessionFactory().openSession();
					MaterijaliManager m= new MaterijaliManager(sess);
//obrisati
					//boolean izbrisano=m.izbrisiMaterijal(table.se);

					
				
				} catch(Exception e){
					e.printStackTrace();
				} finally{
					if(sess != null)
						sess.close();
				}
				
			}
		});
		btnModifikacijaMaterijala.setBounds(240, 299, 121, 23);
		frame.getContentPane().add(btnModifikacijaMaterijala);
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setBounds(372, 299, 121, 23);
		frame.getContentPane().add(btnOdustani);
	}
}
