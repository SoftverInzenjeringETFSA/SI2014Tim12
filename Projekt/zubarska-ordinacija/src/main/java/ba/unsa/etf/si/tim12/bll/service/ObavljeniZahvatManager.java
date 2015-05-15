package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.Scanner;

import org.hibernate.Transaction;
import org.hibernate.Session;
import java.util.*;

public class ObavljeniZahvatManager {
	Session session;
	
	public ObavljeniZahvatManager(Session session) {}
	
	public  boolean dodajNoviZahvat(NoviObavljeniZahvatVM zahvat) {
		return true;
		//TODO: THIS
		
	}
	
	public ArrayList<TipZahvataVM> nadjiSvePoTipuZahvata(long idTipa) {
		return new ArrayList<TipZahvataVM>();
		//TODO: THIS
		
	}

}
