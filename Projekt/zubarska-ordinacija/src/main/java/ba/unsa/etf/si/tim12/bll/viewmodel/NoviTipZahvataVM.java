package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;

public class NoviTipZahvataVM {

	private String naziv;
	private double cijena;
	private String opis;
	private ArrayList<NoviTipZahvataMaterijalVM> materijali;

	public NoviTipZahvataVM() {
		materijali = new ArrayList<NoviTipZahvataMaterijalVM>();
	}

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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

}
