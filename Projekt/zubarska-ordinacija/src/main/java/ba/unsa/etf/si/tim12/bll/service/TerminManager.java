package ba.unsa.etf.si.tim12.bll.service;
import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class TerminManager {
	Session session;
	
	public TerminManager(Session session) {}
	
	public  set<TerminVM> nadjiPoPacijentu(long idpacijenta) {}
	
	public  set<TerminVM> nadjiPoVremenu(Date vrijemePoc, Date vrijemeKraj) {}
	
	public  void odkaziTermin(lond terminId) {}

}
