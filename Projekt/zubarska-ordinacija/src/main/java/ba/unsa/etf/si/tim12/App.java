package ba.unsa.etf.si.tim12;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import ba.unsa.etf.si.tim12.ui.MainForma;
/**
 * Hello world!
 *
 */


public class App 
{
	/**
	 * Launch the application.
	 */
	final static Logger logger = Logger.getLogger(App.class);
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForma window = new MainForma();
					
				} catch (Exception e) {
					logger.debug("Došlo je do greške.", e);
				}
			}
		});
	}

}
