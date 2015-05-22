package ba.unsa.etf.si.tim12.bll.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim12.bll.viewmodel.ObavljeniZahvatVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.TipZahvataVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.ObavljeniZahvat;
import ba.unsa.etf.si.tim12.dal.domainmodel.Posjeta;

public class ObavljeniZahvatManagerTest {
	private ObavljeniZahvat zahvat;

	@Before
	public void setUp() throws Exception {
		zahvat = new ObavljeniZahvat();
		zahvat.setCijena(10.5);
		zahvat.setPosjetaId((long) 1);
		zahvat.setZahvatId((long) 2);
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		long id = (Long) sess.save(zahvat);
		
		zahvat.setId(id);
		
		t.commit();
		sess.close();
	}

	@After
	public void tearDown() throws Exception {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		sess.delete(zahvat);
		
		t.commit();
		sess.close();
	}

	@Test
	public void testDodajNoviZahvat() {
		
	}

	@Test
	public void testNadjiSvePoTipuZahvata() {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		ObavljeniZahvatManager manager = new ObavljeniZahvatManager(sess);
		ArrayList<ObavljeniZahvatVM> rez = manager.nadjiSvePoTipuZahvata(zahvat.getZahvatId());
		long rezId = rez.get(0).getId();
		long zahId = zahvat.getZahvatId();
		assertEquals(rezId, zahId);
	}

}
