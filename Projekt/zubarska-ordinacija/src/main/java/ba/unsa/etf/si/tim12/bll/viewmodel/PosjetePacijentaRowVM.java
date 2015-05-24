package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class PosjetePacijentaRowVM {
	private String opisZahvata;
	private String dijagnoza;
	private String doktor;
	private Date vrijeme;
	
	public PosjetePacijentaRowVM()
	{
		
	}
	
	public PosjetePacijentaRowVM(String dijagnoza, String doktor, String opisZahvata, Date vrijeme)
	{
		this.setDijagnoza(dijagnoza);
		this.setDoktor(doktor);
		this.setOpisZahvata(opisZahvata);
		this.setVrijeme(vrijeme);
		
	}

	public String getOpisZahvata() {
		return opisZahvata;
	}

	public void setOpisZahvata(String opiszahvata) {
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
		this.vrijeme = new Date(vrijeme.getTime());
	}
}
