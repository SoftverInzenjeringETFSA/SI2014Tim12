package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class MaterijaliManager {
	Session session;
	
	public MaterijaliManager(Session session) {
		this.session = session;	
		
	}
	
	public  MaterijalVM nadjiPoImenu(String ime) {
        Transaction t = session.beginTransaction();
        
		String hql ="from Materijal where naziv=:ime";
		Query q = session.createQuery(hql);
		q.setString("naziv", ime);
		
		MaterijalVM materijal = (MaterijalVM)q.uniqueResult();
		
		if (materijal == null) {
	         throw new RuntimeException("Ne postoji materijal sa nazivom :" + ime);
	    }
				
		t.commit();	
		return materijal;
		
	}
	
	public  boolean izbrisiMaterijal(long id) {
		Transaction t = session.beginTransaction();
	
		String hql ="delete Materijal where id=:id";
		
		        Query q = session.createQuery(hql);
				q.setParameter("id", 1);
		        int result = q.executeUpdate();

		        t.commit();
		        return true;

		
	}
	
	
	//povratni tip je MaterijalVM, znaci jedna instanca, a nekako mi logicno
	//da jedan materijal moze biti sadrzan u vise zahvata, ne znam kako je u SRS
	public  MaterijalVM nadjiPoTipuZahvata(long tipZahvataId) {
		Transaction t = session.beginTransaction();
        
		String hql ="from MaterijalTipZahvata where tipZahvataId=:tipZahvataId";
		Query q = session.createQuery(hql);
		q.setLong("tipZahvataId", tipZahvataId);
		
		MaterijalVM materijal = (MaterijalVM)q.uniqueResult();
		
		if (materijal == null) {
	         throw new RuntimeException("Ne postoji materijal sa tipom zahvata :" + tipZahvataId);
	    }			
		t.commit();	
		return materijal;	
		
	}
	
	
	//nisam uspio povezati odakle da kupim iz baze podatke xD gotovo isti kod bi trebao biti ovdje
	//kao u prethodnoj metodi
	public  MaterijalVM nadjiPoObavljenomZahvatu(long obavljeniZahvatId) {
		return new MaterijalVM();
		//TODO: THIS
		
	}

}
