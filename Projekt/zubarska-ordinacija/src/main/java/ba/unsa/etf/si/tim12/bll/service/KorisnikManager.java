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
		
		if(l.isEmpty())
			return false;
		
		t.commit();
		
		return model.getPassword().equals((String) l.get(0));
	}
	
	
	public  boolean promjeniPassword(PromjenaPasswordaVM model) {
       Transaction t = session.beginTransaction();
		 
		String hql = "SELECT k.password FROM Korisnik k " +
					"WHERE k.username = :username";
		Query q = session.createQuery(hql);
		//q.setParameter("username", model.getUsername());
		List l = q.list();
		
		if(l.size() < 1)
			return false;
		
		//ovdje ne znam odakle da uzmem stringove za novi pass i ponovonovi pass 
		//pa sam za sad ovako ostavio
		model.setNoviPass("nekistring"); 
		model.setPonovoNoviPass("ponovnekistring");
		
		t.commit();
		
		return model.getNoviPass().equals(model.getPonovoNoviPass());	
		
	}

}
