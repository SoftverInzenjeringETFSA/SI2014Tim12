package ba.unsa.etf.si.tim12.bll.service;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;


public class KorisnikManager {
	
	private Session session;
	
	public KorisnikManager(Session session) {
		this.session = session;
	}
	
	public boolean provjeriPassword(LoginVM model) {
		
		Transaction t = session.beginTransaction();
		
		String hql = "SELECT k.password FROM Korisnik k " +
					"WHERE k.username = :username";
		Query q = session.createQuery(hql);
		q.setParameter("username", model.getUsername());
		List l = q.list();
		
		if(l.size() < 1)
			return false;
		
		t.commit();
		
		return model.getPassword().equals((String) l.get(0));
	}
	
	public  boolean promjeniPassword(PromjenaPasswordaVM model) {
		return true;
		//TODO: THIS}
		
	}

}
