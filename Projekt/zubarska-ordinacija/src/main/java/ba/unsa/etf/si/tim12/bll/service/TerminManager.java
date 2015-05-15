package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.*;

import org.hibernate.Transaction;
import org.hibernate.Session;

public class TerminManager {
	Session session;
	
	public TerminManager(Session session) {}
	
	public  ArrayList<TerminVM> nadjiPoPacijentu(long idpacijenta) {
		return new ArrayList<TerminVM>();
		//TODO: This
	}
	
	public  ArrayList<TerminVM> nadjiPoVremenu(Date vrijemePoc, Date vrijemeKraj) {
		return new ArrayList<TerminVM>();
		//TODO: This
	}
	
	public  void odkaziTermin(long terminId) {}

}
