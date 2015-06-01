package ba.unsa.etf.si.tim12.bll.service;
import java.util.List;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.Materijal;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class MaterijaliManager {
	Session session;
	
	public MaterijaliManager(Session session) {
		this.session = session;		
	}
	
	public ArrayList<MaterijalVM> nadjiPoImenu(String ime) {
        Transaction t = session.beginTransaction();
        String naziv="%"+ime+"%";
                	
        String hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) "+
        			"FROM Materijal p WHERE p.naziv LIKE :naziv";
		Query q = session.createQuery(hql);
		
		q.setString("naziv",naziv);
		
		List<MaterijalVM> nesto =  (List<MaterijalVM>) q.list();
		ArrayList<MaterijalVM> materijali = new ArrayList<MaterijalVM>(nesto);
		
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
	    }
		
		hql = "DELETE MaterijalTipZahvata WHERE materijalId = :id";
		q = session.createQuery(hql);
		q.setParameter("id",id);

		q.executeUpdate();	

		hql = "DELETE UtroseniMaterijal WHERE materijalId = :id";
		q = session.createQuery(hql);
		q.setParameter("id",id);

		q.executeUpdate();	


        t.commit();
        return true;
	}
	
	
	public ArrayList<TipZahvataMaterijalVM> nadjiPoTipuZahvata(long tipZahvataId) {

		
		Transaction t = session.beginTransaction();
		//nisam ovo uspio sa JOIN-om pa sam ovako uradio,
		//jer za JOIN bi sve tri klase biti poveza i napraviti
		//poseban konstruktor koji bi vracao sta se tacno hoce ovom metodom
		//String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) FROM  Materijal p,UtroseniMaterijal u INNER JOIN ObavljeniZahvat o ON  u.obavljeniZahvatId = o.zahvatId WHERE p.id=u.materijalId AND u.obavljeniZahvatId= :tipZahvataId";		Query q = session.createQuery(hql);
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataMaterijalVM(m.id, m.naziv, m.mjernaJedinica, mt.tipZahvataId, mt.kolicina) "
				+ "FROM  Materijal m, MaterijalTipZahvata mt "
				+ "WHERE m.id= mt.materijalId AND mt.tipZahvataId = :tipZahvataId";		
		Query q = session.createQuery(hql);
		q.setLong("tipZahvataId", tipZahvataId);
		
		List<TipZahvataMaterijalVM> nesto =  (List<TipZahvataMaterijalVM>) q.list();
		ArrayList<TipZahvataMaterijalVM> materijali = new ArrayList<TipZahvataMaterijalVM>(nesto);
	
		t.commit();	
		return materijali;	

	}
	
	
	
	public  ArrayList< MaterijalVM> nadjiPoObavljenomZahvatu(long obavljeniZahvatId) {
		
		Transaction t = session.beginTransaction();
        
		//i ovaj je isti kao prethodni , ne znam koja je razlika, uglavnom samo upit se treba promjeniti
		//bas kao i na prethodnom
		String hql ="Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) "
				+ "FROM Materijal p, UtroseniMaterijal u, ObavljeniZahvat o "
				+ "WHERE u.obavljeniZahvatId=o.id AND p.id=u.materijalId AND o.id= :obavljeniZahvatId";
		Query q = session.createQuery(hql);
		q.setLong("obavljeniZahvatId", obavljeniZahvatId);
		
		List<MaterijalVM> nesto =  (List<MaterijalVM>) q.list();
		ArrayList<MaterijalVM> materijali = new ArrayList<MaterijalVM>(nesto);
	
		t.commit();	
		return materijali;	
		
	}
	
	public boolean dodajNoviMaterijal(NoviMaterijalVM NoviMaterijal)
	{
		Transaction t = session.beginTransaction();
		
		String hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) "
				+ "FROM Materijal p WHERE p.naziv= :NoviMaterijal";		
        Query q = session.createQuery(hql);
        q.setString("NoviMaterijal",NoviMaterijal.getNaziv());
		
        List<MaterijalVM> nesto =  (List<MaterijalVM>) q.list();
        //Vidi da li ovdje treba bacati Exception ili returnati FALSE 
			if(!nesto.isEmpty() )
				return false;
		//throw new RuntimeException ("Materijal sa imenom već postoji!");
		
		
		Materijal m = new Materijal();
		m.setNaziv(NoviMaterijal.getNaziv());
		m.setCijena(NoviMaterijal.getCijena());
		m.setMjernaJedinica(NoviMaterijal.getMjernaJedinica());

		session.save(m);
		t.commit();
		return true;
		

	}

}
