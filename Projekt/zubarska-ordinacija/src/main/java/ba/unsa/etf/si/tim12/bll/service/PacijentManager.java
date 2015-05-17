package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.PacijentNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;

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
		return true;
		//TODO: This
	}
	
	public  boolean modificirajPacijenta(PacijentVM pacijent) {
		return true;
		//TODO: This
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
		if(l.size() < 0)
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
