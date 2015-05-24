package ba.unsa.etf.si.tim12.bll.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ba.unsa.etf.si.tim12.bll.viewmodel.FinancijskiUlazVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.OdradjenePosjeteVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PosjetePacijentaVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PotMaterijaliRowVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PotMaterijaliVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.ZahvatiPoDoktoruVM;
import ba.unsa.etf.si.tim12.dal.domainmodel.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim12.dal.HibernateUtil;

public class IzvjestajManagerTest {
	
	private Posjeta p;
	private ObavljeniZahvat o;
	private Materijal m;
	private Pacijent pac;
	private TipZahvata t;
	private UtroseniMaterijal u;
	private Date vrijemeOd, vrijemeDo, vrijeme;
	private String doktor = "Dr.";

	@Before
	public void setUp() throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction trans = session.beginTransaction();
		try{

			vrijeme = new Date();
			
			pac = new Pacijent();
			pac.setImeIPrezime("heheh");
			pac.setOpis("jajaja");
			pac.setDatumRodjenja(vrijeme);
			pac.setTelefon("029383829");
			session.save(pac);
			
			p = new Posjeta();	
			p.setDoktor(doktor);
			p.setPacijentId(pac.getId());
			p.setVrijeme(vrijeme);
			p.setDijagnoza("diijagnozaa");
			session.save(p);
			
		    m = new Materijal();
			m.setCijena(53);
			m.setMjernaJedinica("l");
			m.setNaziv("Med alkohol");
			session.save(m);
			
			t = new TipZahvata();
			t.setCijena(34);
			t.setNaziv("lala");
			t.setOpis("dgdg");
			session.save(t);
			
			o = new ObavljeniZahvat();
			o.setCijena(34);
			o.setPosjetaId(p.getId());
			o.setZahvatId(t.getId());
			session.save(o);
			
			u = new UtroseniMaterijal();
			u.setKolicina(3);
			u.setMaterijalId(m.getId());
			u.setObavljeniZahvatId(o.getId());
			session.save(u);
			
			Calendar date1 = new GregorianCalendar();
			Calendar date2 = new GregorianCalendar();
			date1.setTime(vrijeme);
			date2.setTime(vrijeme);
			date1.set(Calendar.MONTH, date1.get(Calendar.MONTH) - 1);
			date2.set(Calendar.MONTH, date2.get(Calendar.MONTH) + 1);

			vrijemeOd = date1.getTime();
			vrijemeDo = date2.getTime();
			
			trans.commit();
		}
		catch(Exception e)
		{
			trans.rollback();
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
		
	}

	@After
	public void tearDown() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction trans = session.beginTransaction();
		try{
			Posjeta posjeta = (Posjeta) session.get(Posjeta.class, p.getId());
			if(posjeta != null)
				session.delete(posjeta);
			ObavljeniZahvat ozahvat = (ObavljeniZahvat) session.get(ObavljeniZahvat.class, o.getId());
			if(ozahvat != null)
				session.delete(ozahvat);
			Materijal materijal = (Materijal) session.get(Materijal.class, m.getId());
			if(materijal != null)
				session.delete(materijal);
			Pacijent pacijent = (Pacijent) session.get(Pacijent.class, pac.getId());
			if(pacijent != null)
				session.delete(pacijent);
			TipZahvata tip = (TipZahvata) session.get(TipZahvata.class, t.getId());
			if(tip != null)
				session.delete(tip);
			UtroseniMaterijal utroseni = (UtroseniMaterijal) session.get(UtroseniMaterijal.class, u.getId());
			if(utroseni != null)
				session.delete(utroseni);
			
			trans.commit();
		}
		catch(Exception e)
		{
			trans.rollback();
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}
	
	@Test
	public void testSviZahvatiPoDoktoru() throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			IzvjestajManager mngr = new IzvjestajManager(session);
			ZahvatiPoDoktoruVM zahvati = mngr.sviZahvatiPoDoktoru(doktor, vrijemeOd, vrijemeDo);
			assertEquals(Long.valueOf(o.getId()), Long.valueOf(zahvati.getZahvati().get(0).getId()));
		}
		catch(Exception e)
		{
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testFinancijskiUlaz() throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			IzvjestajManager mngr = new IzvjestajManager(session);
			FinancijskiUlazVM rezultat = mngr.financijskiUlaz(vrijemeOd, vrijemeDo);
		    assertEquals(o.getCijena(), rezultat.getUkupnaCijena(), 0.000002);
			assertEquals(Long.valueOf(o.getId()), Long.valueOf(rezultat.getListaFinancijskiUlazRowVM().get(0).getId()));
		}
		catch(Exception e)
		{
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}

	@Test
	public void testPotroseniMaterijali() throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			IzvjestajManager mngr = new IzvjestajManager(session);
			PotMaterijaliVM materijali = mngr.potroseniMaterijali(vrijemeOd, vrijemeDo);
			assertEquals(m.getCijena() * u.getKolicina(), materijali.getUkupnaCijena(), 0.0002);
			assertEquals(Long.valueOf(m.getId()), Long.valueOf(materijali.getMaterijali().get(0).getIdMaterijala()));
		}
		catch(Exception e)
		{
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}

	@Test
	public void testPosjetePacijenta() throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			IzvjestajManager mngr = new IzvjestajManager(session);
			PosjetePacijentaVM posjete = mngr.posjetePacijenta(pac.getId());
			assertEquals(1, posjete.getUkupnoPosjeta());
			assertEquals(p.getDijagnoza(), posjete.getPosjete().get(0).getDijagnoza());
		}
		catch(Exception e)
		{
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}
	@Test
	public void testOdradjenePosjetePoDanu() throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			IzvjestajManager mngr = new IzvjestajManager(session);
			SimpleDateFormat format = new SimpleDateFormat();
			String DateToStr = format.format(vrijeme);
			
			String d = ("Default pattern: " + DateToStr);
			
			
			 

			OdradjenePosjeteVM posjete = mngr.odradjenePosjetePoDanu(vrijeme);
			d = posjete.getVrijeme().toString();
			DateToStr = format.format(posjete.getVrijeme());
			assertEquals(0, posjete.getUkupnoPosjeta());
			//assertEquals(p.getId(), posjete.getOdradjenePosjete().get(0).getId());
		}
		catch(Exception e)
		{
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}

}
