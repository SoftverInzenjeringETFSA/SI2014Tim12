package ba.unsa.etf.si.tim12.dal.domainmodel;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim12.dal.HibernateUtil;

public class TipZahvataTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void tip_zahvata_test() {
		TipZahvata tz = new TipZahvata();
		tz.setNaziv("Popravka");
		tz.setCijena(50.50);
		tz.setOpis("oTest");
		Session session = null;
		try {
				session = HibernateUtil.getSessionFactory().openSession();
			
	
			Transaction t = session.beginTransaction();
			
		
			long novi_id = (Long) session.save(tz);
			
			assertEquals("Id tipa zahvata mora biti jednak novom id-u", novi_id, tz.getId());
			t.commit();
			
			TipZahvata tz2 = (TipZahvata) session.get(TipZahvata.class, tz2.getId());

			assertEquals("Idevi moraju biti jednaki", tz.getId(), tz2.getId());
			assertEquals("Imena moraju biti jednaki", tz.getNaziv(), tz2.getNaziv());
			assertEquals("Cijene moraju biti iste", tz.getCijena(), tz2.getCijena(),0.00001);
			assertEquals("Opisi moraju biti isti",tz.getOpis(), tz2.getOpis());
			

			tz.setCijena(40.56);
			
			t = session.beginTransaction();
			session.update(tz);
				assertEquals("", 40.56, tz.getCijena(), 0.00001);
			t.commit();
			
           t = session.beginTransaction();
           
           tz.setOpis("novi");
           t = session.beginTransaction();
			session.update(tz);
			tz2 = (TipZahvata) session.get(TipZahvata.class, tz.getId());
			assertEquals("novi", tz.getOpis());		
			
			Query q = session.createQuery("SELECT COUNT(*) FROM TipZahvata");
			Long count = (Long) q.uniqueResult();
			assertNotNull(count);
			
			
						
			session.delete(tz);
			t.commit();
			
			
			
			q = session.createQuery("SELECT COUNT(*) FROM TipZahvata");
			Long newCount = (Long) q.uniqueResult();
			assertNotNull(newCount);
			
			assertEquals(count.longValue(), newCount.longValue() + 1);
			

		}
		catch(Exception e){
			fail("Greska" + e.getMessage());
		}finally{
			//VAZNO: ne zaboraviti zatvoriti konekciju u GUI-u
			if(session != null)
				session.close();
		}
	}

}
