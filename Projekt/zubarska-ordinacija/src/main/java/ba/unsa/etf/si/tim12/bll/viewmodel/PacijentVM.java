package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.sql.Timestamp;
import java.util.Date;

public class PacijentVM {
	private long id;
	private String imePrezime;
	private Date datumRodjenja;
	private String brojTelefona;
	private String opis;
	
	public PacijentVM(long id, String imePrezime, Date datumRodjenja, String brojTelefona, String opis) {
		this.id = id;
		this.imePrezime = imePrezime;
		this.datumRodjenja = new Date(datumRodjenja.getTime());
		this.brojTelefona = brojTelefona;
		this.opis = opis;
	}
	public PacijentVM() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = new Date(datumRodjenja.getTime());
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
}
