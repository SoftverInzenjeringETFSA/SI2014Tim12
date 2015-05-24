package ba.unsa.etf.si.tim12.bll.service;

import java.util.ArrayList;

import ba.unsa.etf.si.tim12.MaterijalNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataMaterijalVM;
import ba.unsa.etf.si.tim12.dal.domainmodel.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MaterijaliManagerTest {

	private Materijal materijal; 
	private UtroseniMaterijal u;
	private ObavljeniZahvat o;
	private TipZahvata z;
	private MaterijalTipZahvata mtz;
	
	@Before
	public void setUp() throws Exception {
        Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = sess.beginTransaction();

		
		try{
		materijal = new Materijal();
		materijal.setNaziv("nazivMaterijala");
		materijal.setCijena(24.55);
		materijal.setMjernaJedinica("gram");
		long id = (Long) sess.save(materijal);
		
		materijal.setId(id);
		
		z = new TipZahvata();
		z.setNaziv("lalal");
		sess.save(z);
		
		o = new ObavljeniZahvat();
		o.setZahvatId(z.getId());
		sess.save(o);
		
		u = new UtroseniMaterijal();
		u.setKolicina(5);
		u.setMaterijalId(materijal.getId());
		u.setObavljeniZahvatId(o.getId());
		sess.save(u);

		mtz = new MaterijalTipZahvata();
		mtz.setMaterijalId(materijal.getId());
		mtz.setTipZahvataId(z.getId());
		mtz.setKolicina(3.33);
		sess.save(mtz);
		
		t.commit();

		sess.close();
		}
		catch(Exception e)
		{
			t.rollback();
			throw e;
		}
		finally
		{
			if(sess != null && sess.isOpen())
				sess.close();
		}
	}

	@After
	public void tearDown() throws Exception {
Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = sess.beginTransaction();
		if(materijal != null)
		sess.delete(materijal);
		sess.delete(u);
		sess.delete(o);
		sess.delete(z);
		t.commit();
		
		sess.close();
	}

	@Test
	public void NadjiPoImenuMaterijal()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			
			ArrayList<MaterijalVM> l = mManager.nadjiPoImenu(materijal.getNaziv());
				
			assertEquals(materijal.getNaziv() , l.get(0).getNaziv());
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	@Test
	public void NadjiPoImenuMaterijalNePostoji()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			
			ArrayList<MaterijalVM> l = mManager.nadjiPoImenu("aaaaa");
			assertTrue(l.isEmpty());
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	@Test
	public void nadjiPoTipuZahvata()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			
			ArrayList<TipZahvataMaterijalVM> l = mManager.nadjiPoTipuZahvata(z.getId());

			assertEquals(materijal.getNaziv() , l.get(0).getMaterijalIme());
			assertEquals(mtz.getKolicina(), l.get(0).getKolicina(), 0.00001);
			assertEquals(z.getId(), l.get(0).getTipZahvataId());
			assertEquals(materijal.getId(), l.get(0).getMaterijalId());
			assertEquals(materijal.getMjernaJedinica(), l.get(0).getMjernaJedinica());
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	@Test
	public void nadjiPoTipuZahvataNePostoji()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			ArrayList<TipZahvataMaterijalVM> l = mManager.nadjiPoTipuZahvata(NadjiSlobodanIDTipaZahvata());	
			
			assertTrue(l.isEmpty());
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	@Test
	public void IzbrisiMaterijal()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			
			boolean x = mManager.izbrisiMaterijal(materijal.getId());
			materijal = null;
			
			assertEquals(true , x);
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	@Test
	public void IzbrisiMaterijalNePostoji()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			
			boolean x = mManager.izbrisiMaterijal(NadjiSlobodanIDMaterijala());

			assertFalse(x);
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	@Test
	public void nadjiPoObavljenomZahvatu()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();

			MaterijaliManager mManager = new MaterijaliManager(sess);
			
			ArrayList<MaterijalVM> l = mManager.nadjiPoObavljenomZahvatu(o.getId());

			
			
			assertEquals(materijal.getNaziv() , l.get(0).getNaziv());
			
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	@Test
	public void nadjiPoObavljenomZahvatuNePostoji()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			ArrayList<MaterijalVM> l = mManager.nadjiPoObavljenomZahvatu(NadjiSlobodanIDOZahvata());	
			
			assertTrue(l.isEmpty());
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	 private long NadjiSlobodanIDTipaZahvata() {
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			
			Query q = sess.createQuery("SELECT MAX(id) FROM TipZahvata");
			Long max_id = (Long) q.uniqueResult();
		
			sess.close();
			if(max_id == null)
				return 1;
		
			return max_id + 1;
		}
		
	 private long NadjiSlobodanIDMaterijala() {
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			
			Query q = sess.createQuery("SELECT MAX(id) FROM Materijal");
			Long max_id = (Long) q.uniqueResult();
		
			sess.close();
			if(max_id == null)
				return 1;
		
			return max_id + 1;
		}
		
	 private long NadjiSlobodanIDOZahvata() {
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			
			Query q = sess.createQuery("SELECT MAX(id) FROM ObavljeniZahvat");
			Long max_id = (Long) q.uniqueResult();
		
			sess.close();
			if(max_id == null)
				return 1;
		
			return max_id + 1;
		}


}
