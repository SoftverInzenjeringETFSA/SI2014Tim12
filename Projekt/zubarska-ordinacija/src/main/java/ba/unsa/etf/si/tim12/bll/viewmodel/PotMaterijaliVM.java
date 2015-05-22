package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;
import java.util.Date;

public class PotMaterijaliVM {
	private Date vrijemeOd, vrijemeDo;
	private double ukupnaCijena;
	private ArrayList<PotMaterijaliRowVM> materijali;

	public PotMaterijaliVM() {
		materijali = new ArrayList<PotMaterijaliRowVM>();
	}
	public PotMaterijaliVM(Date vrijemeOd, Date vrijemeDo, double cijena) {
		this.vrijemeOd = new Date(vrijemeOd.getTime());
		this.vrijemeDo = new Date(vrijemeDo.getTime());
		ukupnaCijena = cijena;
		materijali = new ArrayList<PotMaterijaliRowVM>();
	}
	
	public Date getVrijemeOd() {
		return vrijemeOd;
	}

	public void setVrijemeOd(Date vrijemeOd) {
		this.vrijemeOd = new Date(vrijemeOd.getTime());
	}

	public Date getVrijemeDo() {
		return vrijemeDo;
	}

	public void setVrijemeDo(Date vrijemeDo) {
		this.vrijemeDo = new Date(vrijemeDo.getTime());
	}

	public double getUkupnaCijena() {
		return ukupnaCijena;
	}

	public void setUkupnaCijena(double ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}

	public ArrayList<PotMaterijaliRowVM> getMaterijali() {
		return materijali;
	}

	public void setMaterijali(ArrayList<PotMaterijaliRowVM> materijali) {
		this.materijali = materijali;
	}
}