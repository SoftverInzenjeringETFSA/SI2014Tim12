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



/**
 * @author Sumejja, Emina
 *
 */
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
		try{
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
			
			Materijal m = new Materijal();
			m.setCijena(53);
			m.setMjernaJedinica("l");
			m.setNaziv("Med alkohol");
			
			session.save(m);
			
			dodaniMaterijali.add(m);
			
			TipZahvata z = new TipZahvata();
			z.setCijena(34);
			z.setNaziv("lala");
			z.setOpis("dgdg");
			
			session.save(z);
			
			dodaniTipoviZahvata.add(z);
			
			t.commit();
			
			session.close();
		}
		catch(Exception e)
		{
			t.rollback();
			if(session.isOpen())
				session.close();
			throw e;
		}
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		try{
		
		sess.delete(pacijent);
		
		String hql;
		Query q;
		
	for(Posjeta p : dodanePosjete){
			
			//We have to clean the database, delete every "Posjeta" that we added in our previous test
			//We first need to delete all the dependencies also newly added in our tests			
			
			/*String hql2 = "Select m.id from UtroseniMaterijal m, ObavljeniZahvat z "
					+ "WHERE z.id = m.obavljeniZahvatId AND z.posjetaId = :Id";

			Query q2 = sess.createQuery(hql2);
			q2.setLong("Id", p.getId());
			List<Long> ids = q2.list();
			
			hql = "DELETE UtroseniMaterijal m "
					+ "WHERE m.id IN (:lista)";
			
			q = sess.createQuery(hql);
			q.setParameterList("lista", ids);
		    q.executeUpdate();*/
		
		    String hql2 = "DELETE FROM UtroseniMaterijal m "
		    		+ "WHERE exists( SELECT 1 FROM ObavljeniZahvat z "
		    		+ "WHERE z.id = m.obavljeniZahvatId "
		    		+ "and z.posjetaId = :Id )";

			Query q2 = sess.createQuery(hql2);
			q2.setLong("Id", p.getId());
		    
		    
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
		catch(Exception e)
		{
			t.rollback();
			if(sess.isOpen())
				sess.close();
			throw e;
		}
		finally
		{
			
		}
		
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
	public void testDodajNovuPosjetu() throws Exception {

		Session session =  HibernateUtil.getSessionFactory().openSession();
			
		try{
			PosjetaManager mngr = new PosjetaManager(session);
			int num = mngr.dajSvePosjete().size();
		
		NoviOZahvatMaterijalVM m = new NoviOZahvatMaterijalVM();
		m.setMaterijalId(dodaniMaterijali.get(0).getId());
		m.setKolicina(3);
		ArrayList<NoviOZahvatMaterijalVM> materijali = new ArrayList<NoviOZahvatMaterijalVM>();
		materijali.add(m);
		
		NoviObavljeniZahvatVM z = new NoviObavljeniZahvatVM();
		z.setCijena(343);
		z.setZahvatId(dodaniTipoviZahvata.get(0).getId());
		z.setMaterijali(materijali);
		ArrayList<NoviObavljeniZahvatVM> obavljeniZahvati = new ArrayList<NoviObavljeniZahvatVM>();
		obavljeniZahvati.add(z);
		
		NovaPosjetaVM p = new NovaPosjetaVM();
		p.setObavljeniZahvati(obavljeniZahvati);
		p.setDijagnoza(dijagnoza);
		p.setDoktor(doktor);
		p.setPacijentId(pacijentId);
		p.setVrijeme(vrijeme);
		
		Long id = mngr.dodajNovuPosjetu(p);
		
		
		Posjeta posjet = new Posjeta();
		posjet.setId(id);
		posjet.setPacijentId(pacijentId);
		
		dodanePosjete.add(posjet);
		
		assertEquals(num + 1, mngr.dajSvePosjete().size());
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
	
	@Test
	public void testNadjiPoDijagnoziNePostojiPosjeta() throws Exception {

		Session sess = null;
		try{
		sess = HibernateUtil.getSessionFactory().openSession();
		PosjetaManager pm = new PosjetaManager(sess);
		
		ArrayList<PosjetaVM> l = pm.nadjiPoDijagnozi("lalala12345");
		assertTrue(l.isEmpty());
		
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
