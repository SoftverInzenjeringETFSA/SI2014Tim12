package ba.unsa.etf.si.tim12.bll.service;
import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.Session;


public class PacijentManager {
	Session session;
	
	public PacijentManager(Session session) {}
	
	public  boolean dodajNovogPacijenta(noviPacijentVM pacijent) {}
	
	public  boolean modificirajPacijenta(PacijentVM pacijent) {}
	
	public  PrikazPacijentaVM dajPacijenta(lond id) {}
	
	public  set<PacijentVM> nadjiPoIdu(long pacijentId) {}
	
	public  set<PacijentVM> nadjiPoImenu(String pacijentIme) {}
	
	public  set<PacijentVM> nadjiPoOpisu(String opis) {}

}
