package ba.unsa.etf.si.tim12.bll.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ba.unsa.etf.si.tim12.PacijentNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.LoginVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PrikazPacijentaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.*;
import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PacijentManagerTest {

	Pacijent pac;
	
	@Before
	public void setUp() throws Exception {
		pac = new Pacijent();
		pac.setImeIPrezime("Luka Pejovic");
		Calendar c = new GregorianCalendar(1993,12,16);
		pac.setDatumRodjenja(c.getTime());
		pac.setTelefon("063/333-333");
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		sess.save(pac);
		
		t.commit();
		sess.close();
	}

	@After
	public void tearDown() throws Exception {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		sess.delete(pac);
		
		t.commit();
		sess.close();
	}

	@Test
	public void testDajPacijenta() throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			PacijentManager pManager = new PacijentManager(sess);
			
			PrikazPacijentaVM vm = pManager.dajPacijenta(pac.getId());
			
			assertNotNull(vm);
			assertEquals(pac.getId(), vm.getId());
			assertEquals(pac.getImeIPrezime(), vm.getImeIPrezime());
			assertEquals(pac.getDatumRodjenja(), vm.getDatumRodjenja());
			assertEquals(pac.getTelefon(), vm.getBrojTelefona());
			assertEquals(pac.getOpis(), vm.getOpis());
			
			assertNotNull(vm.getPosjete());
			assertNotNull(vm.getTermini());
			
		} catch(PacijentNotFound e){ 
			e.printStackTrace();
			//u GUI-u ne bi proslijedili nego bi nešto uradili s njim
			throw e;
		} catch(Exception e){
			e.printStackTrace();
			//u GUIu ne bi proslijedili nego bi nešto uradili s njim
			throw e;
		} finally {
			if(sess != null)
				sess.close();
		}
	}

}
