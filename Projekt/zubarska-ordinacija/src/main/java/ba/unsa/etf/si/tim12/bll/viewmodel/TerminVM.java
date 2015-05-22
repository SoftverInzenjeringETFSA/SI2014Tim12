package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class TerminVM {
	private long id;
	private String doktor;
	private Date vrijeme;
	private boolean otkazan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		this.vrijeme = new Date(vrijeme.getTime());
	}

	public boolean isOtkazan() {
		return otkazan;
	}

	public void setOtkazan(boolean otkazan) {
		this.otkazan = otkazan;
	}
}
