package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;
import java.util.Date;

public class NovaPosjetaVM {
	private long pacijentId;
	private String doktor;
	private String dijagnoza;
	private Date vrijeme;
	private ArrayList<NoviObavljeniZahvatVM> obavljeniZahvati;
	public long getPacijentId() {
		return pacijentId;
	}
	public void setPacijentId(long pacijentId) {
		this.pacijentId = pacijentId;
	}
	public String getDoktor() {
		return doktor;
	}
	public void setDoktor(String doktor) {
		this.doktor = doktor;
	}
	public String getDijagnoza() {
		return dijagnoza;
	}
	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	public Date getVrijeme() {
		return vrijeme;
	}
	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}
	public ArrayList<NoviObavljeniZahvatVM> getObavljeniZahvati() {
		return obavljeniZahvati;
	}
	public void setObavljeniZahvati(
			ArrayList<NoviObavljeniZahvatVM> obavljeniZahvati) {
		this.obavljeniZahvati = obavljeniZahvati;
	}
}
