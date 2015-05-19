package ba.unsa.etf.si.tim12.bll.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ba.unsa.etf.si.tim12.PacijentNotFound;
import ba.unsa.etf.si.tim12.bll.viewmodel.LoginVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PacijentVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PrikazPacijentaVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.*;
import static org.junit.Assert.*;

import org.hibernate.Query;
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
		pac.setOpis("lala");
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		long id = (Long) sess.save(pac);
		
		pac.setId(id);
		
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
	public void testDajPacijentaPostojiPacijentBezTerminaiPosjeta() throws Exception {
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
	
	@Test(expected = PacijentNotFound.class)
	public void testDajPacijentaNePostojiPacijent() throws Exception {
		Session sess = null;
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			PacijentManager pManager = new PacijentManager(sess);
			
			long id = NadjiSlobodanID();
			pManager.dajPacijenta(id);
			
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

	private long NadjiSlobodanID() {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Query q = sess.createQuery("SELECT MAX(id) FROM Pacijent");
		long max_id = (Long) q.uniqueResult();
		
		sess.close();
		
		return max_id + 1;
	}
	
	@Test
	public void nadjiPoIduTest() {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		PacijentManager pm = new PacijentManager(sess);
		ArrayList<PacijentVM> l = pm.nadjiPoIdu(pac.getId());
		PacijentVM vm = new PacijentVM();
		vm.setBrojTelefona(pac.getTelefon());
		vm.setDatumRodjenja(pac.getDatumRodjenja());
		vm.setId(pac.getId());
		vm.setImePrezime(pac.getImeIPrezime());
		vm.setOpis(pac.getOpis());
		assertEquals(vm.getId(), l.get(0).getId());
	}
	
	@Test
	public void nadjiPoImenuTest() {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		PacijentManager pm = new PacijentManager(sess);
		ArrayList<PacijentVM> l = pm.nadjiPoImenu(pac.getImeIPrezime());
		PacijentVM vm = new PacijentVM();
		vm.setBrojTelefona(pac.getTelefon());
		vm.setDatumRodjenja(pac.getDatumRodjenja());
		vm.setId(pac.getId());
		vm.setImePrezime(pac.getImeIPrezime());
		vm.setOpis(pac.getOpis());
		assertEquals(vm.getImePrezime(), l.get(0).getImePrezime());
	}
	
	@Test
	public void nadjiPoOpisuTest() {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		PacijentManager pm = new PacijentManager(sess);
		ArrayList<PacijentVM> l = pm.nadjiPoOpisu(pac.getOpis());
		PacijentVM vm = new PacijentVM();
		vm.setBrojTelefona(pac.getTelefon());
		vm.setDatumRodjenja(pac.getDatumRodjenja());
		vm.setId(pac.getId());
		vm.setImePrezime(pac.getImeIPrezime());
		vm.setOpis(pac.getOpis());
		assertEquals(vm.getOpis(), l.get(0).getOpis());		
	}
}
