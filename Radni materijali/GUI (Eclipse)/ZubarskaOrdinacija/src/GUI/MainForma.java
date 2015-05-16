package GUI;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JTabbedPane;
import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class MainForma {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForma window = new MainForma();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 *  
	 */
	public MainForma(){
		initialize();
		new loginGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Zubarska ordinacija (by LunaSoft)");
		frame.setBounds(100, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 1, 10, 10));
		frame.setLocationRelativeTo(null);
		
		JTabbedPane TP = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(TP);
		ImageIcon ihome = new ImageIcon (getClass().getResource("/home_16px.png"));
		
		JPanel p1 = new JPanel();
		TP.addTab ("Home", ihome, p1, "Poèetna stranica");
		p1.setLayout(new MigLayout("", "[67px][119px][][][][][][][][][][][]", "[23px][][][][][][][]"));
		
				JButton btnLogout = new JButton("LogOut");
				p1.add(btnLogout, "cell 12 6,alignx right,aligny center");
				btnLogout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						new loginGUI();
					}
				});
		
		JButton btnNewButton = new JButton("Izmijeni password");
		p1.add(btnNewButton, "cell 12 7,alignx left");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PasswordMgr();
			}
		});
		
		JPanel p2 = new JPanel();
		TP.addTab ("Termini", null, p2, "Pregled narudžbi za pacijente");
		p2.setLayout(new MigLayout("", "[][][]", "[]"));
		
		JButton btnRezervacijaTermina = new JButton("Rezervacija termina");
		btnRezervacijaTermina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RezervacijaTerminaGUI();
			}
		});
		p2.add(btnRezervacijaTermina, "cell 0 0");
		
		JButton btnPregledTermina = new JButton("Pregled termina");
		btnPregledTermina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PrikazTerminaGUI();
			}
		});
		p2.add(btnPregledTermina, "cell 1 0");
		JPanel p3 = new JPanel();
		TP.addTab ("Pacijenti", null, p3, "Tab za manipulaciju podacima o pacijentima");
		p3.setLayout(new MigLayout("", "[123px][][]", "[23px][][]"));
		
		JButton btnPacijenti = new JButton("Pacijenti");
		btnPacijenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PacijentiGUI();
			}
		});
		p3.add(btnPacijenti, "cell 0 0");
		JPanel p4 = new JPanel();
		TP.addTab ("Posjete", null, p4, "Evidencija posjeta za pacijente");
		p4.setLayout(new MigLayout("", "[][]", "[]"));
		
		JButton btnPosjete = new JButton("Registracija posjeta");
		btnPosjete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Posjete();
			}
		});
		p4.add(btnPosjete, "cell 0 0");
		
		JButton btnPrikazPosjeta = new JButton("Prikaz posjeta");
		btnPrikazPosjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PosjeteGUI();
			}
		});
		p4.add(btnPrikazPosjeta, "cell 1 0");
		JPanel p5 = new JPanel();
		TP.addTab ("Zahvati", null, p5, "Evidencija zahvata");
		p5.setLayout(new MigLayout("", "[][]", "[]"));
		
		JButton btnDodavanjeZahvata = new JButton("Dodavanje zahvata");
		btnDodavanjeZahvata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new KreiranjeZahvataGUI();
			}
		});
		p5.add(btnDodavanjeZahvata, "cell 0 0");
		
		JButton btnPrikazZahvata = new JButton("Prikaz zahvata");
		btnPrikazZahvata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new PrikazZahvataGUI();
			}
		});
		p5.add(btnPrikazZahvata, "cell 1 0");
		JPanel p6 = new JPanel();
		TP.addTab ("Materijali", null, p6, "Materijali");
		p6.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		JButton btnDodavanjeMaterijal = new JButton("Dodavanje materijal");
		btnDodavanjeMaterijal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new KreiranjeMaterijalaGUI();
			}
		});
		p6.add(btnDodavanjeMaterijal, "cell 0 0");
		
		JButton btnPrikazMaterijala = new JButton("Prikaz materijala");
		btnPrikazMaterijala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrikazMaterijalaGUI();
			}
		});
		p6.add(btnPrikazMaterijala, "cell 1 0");
		JPanel p7 = new JPanel();
		TP.addTab ("Izvještaji", null, p7, "Pregled izvještaja");
		
		JButton btnOPosjetama = new JButton("O posjetama i zahvatima");
		btnOPosjetama.setBounds(10, 7, 343, 23);
		btnOPosjetama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IzvjestajPosjeteZahvati();
			}
		});
		p7.setLayout(null);
		p7.add(btnOPosjetama);
		
		JButton btnOSvimUlazima = new JButton("O svim ulazima");
		btnOSvimUlazima.setBounds(10, 41, 338, 23);
		btnOSvimUlazima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IzvjestajUlazi();
			}
		});
		p7.add(btnOSvimUlazima);
		
		JButton btnOPosjetamaPacijenta = new JButton("O posjetama pacijenta");
		btnOPosjetamaPacijenta.setBounds(10, 109, 338, 23);
		btnOPosjetamaPacijenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IzvjestajPacijent();
			}
		});
		
		JButton btnNewButton_1 = new JButton("O potro\u0161enim materijalima");
		btnNewButton_1.setBounds(10, 75, 338, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IzvjestajMaterijali();
			}
		});
		p7.add(btnNewButton_1);
		p7.add(btnOPosjetamaPacijenta);
		
		JButton btnODnevnimPosjetama = new JButton("O dnevnim/sedmi\u010Dnim/mjese\u010Dnim posjetama");
		btnODnevnimPosjetama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IzvjestajPosjeteDSM();
			}
		});
		btnODnevnimPosjetama.setBounds(10, 143, 338, 23);
		p7.add(btnODnevnimPosjetama);
		frame.setVisible(true);
	}

}
