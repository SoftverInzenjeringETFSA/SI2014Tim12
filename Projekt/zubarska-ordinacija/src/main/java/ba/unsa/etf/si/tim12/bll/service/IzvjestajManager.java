package ba.unsa.etf.si.tim12.bll.service;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;

public class IzvjestajManager {
	Session session;
	
	public IzvjestajManager(Session session) {
		this.session = session;
	}
	
	public ZahvatiPoDoktoruVM sviZahvatiPoDoktoru(String imeDoktora) {
		Transaction t = session.beginTransaction();
		String hql = "";
		Query q = session.createQuery(hql);
		//q.setParameter("", );
		List<String> rez = (List<String>) q.list();
		return new ZahvatiPoDoktoruVM();
	}
	
	public  FinancijskiUlazVM financijskiUlaz(Date vrijemeOd, Date vrijemeDo) {return new FinancijskiUlazVM();}
	
	public  PotMaterijaliVM potroseniMaterijali(Date vrijemeOd, Date vrijemeDo) {return new PotMaterijaliVM();}
	
	public  PosjetePacijentaVM posjetePacijenta(long idPacijenta) { return new PosjetePacijentaVM();}
	
	public  OdradjenePosjeteVM odradjenePosjetePoDanu(Date vrijeme) {return new OdradjenePosjeteVM();}
	
	
}
