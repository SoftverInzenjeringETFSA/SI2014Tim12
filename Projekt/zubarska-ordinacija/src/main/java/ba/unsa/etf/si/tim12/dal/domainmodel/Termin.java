package ba.unsa.etf.si.tim12.dal.domainmodel;

import java.util.Date;

public class Termin implements java.io.Serializable {
	long id,pacijentId;
	String doktor;
	Date vrijeme;
	boolean otkazano; 
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public Date getVrijeme() {
		return vrijeme;
	}
	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}
	public boolean isOtkazano() {
		return otkazano;
	}
	public void setOtkazano(boolean otkazano) {
		this.otkazano = otkazano;
	}

}