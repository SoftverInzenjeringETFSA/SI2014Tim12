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
		Long max_id = (Long) q.uniqueResult();
		
		sess.close();
		
		if(max_id == null)
			return 1;
		
		return max_id + 1;
	}
	
	@Test
	public void nadjiPoIduTest() {
		//Pronalazenje cijelog ida
		nadjiPoIduPomocna(pac.getId(), "Mora se cijeli ID poklapati.");
		
		//TODO: Napraviti test da provjerava dio ID-a
	}
	
	private void nadjiPoIduPomocna(long id, String message){
		Session sess = HibernateUtil.getSessionFactory().openSession();
		PacijentManager pm = new PacijentManager(sess);
		ArrayList<PacijentVM> l = pm.nadjiPoIdu(pac.getId());
		
		//Trazimo da li je među ovim pacijenitima pronasao i naseg pacijenta
		boolean any = false;
		for (PacijentVM pacijentVM : l) {
			if(pacijentVM.getId() == pac.getId())
				any = true;
		}
		
		assertTrue(message, any);
		
		sess.close();
		
	}
	
	@Test
	public void nadjiPoImenuTest() {
		//Pronalazenje cijelog imena
		nadjiPoImenuPomocna(pac.getImeIPrezime(), "Mora se cijelo ime poklapati.");
		
		//Pronalazenje dijela (pola)imena
		String dioImena = pac.getImeIPrezime();
		dioImena = dioImena.substring(1, dioImena.length()/2);
		nadjiPoImenuPomocna(dioImena, "Mora se dio imena poklapati");
		
		//Pronalazenje cijelog imena gdje ne zavisi od velicine slova
		nadjiPoImenuPomocna(pac.getImeIPrezime().toLowerCase(), "Ne smije zavisiti od velicine slova");
		nadjiPoImenuPomocna(pac.getImeIPrezime().toUpperCase() , "Ne smije zavisiti od velicine slova");
		
		//Pronalazenje dijela (pola)imena gdje ne zavisi o velicine slova
		nadjiPoImenuPomocna(dioImena.toLowerCase(), "Mora se dio imena poklapati i ne smije zavisiti od velicine slova");
		nadjiPoImenuPomocna(dioImena.toUpperCase(), "Mora se dio imena poklapati i ne smije zavisiti od velicine slova");		
	}

	private void nadjiPoImenuPomocna(String ime, String message) {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		PacijentManager pm = new PacijentManager(sess);
		
		ArrayList<PacijentVM> l = pm.nadjiPoImenu(ime);
	
		//Trazimo da li je među ovim pacijenitima pronasao i naseg pacijenta
		boolean any = false;
		for (PacijentVM pacijentVM : l) {
			if(pacijentVM.getId() == pac.getId())
				any = true;
		}
		
		assertTrue(message, any);
		
		sess.close();
	}
	
	@Test
	public void nadjiPoOpisuTest() {
		//Pronalazenje cijelog opisa
		nadjiPoOpisuPomocna(pac.getOpis(), "Mora se cijeli opis poklapati.");
		
		//Pronalazenje dijela (pola)opiwq
		String dioOpisa = pac.getOpis();
		dioOpisa = dioOpisa.substring(1, dioOpisa.length()/2);
		nadjiPoOpisuPomocna(dioOpisa, "Mora se dio opisa poklapati");
		
		//Pronalazenje cijelog opisa gdje ne zavisi od velicine slova
		nadjiPoOpisuPomocna(pac.getOpis().toLowerCase(), "Ne smije zavisiti od velicine slova");
		nadjiPoOpisuPomocna(pac.getOpis().toUpperCase() , "Ne smije zavisiti od velicine slova");
		
		//Pronalazenje dijela (pola)opisa gdje ne zavisi o velicine slova
		nadjiPoOpisuPomocna(dioOpisa.toLowerCase(), "Mora se dio opisa poklapati i ne smije zavisiti od velicine slova");
		nadjiPoOpisuPomocna(dioOpisa.toUpperCase(), "Mora se dio opisa poklapati i ne smije zavisiti od velicine slova");
	}

	private void nadjiPoOpisuPomocna(String opis, String message) {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		PacijentManager pm = new PacijentManager(sess);
		ArrayList<PacijentVM> l = pm.nadjiPoOpisu(opis);
		
		//Trazimo da li je među ovim pacijenitima pronasao i naseg pacijenta
		boolean any = false;
		for (PacijentVM pacijentVM : l) {
			if(pacijentVM.getId() == pac.getId())
				any = true;
		}
		
		assertTrue(message, any);
		
		sess.close();
	}
}
