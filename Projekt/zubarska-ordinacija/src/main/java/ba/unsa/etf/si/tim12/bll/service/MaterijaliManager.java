package ba.unsa.etf.si.tim12.bll.service;
import java.util.List;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class MaterijaliManager {
	Session session;
	
	public MaterijaliManager(Session session) {
		this.session = session;	
		
	}
	
	public List< MaterijalVM> nadjiPoImenu(String ime) {
        Transaction t = session.beginTransaction();
        String naziv="%"+ime+"%";
                	
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalIVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) FROM Materijal where p.naziv LIKE :naziv";
		Query q = session.createQuery(hql);
		
		q.setString("naziv", ime);
		
		List<MaterijalVM> nesto = q.list();
		ArrayList<MaterijalVM> materijali = new ArrayList<MaterijalVM>(nesto);
	
		
		
		if (materijali.isEmpty()) {
	         throw new RuntimeException("Ne postoji materijal sa nazivom :" + ime);
	    }
				
		t.commit();	
		return materijali;
		
	}
	
	public  boolean izbrisiMaterijal(long id) {
		Transaction t = session.beginTransaction();
	
		String hql ="DELETE new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalIVM where id= :id";
		
		        Query q = session.createQuery(hql);
				q.setParameter("id", id);

		        int result = q.executeUpdate();
		        if (result == 0) {
			         throw new RuntimeException("Ne postoji materijal sa takvim ID-om!");
			    }	
		        t.commit();
		        return true;
		
	}
	
	
	
	public List< MaterijalVM> nadjiPoTipuZahvata(long tipZahvataId) {
		Transaction t = session.beginTransaction();
        
		//nisam siguran
		String hql ="SELECT new ba.unsa.etf.si.tim12.bll.viewmodel.UtroseniMaterijal(materijalId, obavljeniZahvatId) FROM UtroseniMaterijal INNER JOIN ObavljeniZahvat ON UtroseniMaterijal.obavljeniZahvatId = ObavljeniZahvat.zahvatId WHERE UtroseniMaterijal.obavljeniZahvatId= :tipZahvataId";
		Query q = session.createQuery(hql);
		q.setLong("tipZahvataId", tipZahvataId);
		
		List<MaterijalVM> nesto = q.list();
		ArrayList<MaterijalVM> materijali = new ArrayList<MaterijalVM>(nesto);
	
		
		
		if (materijali.isEmpty()) {
	         throw new RuntimeException("Ne postoji materijal sa tim tipom zahvata!");
	    }			
		t.commit();	
		return materijali;	
		
	}
	
	
	
	public  List< MaterijalVM> nadjiPoObavljenomZahvatu(long obavljeniZahvatId) {
Transaction t = session.beginTransaction();
        
		//nisam siguran
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalIVM(id, naziv, mjernaJedinica,cijena) FROM MaterijalIVM INNER JOIN UtroseniMaterijal ON MaterijalIVM.id =  (Select materijalId from UtroseniMaterijal u,ObavljeniZahvat o where u.obavljeniZahvatId=o.id) WHERE UtroseniMaterijal.obavljeniZahvatId= :tipZahvataId";
		Query q = session.createQuery(hql);
		q.setLong("obavljeniZahvatId", obavljeniZahvatId);
		
		List<MaterijalVM> nesto = q.list();
		ArrayList<MaterijalVM> materijali = new ArrayList<MaterijalVM>(nesto);
	
		
		
		if (materijali.isEmpty()) {
	         throw new RuntimeException("Ne postoji materijal sa tim obavljenim zahvatom!");
	    }			
		t.commit();	
		return materijali;	
		
	}

}
