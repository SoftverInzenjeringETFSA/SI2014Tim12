package ba.unsa.etf.si.tim12.bll.service;
import java.util.ArrayList;

import ba.unsa.etf.si.tim12.bll.viewmodel.ObavljeniZahvatVM;


import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.ObavljeniZahvat;
import ba.unsa.etf.si.tim12.dal.domainmodel.TipZahvata;

public class ObavljeniZahvatManagerTest {
	private ObavljeniZahvat z;
	private TipZahvata t;

	@Before
	public void setUp() throws Exception {
		   Session session = HibernateUtil.getSessionFactory().openSession();
		   Transaction trans = session.beginTransaction();
		   try{
			   t = new TipZahvata();
			   t.setCijena(34);
			   t.setNaziv("Vadjenje zuba");
			   t.setOpis("");
			   session.save(t);
			   
			   z = new ObavljeniZahvat();
			   z.setCijena(34);
			   z.setPosjetaId(0L);
			   z.setZahvatId(t.getId());
			   session.save(z);
			   
			   trans.commit();
		   }
		   catch(Exception e)
		   {
			   trans.rollback();
			   throw e;
		   }
		   finally
		   {
			   if(session != null)
				   session.close();
		   }
	}

	@After
	public void tearDown() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		   Transaction trans = session.beginTransaction();
		   try{
			   t = (TipZahvata) session.get(TipZahvata.class, t.getId());
			   if(t != null)
				   session.delete(t);
			   
			   z = (ObavljeniZahvat) session.get(ObavljeniZahvat.class, z.getId());
			   if(z != null)
				   session.delete(z);
			   
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
	public void testNadjiSvePoTipuZahvata() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			ObavljeniZahvatManager mng = new ObavljeniZahvatManager(session);
			ArrayList<ObavljeniZahvatVM> zahvati = mng.nadjiSvePoTipuZahvata(t.getId());
			assertEquals(Long.valueOf(z.getId()), Long.valueOf(zahvati.get(0).getId()));
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
