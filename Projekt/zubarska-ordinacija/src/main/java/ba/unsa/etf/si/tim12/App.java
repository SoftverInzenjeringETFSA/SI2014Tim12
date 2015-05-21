package ba.unsa.etf.si.tim12;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si.tim12.dal.HibernateUtil;
import ba.unsa.etf.si.tim12.dal.domainmodel.*;
/**
 * Hello world!
 *
 */


public class App 
{
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Materijal m = new Materijal();
		m.setNaziv("tufer2");
		m.setMjernaJedinica("kom");
		m.setCijena(0.01);
		
		System.out.format("%d, %s, %s, %f", m.getId(), m.getNaziv(), m.getMjernaJedinica(), m.getCijena());
		
		Session session;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			m = AddMaterijal(session, m);
			
			System.out.format("%d, %s, %s, %f%n", m.getId(), m.getNaziv(), m.getMjernaJedinica(), m.getCijena());
			
			m = getMaterijal(session, m.getId());
			
			System.out.format("%d, %s, %s, %f%n", m.getId(), m.getNaziv(), m.getMjernaJedinica(), m.getCijena());
			
			session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Greska: " + e.getMessage());
			e.printStackTrace();
			
			throw e;
		} 
		
		
	}
	
	public static Materijal AddMaterijal(Session ses, Materijal m){
		Transaction t = ses.beginTransaction();
		
		long id = (Long) ses.save(m);
		System.out.println("Dodan materijal, id: " + id);
		
		m.setId(id);
		t.commit();
		
		return m;
	}
	
	public static Materijal getMaterijal(Session ses, long id){
		
		Transaction t = ses.beginTransaction();
		
		Materijal m = (Materijal) ses.get(Materijal.class, id);
		
		t.commit();
		
		return m;
	}

}
