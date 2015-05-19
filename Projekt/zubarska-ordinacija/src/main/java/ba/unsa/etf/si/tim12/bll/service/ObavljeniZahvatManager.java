package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.MaterijalTipZahvata;
import ba.unsa.etf.si.tim12.dal.domainmodel.TipZahvata;
import ba.unsa.etf.si.tim12.dal.domainmodel.ObavljeniZahvat;

import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.*;

public class ObavljeniZahvatManager {
	Session session;
	
	public ObavljeniZahvatManager(Session session)
	{
		this.session = session;
	}
	
	public  boolean dodajNoviZahvat(NoviObavljeniZahvatVM zahvat) {
     Transaction t = session.beginTransaction();
		
		ObavljeniZahvat z = new ObavljeniZahvat();
		z.setCijena(zahvat.getCijena());
		z.setId(zahvat.getZahvatId());
		z.setPosjetaId(zahvat.getPosjetaId());

		session.save(z);
		
		t.commit();
		
		return true;
		
	}
	
	public ArrayList<TipZahvataVM> nadjiSvePoTipuZahvata(long idTipa) {
		Transaction t = session.beginTransaction();
		
		//ne vidim u bazi kolonu idTipa u tabeli TipZahvata
		String hql ="from ObavljeniZahvat where idTipa=:idTipa";
		Query q = session.createQuery(hql);
		q.setLong("tipZahvataId", idTipa); 
	  
	    List<TipZahvataVM> lista = q.list();
	    
	    ArrayList<TipZahvataVM> lista1 = new ArrayList<TipZahvataVM>(lista.size());
	    lista1.addAll(lista);
	    
	    t.commit();
		return lista1;
		
	}

}
