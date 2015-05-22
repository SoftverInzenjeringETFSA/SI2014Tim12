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
		
	    //TODO: ovdje trebe Select new TerminVM(...) tako da neće ni biti potrebe
		//za konverzijom
		String hql ="from Termin where pacijentId = :idpacijenta";
		Query q = session.createQuery(hql);
		q.setLong("idpacijenta", idpacijenta); 
	  
	    List<TerminVM> lista = q.list();
	    
	    ArrayList<TerminVM> lista1 = new ArrayList<TerminVM>(lista);
	    
	    t.commit();
		return lista1;
	}
	
	public  ArrayList<TerminVM> nadjiPoVremenu(Date vrijemePoc, Date vrijemeKraj) {
		Transaction t = session.beginTransaction();
		
	    //nisam nasao u tabeli u bazi da ima i kraj termina
		//ovo poredjenje u sljedecoj liniji vjerovatno ne treba ovako, posto je datum
		String hql ="from Termin where vrijeme=:vrijemePoc";
		Query q = session.createQuery(hql);
		q.setDate("vrijeme", vrijemePoc); 
	  
	    List<TerminVM> lista = q.list();
	    
	    ArrayList<TerminVM> lista1 = new ArrayList<TerminVM>(lista.size());
	    lista1.addAll(lista);
	    
	    t.commit();
		return lista1;
	}
	

	public void otkaziTermin(long terminId)
	{			
		Termin t = (Termin)session.get(Termin.class, terminId);
		t.setOtkazano(false);
		session.update(t);		
	}

}
