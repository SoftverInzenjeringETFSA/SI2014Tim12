package ba.unsa.etf.si.tim12.bll.service;

import java.util.ArrayList;

import ba.unsa.etf.si.tim12.MaterijalNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM;
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

	@Before
	public void setUp() throws Exception {
		materijal = new Materijal();
		materijal.setNaziv("nazivMaterijala");
		materijal.setCijena(24.55);
		materijal.setMjernaJedinica("gram");
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = sess.beginTransaction();

		long id = (Long) sess.save(materijal);
		
		materijal.setId(id);
		
		
		t.commit();

		sess.close();
	}

	@After
	public void tearDown() throws Exception {
Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = sess.beginTransaction();
		sess.delete(materijal);
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
			MaterijalVM m = new MaterijalVM();
			m.setNaziv(materijal.getNaziv());
			m.setCijena(materijal.getCijena());
			m.setMjernaJedinica(materijal.getMjernaJedinica());
			m.setId(materijal.getId());
			
			assertEquals(m.getNaziv() , l.get(0).getNaziv());
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	@Test(expected = MaterijalNotFound.class)
	public void NadjiPoImenuMaterijalNePostoji()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			
			ArrayList<MaterijalVM> l = mManager.nadjiPoImenu("aaaaa");
						
			
		}
		catch(MaterijalNotFound e){
			e.printStackTrace();
			throw e;
			
		} 
		catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}
	
	//@Test
	public void nadjiPoTipuZahvata()  throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			MaterijaliManager mManager = new MaterijaliManager(sess);
			
			UtroseniMaterijal u = new UtroseniMaterijal();
			u.setMaterijalId(5);
			u.setObavljeniZahvatId(6);
			ObavljeniZahvat o = new ObavljeniZahvat();
			o.setId(6);
			o.setZahvatId(7);
			ArrayList<MaterijalVM> l = mManager.nadjiPoTipuZahvata(7);

			MaterijalVM m = new MaterijalVM();
			m.setNaziv(materijal.getNaziv());
			m.setCijena(materijal.getCijena());
			m.setMjernaJedinica(materijal.getMjernaJedinica());
			m.setId(7);
			
			assertEquals(m.getId() , l.get(0).getNaziv());
			
			
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
			
			Materijal m = new Materijal();
			m.setNaziv(materijal.getNaziv());
			m.setCijena(materijal.getCijena());
			m.setMjernaJedinica(materijal.getMjernaJedinica());
			m.setId(materijal.getId());
			//ArrayList<Materijal> l = new ArrayList<Materijal>();
			//l.add(0,m);
			sess.save(m);
			boolean x = mManager.izbrisiMaterijal(m.getId());

			assertEquals(true , x);
			
			
		} catch(Exception e){
			e.printStackTrace();
			throw e;
			
		} finally {
			if(sess != null)
				sess.close();
		}

	}

}
