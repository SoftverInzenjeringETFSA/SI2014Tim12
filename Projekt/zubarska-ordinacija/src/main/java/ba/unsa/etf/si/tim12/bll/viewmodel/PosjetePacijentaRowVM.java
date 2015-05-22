package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class PosjetePacijentaRowVM {
	private String opisZahvata;
	private String dijagnoza;
	private String doktor;
	private Date vrijeme;

	public String getOpiszahvata() {
		return opisZahvata;
	}

	public void setOpiszahvata(String opiszahvata) {
		this.opisZahvata = opiszahvata;
	}

	public String getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}

	public String getDoktor() {
		return doktor;
	}

	public void setDoktor(String doktor) {
		this.doktor = doktor;
	}

	public Date getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}
}
