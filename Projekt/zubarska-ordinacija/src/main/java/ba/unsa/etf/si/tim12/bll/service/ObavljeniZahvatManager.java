package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.MaterijalNotFound;
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
class ObavljeniZahvatManager {
	Session session;
	
	public ObavljeniZahvatManager(Session session)
	{
		this.session = session;
	}
	
	public  boolean dodajNoviZahvat(NoviObavljeniZahvatVM zahvat) throws Exception { 
		
		String hql = "SELECT COUNT(DISTINCT z.id) from  TipZahvata z "
				+ "WHERE z.id = :id";
		Query query = session.createQuery(hql);
		query.setLong("id", zahvat.getZahvatId());
		if(query.equals(0))
			return false;
		
		ObavljeniZahvat z = new ObavljeniZahvat();
		z.setCijena(zahvat.getCijena());
		z.setZahvatId(zahvat.getZahvatId());
		z.setPosjetaId(zahvat.getPosjetaId());

		session.save(z);

		for(NoviOZahvatMaterijalVM m : zahvat.getMaterijali())
		{
			UtroseniMaterijal mat = new UtroseniMaterijal();
			hql = "SELECT COUNT(DISTINCT m.id) "+
					"FROM Materijal m WHERE m.id=:id";
			
			query = session.createQuery(hql);
			query.setLong("id", m.getMaterijalId());
			if(query.equals(0))
				throw new MaterijalNotFound("Materijal sa unesenim id-em ne postoji!");
			
			mat.setKolicina(m.getKolicina());
			mat.setMaterijalId(m.getMaterijalId());
			mat.setObavljeniZahvatId(z.getId());
			
			session.save(mat);
			
		}
	
      
		
		
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
