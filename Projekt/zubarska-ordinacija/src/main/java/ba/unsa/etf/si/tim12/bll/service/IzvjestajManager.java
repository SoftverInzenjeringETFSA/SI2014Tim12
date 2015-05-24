package ba.unsa.etf.si.tim12.bll.service;

import ba.unsa.etf.si.tim12.PacijentNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;

@SuppressWarnings("unchecked")
public class IzvjestajManager {
	Session session;

	public IzvjestajManager(Session session) {
		this.session = session;
	}

	public ZahvatiPoDoktoruVM sviZahvatiPoDoktoru(String imeDoktora, Date vrijemeOd, Date vrijemeDo) {
		
		Transaction t = session.beginTransaction();
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.ZahvatiPoDoktoruRowVM(z.cijena, z.id, pac.imeIPrezime, t.naziv, pos.vrijeme) "
				+ "from Posjeta pos, Pacijent pac, ObavljeniZahvat z, TipZahvata t where pos.id = z.posjetaId and "
				+ "t.id = z.zahvatId and pac.id = pos.pacijentId and pos.doktor = :doktor and "
				+ "pos.vrijeme BETWEEN :date1 AND :date2";
		Query q = session.createQuery(hql);
		q.setParameter("doktor", imeDoktora);
		q.setDate("date1", vrijemeOd);
		q.setDate("date2", vrijemeDo);
		
		ArrayList<ZahvatiPoDoktoruRowVM> rez = new ArrayList<ZahvatiPoDoktoruRowVM>(
				q.list());
		
		hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.ZahvatiPoDoktoruVM(COUNT(distinct p.id), sum(z.cijena)) "
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
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazVM(sum(z.cijena), count(DISTINCT p.id))"
				+ " from ObavljeniZahvat z, Posjeta p where p.id = z.posjetaId "
				+ "and p.vrijeme between :vrijemeOd and :vrijemeDo";
		Query q = session.createQuery(hql);
		q.setDate("vrijemeOd", vrijemeOd);
		q.setDate("vrijemeDo", vrijemeDo);
		FinancijskiUlazVM rez = new FinancijskiUlazVM();
		rez.setUkupnaCijena(0);
		rez.setUkupnoPosjeta(0);
		
		if (!q.list().isEmpty())
			rez = (FinancijskiUlazVM) q.list().get(0);
		else
			return rez;
		
		rez.setVrijemeDo(vrijemeDo);
		rez.setVrijemeOd(vrijemeOd);
		
		
		hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazRowVM(z.id, pac.imeIPrezime, t.naziv, pos.vrijeme, z.cijena) "
				+ "from Posjeta pos, Pacijent pac, TipZahvata t, ObavljeniZahvat z "
				+ "where pos.id = z.posjetaId and pac.id = pos.pacijentId and t.id = z.zahvatId "
				+ "and pos.vrijeme between :vrijemeOd and :vrijemeDo";
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
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.PotMaterijaliVM(sum(m.cijena * u.kolicina)) "
				+ "from Materijal m, UtroseniMaterijal u, ObavljeniZahvat o, Posjeta p "
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
		

		rez.setVrijemeDo(vrijemeDo);
		rez.setVrijemeOd(vrijemeOd);
		
		hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.PotMaterijaliRowVM(m.id, m.cijena, u.kolicina, m.mjernaJedinica, m.naziv, u.kolicina*m.cijena)"
				+ "from Materijal m, UtroseniMaterijal u, Posjeta p, ObavljeniZahvat o "
				+ "where m.id = u.materijalId and o.id = u.obavljeniZahvatId and p.id = o.posjetaId "
				+ "and p.vrijeme between :vrijemeOd and :vrijemeDo";
		
		ArrayList<PotMaterijaliRowVM> r = new ArrayList<PotMaterijaliRowVM>(q.list());
		
		t.commit();
		
		rez.setMaterijali(r);
		
		return rez;
	}

	public PosjetePacijentaVM posjetePacijenta(long idPacijenta) throws Exception {
		Transaction t = session.beginTransaction();
		
		Pacijent pac = (Pacijent) session.get(Pacijent.class, idPacijenta);
		if(pac == null)
			throw new PacijentNotFound("Pacijent sa unesenim id-em ne postoji!");

		
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaVM(pac.id, pac.imeIPrezime, count(DISTINCT pos.id)) "
				+ "from Pacijent pac, Posjeta pos where pos.pacijentId = pac.id and pac.id = :id)";
		Query q = session.createQuery(hql);
		q.setLong("id", idPacijenta);	
		
		
		PosjetePacijentaVM rez = (PosjetePacijentaVM) q.list().get(0);
		
		hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaRowVM(p.dijagnoza, p.doktor, t.opis, p.vrijeme)"
				+ "from Posjeta p, Pacijent pac, TipZahvata t, ObavljeniZahvat o "
				+ "where p.id = o.posjetaId and t.id = o.zahvatId and pac.id = p.pacijentId and pac.id = :id";
		q = session.createQuery(hql);
		q.setLong("id", idPacijenta);
		
		ArrayList<PosjetePacijentaRowVM> r = new ArrayList<PosjetePacijentaRowVM>(q.list());
		
		t.commit();
		rez.setPosjete(r);
		return rez;
	}

	public OdradjenePosjeteVM odradjenePosjetePoDanu(Date vrijeme) {
		Transaction t = session.beginTransaction();
		
		String hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.OdradjenePosjeteVM(vrijeme, count(DISTINCT id) from Posjeta "
				+ "where vrijeme = :datum)";
		Query q = session.createQuery(hql);
		q.setDate("datum", vrijeme);
		OdradjenePosjeteVM posjete = (OdradjenePosjeteVM) q.list().get(0);
		
		hql = "select new ba.unsa.etf.si.tim12.bll.viewmodel.OdradjenePosjeteRowVM(pos.id, pac.imeIPrezime, pos.doktor, pos.vrijeme)"
				+ "from Posjeta pos, Pacijent pac where pac.id = pos.pacijentId and pos.datum = :datum";
		q = session.createQuery(hql);
		q.setDate("datum", vrijeme);
		
		ArrayList<OdradjenePosjeteRowVM> posjeteRed = new ArrayList<OdradjenePosjeteRowVM>(q.list());
		t.commit();
		
		posjete.setOdradjenePosjete(posjeteRed);
		return posjete;
	}

}
