package ba.unsa.etf.si.tim12.bll.service;

public class IzvjestajManager {
	Session session;
	
	public IzvjestajManager(Session session) {}
	
	public  ZahvatiPoDoktoruVM sviZahvatiPoDoktoru(String imeDoktora) {}
	
	public  FinancijskiUlazVM financijskiUlaz(Date vrijemeOd, Date vrijemeDo) {}
	
	public  PotMaterijaliVM potrosniMaterijali(Date vrijemeOd, Date vrijemeDo) {}
	
	public  PosjetePacijentaVM posjetePacijenta(long idPacijenta) {}
	
	public  OdradjenePosjeteVM odradjenePosjetePoDanu(Date vrijeme) {}
	
	
}
