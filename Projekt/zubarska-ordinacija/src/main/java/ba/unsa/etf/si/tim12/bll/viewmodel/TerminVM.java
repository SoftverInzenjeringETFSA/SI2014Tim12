package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class TerminVM {
	private long id;
	private String doktor;
	private Date vrijeme;
	private boolean otkazan;
	private long pacijentId;
	
	public TerminVM()
	{
		
	}
	//t.id, t.doktor, t.vrijeme, t.otkazano, t.pacijentId)
	public TerminVM(long id, String doktor, Date vrijeme, boolean otkazan, long pacijentId)
	{
		setId(id);
		setDoktor(doktor);
		setVrijeme(vrijeme);
		setOtkazan(otkazan);
		setPacijentId(pacijentId);
	}

	public long getId() {
		return id;
	}
	
	public long getPacijentId() {
		return pacijentId;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setPacijentId(long id) {
		this.pacijentId = id;
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
