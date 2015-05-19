package ba.unsa.etf.si.tim12.bll.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim12.bll.viewmodel.NoviTipZahvataMaterijalVM;
import ba.unsa.etf.si.tim12.bll.viewmodel.NoviTipZahvataVM;
import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.Materijal;
import ba.unsa.etf.si.tim12.dal.domainmodel.MaterijalTipZahvata;

public class TipZahvataManagerTest {

	NoviTipZahvataVM vm;
	ArrayList<NoviTipZahvataMaterijalVM> mvms;
	ArrayList<Materijal> ms;
	
	@Before
	public void setUp() throws Exception {
		vm = new NoviTipZahvataVM();
		vm.setNaziv("testTipZahvat1");
		vm.setCijena(3.33);
		
		mvms = new ArrayList<NoviTipZahvataMaterijalVM>();
		ms = new ArrayList<Materijal>();
		
		Materijal temp = new Materijal();
		temp.setNaziv("testNaziv1");
		temp.setMjernaJedinica("testMJ1");
		temp.setCijena(3.33);
		
		ms.add(temp);
		
		temp = new Materijal();
		temp.setNaziv("testNaziv2");
		temp.setMjernaJedinica("testMJ2");
		temp.setCijena(4.44);
		
		ms.add(temp);
		
		temp = new Materijal();
		temp.setNaziv("testNaziv3");
		temp.setMjernaJedinica("testMJ3");
		temp.setCijena(2.44);
		
		ms.add(temp);
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sess.beginTransaction();
		
		for (Materijal materijal : ms) {
			sess.save(materijal);
			
			NoviTipZahvataMaterijalVM mvm = new NoviTipZahvataMaterijalVM();
			mvm.setMaterijalId(materijal.getId());
			mvm.setKolicina(3);
			
			mvms.add(mvm);
		}
		
		t.commit();
		sess.close();
	}

	@After
	public void tearDown() throws Exception {
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		Transaction t = sess.beginTransaction();
				
		String hql = "SELECT id FROM TipZahvata t " +
				"WHERE t.naziv = :naziv";
		Query q = sess.createQuery(hql);
		q.setString("naziv", vm.getNaziv());
		Long id = (Long) q.uniqueResult();
		
		if(id != null){
			hql = "DELETE FROM MaterijalTipZahvata m " +
					"WHERE m.tipZahvataId = :zahvatId";
			q = sess.createQuery(hql);
			q.setLong("zahvatId", id);
			q.executeUpdate();
			
			hql = "DELETE TipZahvata t " +
					"WHERE t.id = :zahvatId";
			q = sess.createQuery(hql);
			q.setLong("zahvatId", id);
			q.executeUpdate();
		}
		for(Materijal m : ms){
			
			hql = "DELETE Materijal m " +
					"WHERE m.id = :materijalId";
			q = sess.createQuery(hql);
			q.setLong("materijalId", m.getId());
			q.executeUpdate();
		}
		
		t.commit();
		
		sess.close();
	}

	@Test
	public void dodajNoviTipBezmaterijala() {
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		TipZahvataManager tzmManager = new TipZahvataManager(sess);
		boolean result = tzmManager.dodajNoviTip(vm);
		assertTrue(result);
		
		
		sess.close();
	}
	
	@Test
	public void dodajNoviTipSMaterijalima(){
		
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		long broja_zahvata = dajBrojTipZahvata(sess);
		long broj_materijala = dajBrojMaterijalaTipZahvata(sess);
		
		TipZahvataManager tzmManager = new TipZahvataManager(sess);
		
		vm.setMaterijali(mvms);
		boolean result = tzmManager.dodajNoviTip(vm);
		assertTrue(result);
		
		assertEquals("Broj tipova zahvata mora se uvecat",
				broja_zahvata + 1, dajBrojTipZahvata(sess));
		assertEquals("Broj matrijalTipZahvata mora se uvecati",
				broj_materijala + mvms.size(), dajBrojMaterijalaTipZahvata(sess));
		MaterijalTipZahvata n = new MaterijalTipZahvata();
		
		String hql = "SELECT id FROM TipZahvata t WHERE t.naziv = :naziv";
		Query q = sess.createQuery(hql);
		q.setString("naziv", vm.getNaziv());
		
		Long id = (Long) q.uniqueResult();
		assertNotNull("TipZahvata mora biti pronadjen",id);
		
		hql = "SELECT materijalId FROM MaterijalTipZahvata m " +
				"WHERE m.tipZahvataId = :tipZahvataId";
		q = sess.createQuery(hql);
		q.setLong("tipZahvataId", id);
			
		ArrayList<Long> mvms_mids = new ArrayList<Long>();
		List<Long> materijali = q.list();
		
		for (NoviTipZahvataMaterijalVM mvm : mvms) {
			assertTrue("A", materijali.contains(mvm.getMaterijalId()));
			mvms_mids.add(mvm.getMaterijalId());
		}
		
		for (Long m_id : materijali) {
			assertTrue("B", mvms_mids.contains(m_id));
		}
		
		sess.close();
	}
	
	@Test
	public void dodajNoviTipSPogresnimMaterijalom(){
		
		long id = dajIdMaterijalaKojiPostoji();
		
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		long broja_zahvata = dajBrojTipZahvata(sess);
		long broj_materijala = dajBrojMaterijalaTipZahvata(sess);
		
		TipZahvataManager tzmManager = new TipZahvataManager(sess);
		
		vm.setMaterijali(mvms);
		NoviTipZahvataMaterijalVM temp = new NoviTipZahvataMaterijalVM();
		temp.setMaterijalId(id);
		temp.setKolicina(3.33);
		vm.getMaterijali().add(temp);
		
		boolean result = tzmManager.dodajNoviTip(vm);
		assertFalse(result);
		
		assertEquals("Broj tipova zahvata mora ostati nepromijenjen",
				broja_zahvata, dajBrojTipZahvata(sess));
		assertEquals("Broj matrijalTipZahvata mora ostati nepromijenjen",
				broj_materijala, dajBrojMaterijalaTipZahvata(sess));
		
		sess.close();
		
	}
	
	public long dajIdMaterijalaKojiPostoji(){
		Session sess = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "SELECT MAX(id) FROM Materijal";
		Query q = sess.createQuery(hql);
		long max_id = (Long) q.uniqueResult();
		
		sess.close();
		
		return max_id + 1;
	}
	
	public long dajBrojMaterijalaTipZahvata(Session session){
		String hqlImaLi = "SELECT COUNT(*) FROM MaterijalTipZahvata";
		Query imaLi = session.createQuery(hqlImaLi); 
		long broj = (Long) imaLi.uniqueResult();
		
		return broj;
	}
	
	public long dajBrojTipZahvata(Session session){
		String hqlImaLi = "SELECT COUNT(*) FROM TipZahvata";
		Query imaLi = session.createQuery(hqlImaLi); 
		long broj = (Long) imaLi.uniqueResult();
		
		return broj;
	}
}
