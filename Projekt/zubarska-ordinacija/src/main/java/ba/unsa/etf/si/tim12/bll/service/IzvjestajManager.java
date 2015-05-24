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

	public ZahvatiPoDoktoruVM sviZahvatiPoDoktoru(String imeDoktora, Date vrijemeOd, Date vrijemeDo) {
		
		Transaction t = session.beginTransaction();
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.ZahvatiPoDoktoruRowVM(z.id, pac.imeIPrezime, t.naziv, pos.vrijeme, z.cijena) "
				+ "from posjeta pos, pacijent pac, obavljenizahvat z, tipzahvata t where pos.id = z.posjetaId and "
				+ "t.id = z.zahvatId and pac.id = pos.pacijentId and pos.doktor = :doktor and "
				+ "pos.vrijeme BETWEEN :date1 AND :date2";
		Query q = session.createQuery(hql);
		q.setParameter("doktor", imeDoktora);
		q.setDate("date1", vrijemeOd);
		q.setDate("date2", vrijemeDo);
		
		ArrayList<ZahvatiPoDoktoruRowVM> rez = new ArrayList<ZahvatiPoDoktoruRowVM>(
				q.list());
		
		hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.ZahvatiPoDoktoru(COUNT(distinct p.id), sum(z.cijena)) "
				+ "FROM Posjeta p, ObavljeniZahvat z "
				+ "WHERE p.doktor = :doktor and "
				+ "z.posjetaId = p.id";
		q = session.createQuery(hql);
		q.setParameter("doktor", imeDoktora);
		List<Object> r = q.list();
				
	      ZahvatiPoDoktoruVM rezultat = (ZahvatiPoDoktoruVM) r.get(0);
	      rezultat.setDoktor(imeDoktora);
	      rezultat.setVrijemeDo(vrijemeDo);
	      rezultat.setVrijemeOd(vrijemeOd);
	      rezultat.setZahvati(rez);
	    
	    t.commit();
		return rezultat;
	}
	
	public FinancijskiUlazVM financijskiUlaz(Date vrijemeOd, Date vrijemeDo) {
		Transaction t = session.beginTransaction();
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazVM(:vrijemeOd, :vrijemeDo, sum(z.cijena), count(DISTINCT p.id)"
				+ " from obavljeniZahvat z, posjeta p where p.id = obavljeniZahvat.posjetaId "
				+ "and p.vrijeme between :vrijemeOd and :vrijemeDo";
		Query q = session.createQuery(hql);
		q.setDate("vrijemeOd", vrijemeOd);
		q.setDate("vrijemeDo", vrijemeDo);
		FinancijskiUlazVM rez;
		if (!q.list().isEmpty())
			rez = (FinancijskiUlazVM) q.list().get(0);
		else
			rez = null;
		
		hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazRowVM(z.id, pac.imeIPrezime, t.naziv, pos.vrijeme, z.cijena "
				+ "from posjete pos, pacijent pac, tipzahvata t, obavljenizahvat z "
				+ "where pos.id = z.posjetaId and pac.id = pos.pacijentId and t.id = z.zahvatId "
				+ "and pos.datum between :vrijemeOd and :vrijemeDo";
		q = session.createQuery(hql);
		q.setDate("vrijemeOd", vrijemeOd);
		q.setDate("vrijemeDo", vrijemeDo);
		
		ArrayList<FinancijskiUlazRowVM> r = new ArrayList<FinancijskiUlazRowVM>(q.list());
		
		rez.setFinancijskiUlazRowVM(r);
		
		t.commit();
		return rez;
	}

	public PotMaterijaliVM potroseniMaterijali(Date vrijemeOd, Date vrijemeDo) {
		Transaction t = session.beginTransaction();
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.PotMaterijaliVM(:vrijemeOd, :vrijemeDo, sum(m.cijena)) "
				+ "from materijal m, utrosenimaterijal u, obavljeni zahvat o, posjeta p "
				+ "where m.id = u.materijalId and o.id = u.obavljeniZahvatId and p.id = o.posjetaId "
				+ "and p.vrijeme BETWEEN :vrijemeOd AND :vrijemeDo";
		Query q = session.createQuery(hql);
		q.setDate("vrijemeOd", vrijemeOd);
		q.setDate("vrijemeDo", vrijemeDo);		
		
		PotMaterijaliVM rez = new PotMaterijaliVM();
		rez.setUkupnaCijena(0);
		rez.setVrijemeDo(vrijemeDo);
		rez.setVrijemeOd(vrijemeOd);
		
		if (!q.list().isEmpty())
			rez = (PotMaterijaliVM) q.list().get(0);
		else
			return rez;
		
		hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.PotMaterijaliRowVM(m.id, m.cijena, u.kolicina, m.mjernaJedinica, m.naziv, u.kolicina*m.cijena)"
				+ "from materijal m, utrosenimaterijal u, posjeta p, obavljenizahvat o "
				+ "where m.id = u.materijalId and o.id = u.obavljeniZahvatId and p.id = o.posjetaId "
				+ "and p.vrijeme between :vrijemeOd and :vrijemeDo";
		
		ArrayList<PotMaterijaliRowVM> r = new ArrayList<PotMaterijaliRowVM>(q.list());
		
		t.commit();
		
		rez.setMaterijali(r);
		
		return rez;
	}

	public PosjetePacijentaVM posjetePacijenta(long idPacijenta) {
		Transaction t = session.beginTransaction();
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaVM(pac.imeIPrezime, pac.id, count(*) "
				+ "from pacijent pac, posjeta pos where pos.pacijentId = pac.id and pac.id = :id)";
		Query q = session.createQuery(hql);
		q.setLong("id", idPacijenta);	
		PosjetePacijentaVM rez;
		if (!q.list().isEmpty())
			rez = (PosjetePacijentaVM) q.list().get(0);
		else
			rez = null;
		t.commit();
		hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaRowVM(t.naziv, p.dijagnoza, p.doktor, p.datum)"
				+ "from posjeta p, pacijent pac, tipzahvata z, obavljenizahvat o "
				+ "where p.id = o.posjetaId and t.id = o.zahvatId and pac.id = p.pacijentId and pac.id = :id";
		ArrayList<PosjetePacijentaRowVM> r = new ArrayList<PosjetePacijentaRowVM>(q.list());
		t.commit();
		rez.setPosjete(r);
		return rez;
	}

	public OdradjenePosjeteVM odradjenePosjetePoDanu(Date vrijeme) {
		Transaction t = session.beginTransaction();
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.OdradjenePosjeteVM(datum, count(*) from posjeta "
				+ "where datum = :datum)";
		Query q = session.createQuery(hql);
		q.setDate("datum", vrijeme);
		OdradjenePosjeteVM posjete;
		if(!q.list().isEmpty())
			posjete = (OdradjenePosjeteVM) q.list().get(0);
		else posjete = null;
		t.commit();
		hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.OdradjenePosjeteRowVM(pos.id, pac.imeIPrezime, pos.doktor, pos.datum)"
				+ "from posjeta pos, pacijent pac where pac.id = pos.pacijentId and pos.datum = :datum";
		ArrayList<OdradjenePosjeteRowVM> posjeteRed = new ArrayList<OdradjenePosjeteRowVM>(q.list());
		t.commit();
		posjete.setOdradjenePosjete(posjeteRed);
		return posjete;
	}

}
