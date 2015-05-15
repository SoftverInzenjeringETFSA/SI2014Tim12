package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class ZahvatiPoDoktoruVM {
	String doktor;
	Date vrijemeOd,vrijemeDo;
	double ukupnaCijena;
	int ukupnoPosjeta;
	
	public String getDoktor() {
		return doktor;
	}
	public void setDoktor(String doktor) {
		this.doktor = doktor;
	}
	public Date getVrijemeOd() {
		return vrijemeOd;
	}
	public void setVrijemeOd(Date vrijemeOd) {
		this.vrijemeOd = vrijemeOd;
	}
	public Date getVrijemeDo() {
		return vrijemeDo;
	}
	public void setVrijemeDo(Date vrijemeDo) {
		this.vrijemeDo = vrijemeDo;
	}
	public double getUkupnaCijena() {
		return ukupnaCijena;
	}
	public void setUkupnaCijena(double ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}
	public int getUkupnoPosjeta() {
		return ukupnoPosjeta;
	}
	public void setUkupnoPosjeta(int ukupnoPosjeta) {
		this.ukupnoPosjeta = ukupnoPosjeta;
	}
}
