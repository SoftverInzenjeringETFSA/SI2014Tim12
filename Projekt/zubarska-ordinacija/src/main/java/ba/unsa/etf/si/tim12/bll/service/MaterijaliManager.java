package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.Session;

public class MaterijaliManager {
	Session session;
	
	public MaterijaliManager(Session session) {
		
		
	}
	
	public  MaterijaliVM nadjiPoImenu(String ime) {
		return new MaterijaliVM();
		//TODO: THIS
		
	}
	
	public  boolean izbrisiMaterijal(long id) {
		return true;
		//TODO: THIS
		
	}
	
	public  MaterijaliVM nadjiPoTipuZahvata(long tipZahvataId) {
		return new MaterijaliVM();
		//TODO: THIS
		
	}
	
	public  MaterijaliVM nadjiPoObavljenomZahvatu(long obavljeniZahvatId) {
		return new MaterijaliVM();
		//TODO: THIS
		
	}

}
