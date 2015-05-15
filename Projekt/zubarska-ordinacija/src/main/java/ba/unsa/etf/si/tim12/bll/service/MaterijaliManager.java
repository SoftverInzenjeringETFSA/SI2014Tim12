package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class MaterijaliManager {
	Session session;
	
	public MaterijaliManager(Session session) {
		
		
	}
	
	public  MaterijalVM nadjiPoImenu(String ime) {
		return new MaterijalVM();
		//TODO: THIS
		
	}
	
	public  boolean izbrisiMaterijal(long id) {
		return true;
		//TODO: THIS
		
	}
	
	public  MaterijalVM nadjiPoTipuZahvata(long tipZahvataId) {
		return new MaterijalVM();
		//TODO: THIS
		
	}
	
	public  MaterijalVM nadjiPoObavljenomZahvatu(long obavljeniZahvatId) {
		return new MaterijalVM();
		//TODO: THIS
		
	}

}
