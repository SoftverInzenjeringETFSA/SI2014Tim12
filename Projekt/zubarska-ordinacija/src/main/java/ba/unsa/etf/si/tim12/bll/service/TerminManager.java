package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.PacijentNotFound;
import ba.unsa.etf.si.tim12.TerminNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;
import ba.unsa.etf.si.tim12.dal.domainmodel.Termin;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class TerminManager {
	Session session;
	
	public TerminManager(Session session) 
	{
		this.session = session;
	}
	
	public long dodajNoviTermin(NoviTermin t) throws Exception {
		Transaction trans = session.beginTransaction();
		
		
		Pacijent pac = (Pacijent) session.get(Pacijent.class, t.getPacijentId());
		if(pac == null)
			throw new PacijentNotFound("Pacijent sa unesenim id-em ne postoji!");
		
		Termin termin = new Termin();
		termin.setDoktor(t.getDoktor());
		termin.setOtkazano(false);
		termin.setVrijeme(t.getVrijeme());
		termin.setPacijentId(t.getPacijentId());
		
		session.save(termin);
		
		trans.commit();
		
		return termin.getId();
	}
	
	public  ArrayList<TerminVM> nadjiPoPacijentu(long idpacijenta) {
		Transaction t = session.beginTransaction();
		
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.TerminVM(t.id, t.doktor, t.vrijeme, t.otkazano, t.pacijentId) "
				+ "FROM Termin t "
				+ "WHERE t.pacijentId = :idpacijenta";
		Query q = session.createQuery(hql);
		q.setLong("idpacijenta", idpacijenta); 
	  
	    List<TerminVM> lista = q.list();
	    
	    ArrayList<TerminVM> lista1 = new ArrayList<TerminVM>(lista);
	    
	    t.commit();
		return lista1;
	}
	
	public  ArrayList<TerminVM> nadjiPoVremenu(Date vrijemePoc, Date vrijemeKraj) {
		Transaction t = session.beginTransaction();
		
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.TerminVM(t.id, t.doktor, t.vrijeme, t.otkazano, t.pacijentId) "
				+ "from Termin t "
				+ "where t.vrijeme BETWEEN :date1 AND :date2";
		Query q = session.createQuery(hql);
		q.setDate("date1", vrijemePoc);
		q.setDate("date2", vrijemeKraj);
	  
	    ArrayList<TerminVM> lista1 = new ArrayList<TerminVM>(q.list());
	    
	    t.commit();
		return lista1;
	}
	

	public boolean otkaziTermin(long terminId) throws TerminNotFound
	{	
		Transaction t = session.beginTransaction();
		
		Termin ter = (Termin)session.get(Termin.class, terminId);
		if(ter == null)
			throw new TerminNotFound("Termin sa unesenim id-em ne postoji u bazi podataka!");
		ter.setOtkazano(true);
		session.update(ter);
		
		t.commit();
		
		return true;
	}
	
	public ArrayList<TerminVM> dajSveTermine(){
		String hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.TerminVM(t.id, t.doktor, "+
				"t.vrijeme, t.otkazano, t.pacijentId) FROM Termin t ";
		Query query = session.createQuery(hql);
		return new ArrayList<TerminVM>(query.list());
	}

}
