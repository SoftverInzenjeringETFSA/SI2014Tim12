package ba.unsa.etf.si.tim12.bll.service;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;

@SuppressWarnings("unchecked")
public class IzvjestajManager {
	Session session;
	
	public IzvjestajManager(Session session) {
		this.session = session;
	}	
	//TODO: popraviti query tako da uzima u obzir vrijemeOd i vrijemeDo - Emina
	public ZahvatiPoDoktoruVM sviZahvatiPoDoktoru(String imeDoktora) {
		imeDoktora = "%" + imeDoktora.trim() + "%";
		Transaction t = session.beginTransaction();
		String hql = "select new ZahvatiPoDoktoruRowVM(z.id, pac.imeIPrezime, t.naziv, pos.datum, z.cijena) "
				+ "from posjeta pos, pacijent pac, obavljenizahvat z, tipzahvata t where pos.id = z.posjetaId and "
				+ "t.id = z.zahvatId and pac.id = pos.pacijentId and pos.doktor like :doktor";
		Query q = session.createQuery(hql);
		q.setParameter("doktor", imeDoktora);
		ArrayList<ZahvatiPoDoktoruRowVM> rez = new ArrayList<ZahvatiPoDoktoruRowVM>(q.list());
		t.commit();
		hql = "select new ZahvatiPoDoktoruVM(pos.doktor, min(pos.datum), max(pos.datum), sum(z.cijena), count(pos.id) "
				+ "from posjeta pos, obavljenizahvat z where pos.doktor like :doktor and pos.id = z.posjetaId";
		q = session.createQuery(hql);
//		q.setParameter("doktor", imeDoktora);
		ZahvatiPoDoktoruVM rezultat = (ZahvatiPoDoktoruVM) q.list().get(0);
		t.commit();
		rezultat.setZahvati(rez);
		return rezultat;
	}
	
	public  FinancijskiUlazVM financijskiUlaz(Date vrijemeOd, Date vrijemeDo) {
		Transaction t = session.beginTransaction();
		String hql = "select new FinancijskiUlazVM(:vrijemeOd, :vrijemeDo, sum(z.cijena), count(p.id)"
				+ " from obavljeniZahvat z, posjeta p where p.id = obavljeniZahvat.posjetaId "
				+ "and p.datum between :vrijemeOd and :vrijemeDo";
		Query q = session.createQuery(hql);
		q.setDate("vrijemeOd", vrijemeOd);
		q.setDate("vrijemeDo", vrijemeDo);
		FinancijskiUlazVM rez;
		if(!q.list().isEmpty())
			rez = (FinancijskiUlazVM) q.list().get(0);
		else rez = null;
		t.commit();
		hql = "select new FinancijskiUlazRowVM(pos.id, pac.imeIPrezime, t.naziv, pos.datum, z.cijena "
				+ "from posjete pos, pacijent pac, tipzahvata t, obavljenizahvat z "
				+ "where pos.id = z.posjetaid and pac.id = pos.pacijentid and tipzahvataid = z.zahvatid "
				+ "and pos.datum between :vrijemeOd and :vrijemeDo";
		ArrayList<FinancijskiUlazRowVM> r = new ArrayList<FinancijskiUlazRowVM>(q.list());
		t.commit();
		rez.setFinancijskiUlazRowVM(r);
		return rez;
	}
	
	public  PotMaterijaliVM potroseniMaterijali(Date vrijemeOd, Date vrijemeDo) {return new PotMaterijaliVM();}
	
	public  PosjetePacijentaVM posjetePacijenta(long idPacijenta) { return new PosjetePacijentaVM();}
	
	public  OdradjenePosjeteVM odradjenePosjetePoDanu(Date vrijeme) {return new OdradjenePosjeteVM();}
	
	
}
