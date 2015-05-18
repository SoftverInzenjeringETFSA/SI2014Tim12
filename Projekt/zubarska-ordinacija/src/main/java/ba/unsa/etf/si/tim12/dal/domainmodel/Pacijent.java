package ba.unsa.etf.si.tim12.dal.domainmodel;

import java.util.Date;

public class Pacijent implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 580664400325378245L;
	
	private long id;
	private String imeIPrezime,opis,telefon;
	private Date datumRodjenja;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getImeIPrezime() {
		return imeIPrezime;
	}
	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}


	
	

}
