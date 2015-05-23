package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
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
	
	public  ArrayList<TerminVM> nadjiPoPacijentu(long idpacijenta) {
		Transaction t = session.beginTransaction();
		
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.TerminVM(t.id, t.doktor, t.vrijeme, t.otkazano) "
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
		
		String hql ="Select new TerminVM(t.id, t.doktor, t.vrijeme, t.otkazano) "
				+ "from Termin t "
				+ "where t.vrijeme BETWEEN :date1 AND :date2;";
		Query q = session.createQuery(hql);
		q.setDate("date1", vrijemePoc);
		q.setDate("date2", vrijemeKraj);
	  
	    List<TerminVM> lista = q.list();
	    
	    ArrayList<TerminVM> lista1 = new ArrayList<TerminVM>(lista.size());
	    lista1.addAll(lista);
	    
	    t.commit();
		return lista1;
	}
	

	public boolean otkaziTermin(long terminId)
	{	
		Transaction t = session.beginTransaction();
		
		Termin ter = (Termin)session.get(Termin.class, terminId);
		ter.setOtkazano(true);
		session.update(ter);
		
		t.commit();
		
		return true;
	}

}
