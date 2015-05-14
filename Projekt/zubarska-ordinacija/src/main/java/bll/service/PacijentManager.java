package bll.service;

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
