package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.MaterijalNotFound;
import ba.unsa.etf.si.tim12.PacijentNotFound;
import ba.unsa.etf.si.tim12.TipZahvataNotFound;
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
		
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.PosjetaVM(p.id, p.pacijentId, "
				+ "p.doktor, p.dijagnoza, p.vrijeme) "
				+ "from Posjeta p where p.pacijentId=:idpacijenta";
		
		Query q = session.createQuery(hql);
		q.setLong("idpacijenta", idpacijenta); 
	  
	    List<PosjetaVM> lista = q.list();
	    
	    ArrayList<PosjetaVM> lista1 = new ArrayList<PosjetaVM>(lista.size());
	    lista1.addAll(lista);
	    
	    t.commit();
		return lista1;
	}
	
	public  Long dodajNovuPosjetu(NovaPosjetaVM posjeta) throws Exception {
		Transaction t = session.beginTransaction();
		
		try{
		
		Posjeta p = new Posjeta();
		
		Pacijent pac = (Pacijent) session.get(Pacijent.class, posjeta.getPacijentId());
		if(pac == null)
			throw new PacijentNotFound("Pacijent sa unesenim id-em ne postoji!");

		p.setPacijentId(posjeta.getPacijentId());
		p.setDoktor(posjeta.getDoktor());
		p.setDijagnoza(posjeta.getDijagnoza());
		p.setVrijeme(posjeta.getVrijeme());
	
		session.save(p);
		
		ObavljeniZahvatManager mngr = new ObavljeniZahvatManager(session);
		for(NoviObavljeniZahvatVM z: posjeta.getObavljeniZahvati())
		{
			z.setPosjetaId(p.getId());
			if(!mngr.dodajNoviZahvat(z))
				throw new Exception("Greska!Zahvat nije dodan!");
		}
		
		t.commit();
		
		return p.getId();
		
		}
		catch(PacijentNotFound e)
		{
			t.rollback();
			throw e;
		}
		catch(MaterijalNotFound e)
		{
			t.rollback();
			throw e;
		}
		catch(TipZahvataNotFound e)
		{
			t.rollback();
			throw e;
		}
		catch(Exception e)
		{
			t.rollback();
			throw e;
		}
		
		
		
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
	
	public ArrayList<PosjetaVM> dajSvePosjete(){
		String hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.PosjetaVM(p.id, p.pacijentId, "+
				"p.doktor, p.dijagnoza, p.vrijeme) FROM Posjeta p ";
		Query query = session.createQuery(hql);
		return new ArrayList<PosjetaVM>(query.list());
	}
}
