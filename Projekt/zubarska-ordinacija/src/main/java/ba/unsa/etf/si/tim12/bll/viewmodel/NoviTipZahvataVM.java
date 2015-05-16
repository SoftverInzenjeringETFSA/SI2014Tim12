package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;

public class NoviTipZahvataVM {
	public NoviTipZahvataVM() {
		materijali = new ArrayList<NoviTipZahvataMaterijalVM>();
	}
	private String naziv;
	private double cijena;
	private ArrayList<NoviTipZahvataMaterijalVM> materijali;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	public ArrayList<NoviTipZahvataMaterijalVM> getMaterijali() {
		return materijali;
	}
	public void setMaterijali(ArrayList<NoviTipZahvataMaterijalVM> materijali) {
		this.materijali = materijali;
	}
}
