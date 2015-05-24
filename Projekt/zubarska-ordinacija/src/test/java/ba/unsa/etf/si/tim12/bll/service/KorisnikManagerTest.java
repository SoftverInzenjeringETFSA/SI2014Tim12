package ba.unsa.etf.si.tim12.bll.service;

import java.sql.Connection;
import java.sql.SQLException;

import ba.unsa.etf.si.tim12.bll.viewmodel.LoginVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.PromjenaPasswordaVM;
import ba.unsa.etf.si.tim12.dal.domainmodel.Korisnik;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KorisnikManagerTest {

	private Korisnik korisnik;
	String korisnikPass;
	@Before
	public void setUp() throws Exception {
		korisnik = new Korisnik();
		korisnik.setUsername("testUser" + NadjiSlobodanID());
		korisnikPass = "testPassword123";
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		KorisnikManager kManager = new KorisnikManager(sess);
		korisnik.setPassword(kManager.HashPassword(korisnikPass));
		
		Transaction t = sess.beginTransaction();
		sess.save(korisnik);
		t.commit();

		sess.close();
	}

	@After
	public void tearDown() throws Exception {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = sess.beginTransaction();
		sess.delete(korisnik);
		t.commit();
		
		sess.close();
	}

	@Test
	public void ProvjeriPasswordSIspravnimUsernameomIPasswordom() throws Exception {
		Session sess = null;
		
		LoginVM vm = new LoginVM();
		vm.setUsername(korisnik.getUsername());
		vm.setPassword(korisnikPass);
		
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			KorisnikManager kManager = new KorisnikManager(sess);
			
			boolean result = kManager.provjeriPassword(vm);
			
			assertEquals("Korsnik bi se trebao moci prijaviti.",true, result);
			
		} catch(Exception e){
			e.printStackTrace();
			//u GUIu ne bi proslijedili nego bi ne�to uradili s njim
			throw e;
		} finally {
			if(sess != null)
				sess.close();
		}
		
	}
	
	@Test
	public void ProvjeriPasswordSIspravnimUsernameomPogresnimPasswordom() throws Exception {
		Session sess = null;
		
		LoginVM vm = new LoginVM();
		vm.setUsername(korisnik.getUsername());
		vm.setPassword(korisnik.getPassword() + "greska");
		
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			KorisnikManager kManager = new KorisnikManager(sess);
			
			boolean result = kManager.provjeriPassword(vm);
			
			assertEquals("Korsnikov password ne postoji.", false, result);
			
		} catch(Exception e){
			e.printStackTrace();
			//u GUIu ne bi proslijedili nego bi ne�to uradili s njim
			throw e;
		} finally {
			if(sess != null)
				sess.close();
		}
		
	}
	
	@Test
	public void ProvjeriPasswordSPogresnimPassworom() throws Exception {
		Session sess = null;
		
		LoginVM vm = new LoginVM();
		vm.setUsername(korisnik.getUsername()+"Greska");
		vm.setPassword(korisnik.getPassword());
		
		
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			
			KorisnikManager kManager = new KorisnikManager(sess);
			
			boolean result = kManager.provjeriPassword(vm);
			
			assertEquals("Korsnikov username ne postoji.", false, result);
			
		} catch(Exception e){
			e.printStackTrace();
			//u GUIu ne bi proslijedili nego bi ne�to uradili s njim
			throw e;
		} finally {
			if(sess != null)
				sess.close();
		}
		
	}
	
	@Test
	public void promijeniPasswordNeispravanPonovljeniPassword() throws Exception{
		
		PromjenaPasswordaVM model = new PromjenaPasswordaVM();
		model.setUsername(korisnik.getUsername());
		model.setStariPass(korisnik.getPassword());
		model.setNoviPass("noviPassword");
		model.setPonovoNoviPass(model.getNoviPass() + "greska");
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		KorisnikManager kManager = new KorisnikManager(sess);
		int rezultat = kManager.promjeniPassword(model);
		assertTrue(rezultat != 0);
		
		sess.close();
	}
	
	@Test
	public void promijeniPasswordNeispravanStariPassword() throws Exception{
		
		PromjenaPasswordaVM model = new PromjenaPasswordaVM();
		model.setUsername(korisnik.getUsername());
		model.setStariPass(korisnik.getPassword() + "greska");
		model.setNoviPass("noviPassword");
		model.setPonovoNoviPass(model.getNoviPass());
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		KorisnikManager kManager = new KorisnikManager(sess);
		int rezultat = kManager.promjeniPassword(model);
		assertTrue(rezultat != 0);
		
		sess.close();
	}
	
	@Test
	public void promijeniPasswordNeispravanUserName() throws Exception{
		
		PromjenaPasswordaVM model = new PromjenaPasswordaVM();
		model.setUsername(korisnik.getUsername() + "greska");
		model.setStariPass(korisnik.getPassword());
		model.setNoviPass("noviPassword");
		model.setPonovoNoviPass(model.getNoviPass());
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		KorisnikManager kManager = new KorisnikManager(sess);
		int rezultat = kManager.promjeniPassword(model);
		assertTrue(rezultat != 0);
		
		sess.close();
	}
	
	@Test
	public void promijeniPasswordIspravanMode() throws Exception{
		
		PromjenaPasswordaVM model = new PromjenaPasswordaVM();
		model.setUsername(korisnik.getUsername());
		model.setStariPass(korisnikPass);
		model.setNoviPass("noviPassword");
		model.setPonovoNoviPass(model.getNoviPass());
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		KorisnikManager kManager = new KorisnikManager(sess);
		int rezultat = kManager.promjeniPassword(model);
		assertTrue(rezultat == 0);
		
		Transaction t = sess.beginTransaction();
		
		String hql = "FROM Korisnik WHERE username = :username"; 
		Query q =  sess.createQuery(hql);
		q.setParameter("username", korisnik.getUsername());
		
		Korisnik k = (Korisnik) q.uniqueResult();
		
		t.commit();
		
		if(k == null){
			fail("Nema korisnika s usernameom. ");
		}
		
		assertEquals("Password nije promijenjen", kManager.HashPassword(model.getNoviPass()), k.getPassword());
		
		sess.close();
	}
	
	
	private long NadjiSlobodanID() {
	
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Query q = sess.createQuery("SELECT MAX(id) FROM Korisnik");
		Long max_id = (Long) q.uniqueResult();
	
		sess.close();
		if(max_id == null)
			return 1;
	
		return max_id + 1;
	}
}
