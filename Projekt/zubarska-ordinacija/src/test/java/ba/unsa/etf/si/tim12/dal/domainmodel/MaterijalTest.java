package ba.unsa.etf.si.tim12.dal.domainmodel;

//Potreban nam je za Hibernate
import ba.unsa.etf.si.tim12.dal.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



import static org.junit.Assert.*;

import org.junit.Test;


public class MaterijalTest {

	@Test
	public void MaterijalTest_saveGetDelete(){
		Materijal m = new Materijal();
		m.setNaziv("nTest");
		m.setMjernaJedinica("jTest");
		m.setCijena(3.33);
		
	
		//Najprije je potrebnono Napraviti sesiju i otvoriti je.
		//Ovo radimo u GUI-u nakon poziva
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
		
			//Session proslijedimo BLL klasi konstruktorom - Dependency Injection
			//BLL klasa sprema kao privatni atribut sesiju koja joj je prosljeðena kroz konstruktor
			
			//U svakoj BLL metodi otvaramo transakciju
			Transaction t = session.beginTransaction();
			
			//Radimo nešto u metodi
			long novi_id = (Long) session.save(m);
		
			assertEquals("Id materijala mora biti jednak novom id-u", novi_id, m.getId());
			//Kad zavrsimo uradimo commit da se spreme promjene
			t.commit();

			
			Materijal novi = (Materijal) session.get(Materijal.class, m.getId());
			assertEquals("Idevi moraju biti jednaki", m.getId(), novi.getId());
			assertEquals("Imena moraju biti jednaki", m.getNaziv(), novi.getNaziv());
			assertEquals("Mjerne jedinice moraju biti jednake", 
					m.getMjernaJedinica(), novi.getMjernaJedinica());
			assertEquals("Cijene moraju biti 'iste', s 0.00001 tolerancijom", 
					m.getCijena(), novi.getCijena(), 0.00001);
			
			
			m.setCijena(6.66);
			
			//Nakon svakog commita, potrebno je ponovo napraviti transakciju, ako cemo raditi s bazom nesto
			t = session.beginTransaction();
			session.update(m);
				assertEquals("", 6.66, m.getCijena(), 0.00001);
			t.commit();
			
			t = session.beginTransaction();
			
			novi = (Materijal) session.get(Materijal.class, m.getId());
			assertEquals(6.66, novi.getCijena(), 0.00001);		
			
			Query q = session.createQuery("SELECT COUNT(*) FROM Materijal");
			Long count = (Long) q.uniqueResult();
			assertNotNull(count);
			
			System.out.println("Broj materijala: " + count);
						
			session.delete(m);
			t.commit();
			
			
			//Ako imamo samo Selecte... nije potrebna transakcija
			q = session.createQuery("SELECT COUNT(*) FROM Materijal");
			Long newCount = (Long) q.uniqueResult();
			assertNotNull(newCount);
			
			assertEquals(count.longValue(), newCount.longValue() + 1);
			
			
			
		}catch(Exception e){
			fail("Greska" + e.getMessage());
		}finally{
			//VAZNO: ne zaboraviti zatvoriti konekciju u GUI-u
			if(session != null)
				session.close();
		}
	}
}
