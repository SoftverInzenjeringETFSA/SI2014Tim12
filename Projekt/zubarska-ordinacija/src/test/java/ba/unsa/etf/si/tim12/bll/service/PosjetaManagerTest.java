package ba.unsa.etf.si.tim12.bll.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PosjetaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.Pacijent;
import ba.unsa.etf.si.tim12.dal.domainmodel.Posjeta;

public class PosjetaManagerTest {
	Posjeta posjeta;
	
	@Before
	public void setUp() throws Exception {
		posjeta = new Posjeta();
		posjeta.setDijagnoza("ludilo");
		Calendar c = new GregorianCalendar(1993,12,16);
		posjeta.setVrijeme(c.getTime());
		posjeta.setDoktor("xxxx");
		posjeta.setPacijentId(1);
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		long id = (Long) sess.save(posjeta);
		
		posjeta.setId(id);
		
		t.commit();
		sess.close();
	}

	@After
	public void tearDown() throws Exception {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		sess.delete(posjeta);
		
		t.commit();
		sess.close();
	}

	@Test
	public void testNadjiPoPacijentu() {
		fail("Not yet implemented");
	}

	@Test
	public void testDodajNovuPosjetu() {
		fail("Not yet implemented");
	}

	@Test
	public void testNadjiPoDijagnozi() {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		PosjetaManager pm = new PosjetaManager(sess);
		ArrayList<PosjetaVM> l = pm.nadjiPoDijagnozi(posjeta.getDijagnoza());
		PosjetaVM vm = new PosjetaVM();
		vm.setDijagnoza(posjeta.getDijagnoza());
		vm.setDoktor(posjeta.getDoktor());
		vm.setId(posjeta.getId());
		vm.setPacijenti(posjeta.getPacijentId());
		vm.setVrijeme(posjeta.getVrijeme());
		assertEquals(vm.getDijagnoza(), l.get(0).getDijagnoza());
		}

}
