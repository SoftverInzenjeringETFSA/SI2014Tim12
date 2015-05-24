package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class OdradjenePosjeteRowVM {
	private long id;
	private String imePrezime;
	private String doktor;
	private Date vrijeme;
	
	public OdradjenePosjeteRowVM()
	{
		
	}
	
	//(pos.id, pac.imeIPrezime, pos.doktor, pos.vrijeme)
	public OdradjenePosjeteRowVM(long id, String imePrezime, String doktor, Date vrijeme)
	{
		this.setDoktor(doktor);
		this.setId(id);
		this.setIme(imePrezime);
		this.setVrijeme(vrijeme);
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme() {
		return imePrezime;
	}

	public void setIme(String ime) {
		this.imePrezime = ime;
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
