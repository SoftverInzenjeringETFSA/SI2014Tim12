package ba.unsa.etf.si.tim12.bll.service;
import ba.unsa.etf.si.tim12.bll.viewmodel.*;
import ba.unsa.etf.si.tim12.dal.domainmodel.TipZahvata;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.Transaction;
import org.hibernate.Session;

public class TipZahvataManager {
	Session session;
	
	public TipZahvataManager(Session session) {
		this.session = session;
	}
	
	public boolean dodajNoviTip(NoviTipZahvataVM vm) {
		//Dodavanje zahvata
		Transaction t = session.beginTransaction();
		
		TipZahvata zahvat = new TipZahvata();
		System.out.println(zahvat.getId());
		zahvat.setNaziv(vm.getNaziv());
		zahvat.setCijena(vm.getCijena());
		zahvat.setOpis(vm.getOpis());
		
		session.save(zahvat);
		
		System.out.println(zahvat.getId());
		
		t.commit();
		
		//Dodavanje Materijala (MaterijalTipZahvata)
		t = session.beginTransaction();
		
		session.delete(zahvat);
		
		t.commit();
		
		return true;
		//TODO: This
	}
	
	public  void promjeniCijenuZahvata(long id, double cijena) {
		//TODO: This - haris
	}

}
