package ba.unsa.etf.si.tim12.bll.service;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.Korisnik;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;


public class KorisnikManager {
	
	private Session session;
	
	public KorisnikManager(Session session) {
		this.session = session;
	}
	
	public boolean provjeriPassword(LoginVM model) throws Exception {
		
		model.setPassword(HashPassword(model.getPassword()));
		
		Transaction t = session.beginTransaction();
		
		String hql = "SELECT k.password FROM Korisnik k " +
					"WHERE k.username = :username";
		Query q = session.createQuery(hql);
		q.setParameter("username", model.getUsername());
		List<String> l = q.list();
		
		if(l.isEmpty())
			return false;
		
		t.commit();
		
		return model.getPassword().equals((String) l.get(0));
	}
	
	//mijenjam KorisnikManager::PromijeniPassword (Enil)
	public int promjeniPassword(PromjenaPasswordaVM model) throws Exception {
		
		//Ponovljeni password nije uredu
		if(!model.getNoviPass().equals(model.getPonovoNoviPass()))
			return 1;
		
		model.setStariPass(HashPassword(model.getStariPass()));
		
		Transaction t = session.beginTransaction();
		
		String hql = "FROM Korisnik k " +
					"WHERE k.username = :username";
		Query q = session.createQuery(hql);
		q.setParameter("username", model.getUsername());
		
		Korisnik korisnik = (Korisnik) q.uniqueResult();
		if(korisnik == null){ //Nije pronadjen korisnik s ovim usernameom
			t.rollback();
			return 2;
		} else if (!korisnik.getPassword().equals(model.getStariPass())){
			//Stari password nije isti
			t.rollback();
			return 3;
		} else {
			//promijeni password i spremi promjene
			korisnik.setPassword(HashPasswordNoTransaction(model.getNoviPass()));
			session.update(korisnik);
			t.commit();
			return 0;
		}
	}
	
	public String HashPassword(String passwordString) throws Exception{
		
		Transaction t = session.beginTransaction();
		

		String hash = HashPasswordNoTransaction(passwordString);
		
		t.commit();
		return hash;
	}
	
	public String HashPasswordNoTransaction(String passwordString) throws Exception{
		
		
		String sql = "SELECT PASSWORD(:password) FROM Dual";
		Query q = session.createSQLQuery(sql);
		q.setParameter("password", passwordString);
		String hash = (String) q.uniqueResult();
		
		return hash;
	}


}
