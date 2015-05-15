package ba.unsa.etf.si.tim12.bll.service;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.Session;


public class KorisnikManager {
	Session session;
	
	public KorisnikManager(Session session) {}
	
	public  boolean provjeriPassword(LoginVM model) {
		return true;
	//TODO: THIS}
	}
	
	public  boolean promjeniPassword(PromjeniPasswordVM model) {
		return true;
		//TODO: THIS}
		
	}

}
