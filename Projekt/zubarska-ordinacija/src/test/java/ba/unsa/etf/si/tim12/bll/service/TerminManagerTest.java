/**
 * 
 */
package ba.unsa.etf.si.tim12.bll.service;

import static org.junit.Assert.*;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim12.TerminNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviTermin;
import ba.unsa.etf.si.tim12.bll.viewmodel.TerminVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;
import ba.unsa.etf.si.tim12.dal.domainmodel.Termin;
import ba.unsa.etf.si.tim12.dal.domainmodel.TipZahvata;


public class TerminManagerTest {
	
	private long id;
	private String doktor = "Dr. Lala";
	private Date vrijeme = new Date();
	private boolean otkazano = false; 
	private List<Termin> dodaniTermini;
	private Pacijent p;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction trans = session.beginTransaction();
		try{

			dodaniTermini = new ArrayList<Termin>();
			Termin t = new Termin();
		
			t.setDoktor(doktor);
			t.setOtkazano(otkazano);
			t.setVrijeme(vrijeme);
			p = new Pacijent();
			p.setDatumRodjenja(new Date());
			p.setImeIPrezime("Lala Lala");
			p.setOpis("lala lala");
			p.setTelefon("061123456");
			
			session.save(p);
			
			t.setPacijentId(p.getId());
			
			session.save(t);
			
			trans.commit();
			
			dodaniTermini.add(t);
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

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction t = session.beginTransaction();
		try{
			Object pac = (Pacijent) session.get(Pacijent.class, p.getId());
			if( pac != null)
			session.delete(pac);
			
			
			for(Termin termin : dodaniTermini){
				
				String hql = "DELETE from Termin t " +
						"WHERE t.id = :Id";
				Query q = session.createQuery(hql);
				q.setLong("Id", termin.getId());
				q.executeUpdate();
			}
			dodaniTermini = null;
			t.commit();
			
		}
		catch(Exception e)
		{
			t.rollback();
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}
	
	@Test
	public void testDodajNoviTermin() throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		try{
			TerminManager mng = new TerminManager(session);
			int num = mng.dajSveTermine().size();
			
			NoviTermin termin = new NoviTermin();
			termin.setDoktor(doktor);
			termin.setPacijentId(p.getId());
			termin.setVrijeme(vrijeme);
			
            
            Termin t = new Termin();
            t.setId(mng.dodajNoviTermin(termin));
            dodaniTermini.add(t);
			
			assertEquals(num + 1, mng.dajSveTermine().size());
			
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(session != null)
				session.close();
		}
	}

	/**
	 * Test method for {@link ba.unsa.etf.si.tim12.bll.service.TerminManager#nadjiPoPacijentu(long)}.
	 */
	@Test
	public void testNadjiPoPacijentu() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		try{
		TerminManager mng = new TerminManager(session);
		ArrayList<TerminVM> termini = mng.nadjiPoPacijentu(p.getId());
		assertEquals(dodaniTermini.get(0).getId(), termini.get(0).getId());
	
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	
	}

	/**
	 * Test method for {@link ba.unsa.etf.si.tim12.bll.service.TerminManager#nadjiPoVremenu(java.util.Date, java.util.Date)}.
	 */
	@Test
	public void testNadjiPoVremenu() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
	try{
			
			Calendar date1 = new GregorianCalendar();
			Calendar date2 = new GregorianCalendar();
			date1.setTime(vrijeme);
			date2.setTime(vrijeme);
			date1.set(Calendar.MONTH, date1.get(Calendar.MONTH) - 1);
			date2.set(Calendar.MONTH, date2.get(Calendar.MONTH) + 1);

			TerminManager mng = new TerminManager(session);
			ArrayList<TerminVM> termini = mng.nadjiPoVremenu(date1.getTime(), date2.getTime());
			System.out.println("Evo ih: " + termini.size());
			assertTrue(!termini.isEmpty());
			assertEquals(dodaniTermini.get(0).getId(), termini.get(0).getId());
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}

	/**
	 * 
	 * Test method for {@link ba.unsa.etf.si.tim12.bll.service.TerminManager#otkaziTermin(long)}.
	 */
	@Test
	public void testOtkaziTerminKojiPostoji() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		try{
		TerminManager mng = new TerminManager(session);
		boolean b = mng.otkaziTermin(dodaniTermini.get(0).getId());
		assertTrue(b);
		Termin ter = (Termin) session.get(Termin.class, dodaniTermini.get(0).getId());
		assertEquals(true,  ter.isOtkazano());
	}
		catch(Exception e){
			throw e;
		}
		finally
		{
			if(session != null)
				session.close();
		}
	}
	
	@Test(expected = TerminNotFound.class)
	public void testOtkaziTerminKojiNePostoji() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		try{
		TerminManager mng = new TerminManager(session);
		mng.otkaziTermin(NadjiSlobodanIDTermina());
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
	
	 private long NadjiSlobodanIDTermina() {
			
			Session sess = HibernateUtil.getSessionFactory().openSession();
			
			Query q = sess.createQuery("SELECT MAX(id) FROM Termin");
			Long max_id = (Long) q.uniqueResult();
		
			sess.close();
			if(max_id == null)
				return 1;
		
			return max_id + 1;
		}


}
