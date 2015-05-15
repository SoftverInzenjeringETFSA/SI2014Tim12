package ba.unsa.etf.si.tim12.dal.domainmodel;

import java.util.Date;

public class Posjeta implements java.io.Serializable {
	long id,pacijentId;
	String doktor,dijagnoza;
	Date vrijeme;
	
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


}
