package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.util.*;

@SuppressWarnings("unchecked")
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
	
	public ArrayList<ObavljeniZahvatVM> nadjiSvePoTipuZahvata(long idTipa) {
		Transaction t = session.beginTransaction();
		
		String hql ="select new ba.unsa.etf.si.tim12.bll.viewmodel.ObavljeniZahvatVM(z.id, z.zahvatId, z.posjetaId, z.cijena) " +
				"from ObavljeniZahvat z where z.zahvatId = :id";
		Query q = session.createQuery(hql);
		q.setLong("id", idTipa);	    
		ArrayList<ObavljeniZahvatVM> rez = new ArrayList<ObavljeniZahvatVM>(q.list());
	    t.commit();
	    return rez;
	}
}
