package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class PosjetaVM {
	private long id;
	private long pacijenti;
	private String doktor;
	private String dijagnoza;
	private Date vrijeme;
	
	public PosjetaVM() {
		
	}
	public PosjetaVM(long id, long pacijentId, String doktor, String dijagnoza, Date vrijeme) {
		this.id = id;
		this.pacijenti = pacijentId;
		this.doktor = doktor;
		this.dijagnoza = dijagnoza;
		this.vrijeme = new Date(vrijeme.getTime());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPacijenti() {
		return pacijenti;
	}
	public void setPacijenti(long pacijenti) {
		this.pacijenti = pacijenti;
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
		this.vrijeme = new Date(vrijeme.getTime());
	}
}
