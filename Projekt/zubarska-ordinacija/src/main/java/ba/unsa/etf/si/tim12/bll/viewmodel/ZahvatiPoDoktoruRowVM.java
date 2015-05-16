package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class ZahvatiPoDoktoruRowVM {
	private long id;
	private String ime;
	private String prezime;
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
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
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
		this.vrijemePosjete = vrijemePosjete;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
}
