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
	
	public  boolean izbrisiMaterijal(long id) throws MaterijalNotFound {
		Transaction t = session.beginTransaction();
	
		 String hql = "DELETE Materijal WHERE id = :id";
		
		        Query q = session.createQuery(hql);
				q.setParameter("id",id);

		        int result = q.executeUpdate();
		        if (result == 0) {
			        throw  new MaterijalNotFound("Ne postoji materijal sa takvim ID-om!");
			    }	
		        t.commit();
		        return true;
		
	}
	
	
	
	public ArrayList< MaterijalVM> nadjiPoTipuZahvata(long tipZahvataId)throws MaterijalNotFound {

		
		Transaction t = session.beginTransaction();
		//nisam ovo uspio sa JOIN-om pa sam ovako uradio,
		//jer za JOIN bi sve tri klase biti poveza i napraviti
		//poseban konstruktor koji bi vracao sta se tacno hoce ovom metodom
		//String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) FROM  Materijal p,UtroseniMaterijal u INNER JOIN ObavljeniZahvat o ON  u.obavljeniZahvatId = o.zahvatId WHERE p.id=u.materijalId AND u.obavljeniZahvatId= :tipZahvataId";		Query q = session.createQuery(hql);
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) FROM  Materijal p,UtroseniMaterijal u, ObavljeniZahvat o  WHERE  u.obavljeniZahvatId = o.zahvatId AND p.id=u.materijalId AND u.obavljeniZahvatId= :tipZahvataId";		
		Query q = session.createQuery(hql);
		q.setLong("tipZahvataId", tipZahvataId);
		
		List<MaterijalVM> nesto =  (List<MaterijalVM>) q.list();
		ArrayList<MaterijalVM> materijali = new ArrayList<MaterijalVM>(nesto);
	
		
		
		if (materijali.isEmpty()) {
			 throw new MaterijalNotFound("Ne postoji materijal sa tim tipom zahvata!");
	    }			
		t.commit();	
		return materijali;	
		
	}
	
	
	
	public  ArrayList< MaterijalVM> nadjiPoObavljenomZahvatu(long obavljeniZahvatId)throws MaterijalNotFound {
Transaction t = session.beginTransaction();
        
		//i ovaj je isti kao prethodni , ne znam koja je razlika, uglavnom samo upit se treba promjeniti
		//bas kao i na prethodnom
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) FROM Materijal p, UtroseniMaterijal u, ObavljeniZahvat o WHERE u.obavljeniZahvatId=o.zahvatId AND p.id=u.materijalId AND u.obavljeniZahvatId= :obavljeniZahvatId";
		Query q = session.createQuery(hql);
		q.setLong("obavljeniZahvatId", obavljeniZahvatId);
		
		List<MaterijalVM> nesto =  (List<MaterijalVM>) q.list();
		ArrayList<MaterijalVM> materijali = new ArrayList<MaterijalVM>(nesto);
	
		
		if (materijali.isEmpty()) {
			throw new MaterijalNotFound("Ne postoji materijal sa tim obavljenim zahvatom!");
	    }			
		t.commit();	
		return materijali;	
		
	}

}
