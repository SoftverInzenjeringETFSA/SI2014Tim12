package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;
import ba.unsa.etf.si.tim12.dal.domainmodel.Posjeta;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class PosjetaManager {
	Session session;
	
	public PosjetaManager(Session session) 
	{
		this.session = session;
	}
	
	public  ArrayList<PosjetaVM> nadjiPoPacijentu(long idpacijenta) {
		Transaction t = session.beginTransaction();
		
		//TODO: ovdje trebe Select new TerminVM(...) tako da neće ni biti potrebe
		//za konverzijom
		String hql ="from Posjeta where pacijentId=:idpacijenta";
		Query q = session.createQuery(hql);
		q.setLong("idpacijenta", idpacijenta); 
	  
	    List<PosjetaVM> lista = q.list();
	    
	    ArrayList<PosjetaVM> lista1 = new ArrayList<PosjetaVM>(lista.size());
	    lista1.addAll(lista);
	    
	    t.commit();
		return lista1;
	}
	
	public  void dodajNovuPosjetu(NovaPosjetaVM posjeta) {
     Transaction t = session.beginTransaction();
		
		Posjeta p = new Posjeta();
		System.out.println(p.getId());
		
		p.setDoktor(posjeta.getDoktor());
		p.setPacijentId(posjeta.getPacijentId());
		p.setDijagnoza(posjeta.getDijagnoza());
		p.setVrijeme(posjeta.getVrijeme());
	
		session.save(p);
		
		System.out.println(p.getId());
		t.commit();
		
	}
	
	public  ArrayList<PosjetaVM> nadjiPoDijagnozi(String dijagnoza) {
		dijagnoza = dijagnoza.trim();
		dijagnoza = "%" + dijagnoza + "%";
		dijagnoza = dijagnoza.toLowerCase();
		Transaction t = session.beginTransaction();
		String hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.PosjetaVM(p.id, p.pacijentId, "+
				"p.doktor, p.dijagnoza, p.vrijeme) FROM Posjeta p WHERE p.dijagnoza like :dijagnoza";
		Query query = session.createQuery(hql);
		query.setString("dijagnoza", dijagnoza);
		List<PosjetaVM> rezultati = query.list();
		t.commit();
		
		return new ArrayList<PosjetaVM>(rezultati);
	}

}
