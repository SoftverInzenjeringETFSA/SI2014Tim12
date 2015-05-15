package ba.unsa.etf.si.tim12.bll.service;
import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class TipZahvataManager {
	Session session;
	
	public TipZahvataManager(Session session) {}
	
	public  boolean dodajNoviTip(NoviTipZahvataVM tipZahvata) {}
	
	public  void promjeniCijenuZahvata(long id, double cijena) {}

}
