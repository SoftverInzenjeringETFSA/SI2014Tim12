package ba.unsa.etf.si.tim12.dal.domainmodel;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import ba.unsa.etf.si.tim12.dal.HibernateUtil;

public class KorisnikTest {

	@Test
	public void test() {
		Korisnik k = new Korisnik();
		k.setUsername("testUserK");
		k.setPassword("testPassword123");

		// Najprije je potrebnono Napraviti sesiju i otvoriti je.
		// Ovo radimo u GUI-u nakon poziva
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			// Session proslijedimo BLL klasi konstruktorom - Dependency
			// Injection
			// BLL klasa sprema kao privatni atribut sesiju koja joj je
			// prosljeđena kroz konstruktor

			// U svakoj BLL metodi otvaramo transakciju
			Transaction t = session.beginTransaction();

			// Radimo nešto u metodi
			long novi_id = (Long) session.save(k);

			assertEquals("Id materijala mora biti jednak novom id-u", novi_id,
					k.getId());
			// Kad zavrsimo uradimo commit da se spreme promjene
			t.commit();

			Korisnik novi = (Korisnik) session.get(Korisnik.class, k.getId());
			assertEquals("Idevi moraju biti jednaki", k.getId(), novi.getId());
			assertEquals("Username moraju biti jednaki", k.getUsername(),
					novi.getUsername());
			assertEquals("Passwordi moraju biti jednaki", k.getPassword(),
					novi.getPassword());

			t = session.beginTransaction();
			session.delete(k);
			t.commit();

		} catch (Exception e) {

			fail("Greska" + e.getMessage());
		} finally {
			// VAZNO: ne zaboraviti zatvoriti konekciju u GUI-u
			if (session != null)
				session.close();
		}
	}

}
