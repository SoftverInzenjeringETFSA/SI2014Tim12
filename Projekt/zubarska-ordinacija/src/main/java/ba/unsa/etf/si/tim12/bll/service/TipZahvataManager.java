package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.MaterijalTipZahvata;
import ba.unsa.etf.si.tim12.dal.domainmodel.TipZahvata;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class TipZahvataManager {
	Session session;
	
	public TipZahvataManager(Session session) {
		this.session = session;
	}
	
	public boolean dodajNoviTip(NoviTipZahvataVM vm) {
		//Dodavanje zahvata
		Transaction t = session.beginTransaction();
		
		TipZahvata zahvat = new TipZahvata();
		
		zahvat.setNaziv(vm.getNaziv());
		zahvat.setCijena(vm.getCijena());
		zahvat.setOpis(vm.getOpis());
		
		session.save(zahvat);
		
		//Dodavanje Materijala (MaterijalTipZahvata)
		//t = session.beginTransaction();
		
		for (NoviTipZahvataMaterijalVM mvm : vm.getMaterijali()) {
			//provjerimo da li ima materijala u bazi
			String hqlImaLi = "SELECT COUNT(*) FROM Materijal m "+
						"WHERE m.id = :id";
			Query imaLi = session.createQuery(hqlImaLi); 
			imaLi.setLong("id", mvm.getMaterijalId());
			
			long broj = (Long) imaLi.uniqueResult();
			if(broj < 1){ //nema
				t.rollback();
				return false;
			}
			
			MaterijalTipZahvata materijal = new MaterijalTipZahvata();
			materijal.setKolicina(mvm.getKolicina());
			materijal.setMaterijalId(mvm.getMaterijalId());
			materijal.setTipZahvataId(zahvat.getId());
			
			session.save(materijal);
		}
		
		t.commit();
		
		return true;
		//TODO: This
	}
	
	public  void promjeniCijenuZahvata(long id, double cijena) {
		Transaction t = session.beginTransaction();
		TipZahvata zahvat= (TipZahvata) session.load(TipZahvata.class, id);		zahvat.setCijena(cijena);
		session.update(zahvat);
		t.commit();
	}

}
