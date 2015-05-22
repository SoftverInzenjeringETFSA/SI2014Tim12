package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;

public class PrikaziTipZahvataVM {
	private long id;
	private String naziv;
	private Double cijena;
	private ArrayList<TipZahvataMaterijalVM> materijali;

	public PrikaziTipZahvataVM() {
		materijali = new ArrayList<TipZahvataMaterijalVM>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getCijena() {
		return cijena;
	}

	public void setCijena(Double cijena) {
		this.cijena = cijena;
	}

	public ArrayList<TipZahvataMaterijalVM> getMaterijali() {
		return materijali;
	}

	public void setMaterijali(ArrayList<TipZahvataMaterijalVM> materijali) {
		this.materijali = materijali;
	}
}
