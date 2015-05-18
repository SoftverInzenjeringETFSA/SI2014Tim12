package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;
import ba.unsa.etf.si.tim12.dal.domainmodel.Posjeta;

import java.util.*;

import org.hibernate.Transaction;
import org.hibernate.Session;

public class PosjetaManager {
	Session session;
	
	public PosjetaManager(Session session) {}
	
	public  ArrayList<PosjetaVM> nadjiPoPacijentu(long idpacijenta) {
		return new ArrayList<PosjetaVM>();
		//TODO: This
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
		return new ArrayList<PosjetaVM>();
		//TODO: This
	}

}
