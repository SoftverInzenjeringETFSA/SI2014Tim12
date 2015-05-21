/**
 * 
 */
package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.Materijal;
import ba.unsa.etf.si.tim12.dal.domainmodel.Posjeta;
import ba.unsa.etf.si.tim12.dal.domainmodel.ObavljeniZahvat;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;
import ba.unsa.etf.si.tim12.dal.domainmodel.TipZahvata;
import ba.unsa.etf.si.tim12.dal.domainmodel.Materijal;







import java.util.*;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PosjetaManagerTest {
	
	String dijagnoza = "Paradentoza";
	String doktor = "Dr. Mujo";
	long pacijentId;
	Date vrijeme;
	Pacijent pacijent;
	ArrayList<Posjeta> dodanePosjete;
	ArrayList<TipZahvata> dodaniTipoviZahvata;
	ArrayList<Materijal> dodaniMaterijali; 
	/*
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession(); 
		Transaction t = session.beginTransaction();
		dodanePosjete = new ArrayList<Posjeta>();
		dodaniTipoviZahvata = new ArrayList<TipZahvata>();
		dodaniMaterijali = new ArrayList<Materijal>();
			
			Posjeta p = new Posjeta();	
			vrijeme = new Date();
			
			pacijent = new Pacijent();
			session.save(pacijent);
			pacijentId = pacijent.getId();
			
			
			p.setDijagnoza(dijagnoza);
			p.setDoktor(doktor);
			p.setPacijentId(pacijentId);
			p.setVrijeme(vrijeme);
			
			session.save(p);
			
			dodanePosjete.add(p);
			
			t.commit();
			
			session.close();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		sess.delete(pacijent);
		
		String hql;
		Query q;
		
	for(Posjeta p : dodanePosjete){
			
			//We have to clean the database, delete every "Posjeta" that we added in our previous test
			//We first need to delete all the dependencies also newly added in our tests			
			
		/*	String hql2 = "(Select m.id from UtroseniMaterijal m "
					+ "INNER JOIN ObavljeniZahvat z ON z.ud = m.zahvatId"
					+ "WHERE z.posjetaId = :Id)";
			
			hql = "DELETE UtroseniMaterijal m "
					+ "WHERE m.id IN "
					+ hql2;
			
			q = sess.createQuery(hql);
			q.setLong("Id", p.getId());
		    q.executeUpdate();
		    */
		    
		    hql = "DELETE ObavljeniZahvat z "
		    		+ "WHERE z.posjetaId = :Id";

			q = sess.createQuery(hql);
			q.setLong("Id", p.getId());
		    q.executeUpdate();
		    
			hql = "DELETE Posjeta p " +
					"WHERE p.id = :posjetaId";
			q = sess.createQuery(hql);
			q.setLong("posjetaId", p.getId());
			q.executeUpdate();
			
			
			
		}
	
	for(TipZahvata z : dodaniTipoviZahvata){
		
		hql = "DELETE TipZahvata t " +
				"WHERE t.id = :Id";
		q = sess.createQuery(hql);
		q.setLong("Id", z.getId());
		q.executeUpdate();
	}
	
    for(Materijal m : dodaniMaterijali){
		
		hql = "DELETE Materijal m " +
				"WHERE m.id = :Id";
		q = sess.createQuery(hql);
		q.setLong("Id", m.getId());
		q.executeUpdate();
	}
		
		t.commit();
		sess.close();
		
	}


	/**
	 * Test method for {@link ba.unsa.etf.si.tim12.bll.service.PosjetaManager#nadjiPoPacijentu(long)}.
	 * @throws Exception 
	 */
	@Test
	public void testNadjiPoPacijentuPostojiPosjeta() throws Exception {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			
			PosjetaManager mngr = new PosjetaManager(session);
			ArrayList<PosjetaVM> res = mngr.nadjiPoPacijentu(pacijent.getId());
			assertEquals(res.size(), dodanePosjete.size());
			int num = 0;
			for(PosjetaVM p : res)
			{
				assertEquals(p.getId(), dodanePosjete.get(num).getId());
				num++;
			}
		}
		catch(Exception e){
		    throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
		
		}
	
	@Test
	public void testNadjiPoPacijentuNePostojiPosjeta() throws Exception
	{
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			
			PosjetaManager mngr = new PosjetaManager(session);
			ArrayList<PosjetaVM> res = mngr.nadjiPoPacijentu(NadjiSlobodanIDPacijenta());
			assertTrue(res.isEmpty());
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
	 * Test method for {@link ba.unsa.etf.si.tim12.bll.service.PosjetaManager#dodajNovuPosjetu(ba.unsa.etf.si.tim12.bll.viewmodel.NovaPosjetaVM)}.
	 */
	@Test
	public void testDodajNovuPosjetu() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ba.unsa.etf.si.tim12.bll.service.PosjetaManager#nadjiPoDijagnozi(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testNadjiPoDijagnoziPostojiPosjeta() throws Exception {

		Session sess = null;
		try{
		sess = HibernateUtil.getSessionFactory().openSession();
		PosjetaManager pm = new PosjetaManager(sess);
		
		ArrayList<PosjetaVM> l = pm.nadjiPoDijagnozi(dodanePosjete.get(0).getDijagnoza());
		assertTrue(!l.isEmpty());
		
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(sess != null)
				sess.close();
		}
	}
	
	private long NadjiSlobodanIDPosjete() {
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Query q = sess.createQuery("SELECT MAX(id) FROM Posjeta");
		Long max_id = (Long) q.uniqueResult();
	
		sess.close();
		if(max_id == null)
			return 1;
	
		return max_id + 1;
	}
    private long NadjiSlobodanIDPacijenta() {
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Query q = sess.createQuery("SELECT MAX(id) FROM Pacijent");
		Long max_id = (Long) q.uniqueResult();
	
		sess.close();
		if(max_id == null)
			return 1;
	
		return max_id + 1;
	}

}
