package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;

import java.util.*;

import org.hibernate.Transaction;
import org.hibernate.Session;

public class PosjetaManager {
	Session session;
	
	public PosjetaManager(Session session) {}
	
	public  PosjetaVM nadjiPoPacijentu(long idpacijenta) {
		return new PosjetaVM();
		//TODO: This
	}
	
	public  void dodajNovuPosjetu(NovaPosjetaVM posjeta) {
		//TODO: This
	}
	
	public  ArrayList<PosjetaVM> nadjiPoDijagnozi(String dijagnoza) {
		return new ArrayList<PosjetaVM>();
		//TODO: This
	}

}
