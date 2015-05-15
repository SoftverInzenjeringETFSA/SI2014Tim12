package ba.unsa.etf.si.tim12.bll.service;
import java.util.*;

import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;

public class IzvjestajManager {
	Session session;
	
	public IzvjestajManager(Session session) {}
	
	public  ZahvatiPoDoktoruVM sviZahvatiPoDoktoru(String imeDoktora) {return new ZahvatiPoDoktoruVM();}
	
	public  FinancijskiUlazVM financijskiUlaz(Date vrijemeOd, Date vrijemeDo) {return new FinancijskiUlazVM();}
	
	public  PotMaterijaliVM potrosniMaterijali(Date vrijemeOd, Date vrijemeDo) {return new PotMaterijaliVM();}
	
	public  PosjetePacijentaVM posjetePacijenta(long idPacijenta) { return new PosjetePacijentaVM();}
	
	public  OdradjenePosjeteVM odradjenePosjetePoDanu(Date vrijeme) {return new ODradjenePosjeteVM();}
	
	
}
