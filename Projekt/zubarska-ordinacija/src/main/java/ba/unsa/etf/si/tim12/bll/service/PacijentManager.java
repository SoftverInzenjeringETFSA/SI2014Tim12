package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.*;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class PacijentManager {
	Session session;
	
	public PacijentManager(Session session) {}
	
	public  boolean dodajNovogPacijenta(noviPacijentVM pacijent) {
		return true;
		//TODO: This
	}
	
	public  boolean modificirajPacijenta(PacijentVM pacijent) {
		return true;
		//TODO: This
	}
	
	public  PrikazPacijentaVM dajPacijenta(long id) {
		return new PrikazPacijentaVM();
		//TODO: This
	}
	
	public  ArrayList<PacijentVM> nadjiPoIdu(long pacijentId) {
		return new ArrayList<PacijentVM>();
		//TODO: This
	}
	
	public  ArrayList<PacijentVM> nadjiPoImenu(String pacijentIme) {
		return new ArrayList<PacijentVM>();
		//TODO: This
	}
	
	public  ArrayList<PacijentVM> nadjiPoOpisu(String opis) {
		return new ArrayList<PacijentVM>();
		//TODO: This
	}

}
