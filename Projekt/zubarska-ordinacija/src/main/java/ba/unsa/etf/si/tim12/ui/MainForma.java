package ba.unsa.etf.si.tim12.ui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JTabbedPane;
import javax.swing.JButton;

import ba.unsa.etf.si.tim12.App;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import net.miginfocom.swing.MigLayout;

import java.awt.Canvas;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class MainForma {

	private JFrame frame;
	final static Logger logger = Logger.getLogger(MainForma.class);

	/**
	 * Create the application.
	 * 
	 */
	public MainForma() {
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
		// ImageIcon ihome = new ImageIcon
		// (getClass().getResource("/src/main/resources/home_16px.png"));
		ImageIcon ihome = new ImageIcon("src/main/resources/home_16px.png");

		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		TP.addTab("Home", ihome, p1, "Po�etna stranica");
		p1.setLayout(new MigLayout("", "[67px][119px][][][][][][][][][][][]",
				"[23px][][][][][][][]"));

		JButton btnLogout = new JButton("LogOut");
		p1.add(btnLogout, "cell 0 0,growx,aligny center");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new loginGUI();
			}
		});

		JButton btnNewButton = new JButton("Izmijeni password");
		p1.add(btnNewButton, "cell 0 1,alignx left");

		BackgroundPanel bp = new BackgroundPanel();
		JLabel jl = new JLabel();
		jl.setIcon(new ImageIcon("src/main/resources/zubic.png"));
		p1.add(jl, "cell 2 0 20 15, grow");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PasswordMgr();
			}
		});

		JPanel p2 = new JPanel();
		TP.addTab("Termini", null, p2, "Pregled narudžbi za pacijente");
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
		TP.addTab("Pacijenti", null, p3,
				"Tab za manipulaciju podacima o pacijentima");
		p3.setLayout(new MigLayout("", "[123px][][]", "[23px][][]"));

		JButton btnPacijenti = new JButton("Prikaz pacijenata");
		btnPacijenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PacijentiGUI();
			}
		});
		p3.add(btnPacijenti, "cell 0 0");

		JButton btnNewButton_2 = new JButton("Kreiraj novog pacijenta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new KreiranjePacijentaGUI();
			}
		});
		p3.add(btnNewButton_2, "cell 1 0");
		JPanel p4 = new JPanel();
		TP.addTab("Posjete", null, p4, "Evidencija posjeta za pacijente");
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
		TP.addTab("Zahvati", null, p5, "Evidencija zahvata");
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
		TP.addTab("Materijali", null, p6, "Materijali");
		p6.setLayout(new MigLayout("", "[][][][]", "[]"));

		JButton btnDodavanjeMaterijal = new JButton("Dodavanje materijala");
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
		TP.addTab("Izvje\u0161taji", null, p7, "Pregled izvje�taja");

		JButton btnOPosjetama = new JButton("O posjetama i zahvatima");
		btnOPosjetama.setBounds(10, 7, 338, 23);
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

		JButton btnODnevnimPosjetama = new JButton(
				"O dnevnim/sedmi\u010Dnim/mjese\u010Dnim posjetama");
		btnODnevnimPosjetama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IzvjestajPosjeteDSM();
			}
		});
		btnODnevnimPosjetama.setBounds(10, 143, 338, 23);
		p7.add(btnODnevnimPosjetama);
		frame.setVisible(true);
	}

	class BackgroundPanel extends JPanel {
		BufferedImage img;

		public BackgroundPanel() {
			try {
				// img = ImageIO.read(new File
				// (getClass().getResource("/zubic.jpg").toString()));
				img = ImageIO.read(new File("src/main/resources/zubic.png"));
			} catch (IOException e) {
				logger.debug(e.getMessage(), e);
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(img, 0, 0, 3000, 4000, this);
		}
	}

	public static void Prekini(final JFrame dialog) {
		ActionListener escListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		};

		dialog.getRootPane().registerKeyboardAction(escListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public static void Prekini(final JDialog dialog) {
		ActionListener escListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		};

		dialog.getRootPane().registerKeyboardAction(escListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

}
