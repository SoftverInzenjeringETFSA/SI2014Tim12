package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class ZahvatiPoDoktoruRowVM {
	private long id;
	private String imePrezime;
	private String nazivZahvata;
	private Date vrijemePosjete;
	private double cijena;

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

	public String getPrezime() {
		return imePrezime;
	}

	public void setPrezime(String prezime) {
		this.imePrezime = prezime;
	}

	public String getNazivZahvata() {
		return nazivZahvata;
	}

	public void setNazivZahvata(String nazivZahvata) {
		this.nazivZahvata = nazivZahvata;
	}

	public Date getVrijemePosjete() {
		return vrijemePosjete;
	}

	public void setVrijemePosjete(Date vrijemePosjete) {
		this.vrijemePosjete = new Date(vrijemePosjete.getTime());
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
}
