package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class MaterijaliManager {
	Session session;
	
	public MaterijaliManager(Session session) {
		
		
	}
	
	public  MaterijalVM nadjiPoImenu(String ime) {
		return new MaterijalVM();
		//TODO: THIS
		
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
	
	public  MaterijalVM nadjiPoTipuZahvata(long tipZahvataId) {
		return new MaterijalVM();
		//TODO: THIS
		
	}
	
	public  MaterijalVM nadjiPoObavljenomZahvatu(long obavljeniZahvatId) {
		return new MaterijalVM();
		//TODO: THIS
		
	}

}
