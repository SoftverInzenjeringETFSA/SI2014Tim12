package ba.unsa.etf.si.tim12;

import java.awt.EventQueue;

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

}
