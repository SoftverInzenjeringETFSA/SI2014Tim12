package ba.unsa.etf.si.tim12.bll.service;
import java.util.List;

import ba.unsa.etf.si.tim12.MaterijalNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class MaterijaliManager {
	Session session;
	
	public MaterijaliManager(Session session) {
		this.session = session;	
		
	}
	
	public ArrayList< MaterijalVM> nadjiPoImenu(String ime)throws MaterijalNotFound {
        Transaction t = session.beginTransaction();
        String naziv="%"+ime+"%";
                	
        String hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) FROM Materijal p WHERE p.naziv LIKE :naziv";
		Query q = session.createQuery(hql);
		
		q.setString("naziv",naziv);
		
		List<MaterijalVM> nesto =  (List<MaterijalVM>) q.list();
		ArrayList<MaterijalVM> materijali = new ArrayList<MaterijalVM>(nesto);
		
		if (materijali.isEmpty()) {
	         throw new MaterijalNotFound("Ne postoji materijal sa nazivom :" + ime);
	    }
				
		t.commit();	
		return materijali;
	
		
	}
	
	public  boolean izbrisiMaterijal(long id) {
		Transaction t = session.beginTransaction();
	
		 String hql = "DELETE Materijal WHERE id = :id";
		
		        Query q = session.createQuery(hql);
				q.setParameter("id",id);

		        int result = q.executeUpdate();
		        if (result == 0) {
		        	return false;
			       //  throw new RuntimeException("Ne postoji materijal sa takvim ID-om!");
			    }	
		        t.commit();
		        return true;
		
	}
	
	
	
	public ArrayList< MaterijalVM> nadjiPoTipuZahvata(long tipZahvataId) {
		Transaction t = session.beginTransaction();
		
		String hql ="Select new ba.unsa.etf.si.tim12.dal.domainmodel.UtroseniMaterijal(u.materijalId) FROM  UtroseniMaterijal u INNER JOIN ba.unsa.etf.si.tim12.dal.domainmodel.ObavljeniZahvat o ON u.obavljeniZahvatId = o.zahvatId WHERE u.obavljeniZahvatId= :tipZahvataId";
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
	
	
	
	public  ArrayList< MaterijalVM> nadjiPoObavljenomZahvatu(long obavljeniZahvatId) {
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
