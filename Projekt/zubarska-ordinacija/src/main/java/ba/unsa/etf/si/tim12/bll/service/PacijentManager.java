package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.PacijentNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;
import ba.unsa.etf.si.tim12.dal.domainmodel.TipZahvata;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class PacijentManager {
	private Session session;
	
	public PacijentManager(Session session) {
		this.session = session;
	}
	
	public  boolean dodajNovogPacijenta(NoviPacijentVM pacijent) {
	
		Transaction t = session.beginTransaction();
		
		Pacijent p = new Pacijent();
		System.out.println(p.getId());
		p.setImeIPrezime(pacijent.getImeIPrezime());
		p.setDatumRodjenja(pacijent.getDatumRodenja());
		p.setTelefon(pacijent.getBrojTelefona());
		p.setOpis(pacijent.getOpis());

		session.save(p);
		
		System.out.println(p.getId());
		t.commit();
		
		return true;
	}
	
	public  boolean modificirajPacijenta(PacijentVM pacijent) {
		
		Transaction t = session.beginTransaction();
		Pacijent p= (Pacijent) session.load(Pacijent.class, pacijent.getId());
		p.setImeIPrezime(pacijent.getImePrezime());
		p.setDatumRodjenja(pacijent.getDatumRodjenja());
		p.setTelefon(pacijent.getBrojTelefona());
		p.setOpis(pacijent.getOpis());
	    
		session.update(p);
		t.commit();
		return true;
		
	}
	
	public  PrikazPacijentaVM dajPacijenta(long id) throws PacijentNotFound {
		Transaction t = session.beginTransaction();
		
		String hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.PrikazPacijentaVM(p.id, p.imeIPrezime, "+
				"p.datumRodjenja, p.telefon, p.opis) FROM Pacijent p WHERE p.id = :id";
		
		Query q = session.createQuery(hql);
		
		q.setLong("id", id);
		
		List l = q.list();
		
		t.commit();
		
		//TODO ili null vratiti umjesto da se izuzetak baci?
		if(l.isEmpty())
			throw new PacijentNotFound("Pacijent s id-om: " + id + " nije pronadjen.");
		
		PrikazPacijentaVM vm = (PrikazPacijentaVM) l.get(0);
		
		TerminManager tManager = new TerminManager(session);
		vm.setTermini(tManager.nadjiPoPacijentu(vm.getId()));
		
		PosjetaManager poManager = new PosjetaManager(session);
		vm.setPosjete(poManager.nadjiPoPacijentu(vm.getId()));
		
		return vm;
	}
	
	public  ArrayList<PacijentVM> nadjiPoIdu(long pacijentId) {
		return new ArrayList<PacijentVM>();
		//TODO: This
	}
	
	public  ArrayList<PacijentVM> nadjiPoImenu(String pacijentIme) {
		return new ArrayList<PacijentVM>();
		//TODO: This
	}
	
	public  ArrayList<PacijentVM> nadjiPoOpisu(String opis) {
		return new ArrayList<PacijentVM>();
		//TODO: This
	}

}
