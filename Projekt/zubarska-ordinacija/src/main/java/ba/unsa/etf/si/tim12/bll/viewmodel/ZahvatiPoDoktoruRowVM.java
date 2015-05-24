package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class ZahvatiPoDoktoruRowVM {
	private long id;
	private String imePrezime;
	private String nazivZahvata;
	private Date vrijemePosjete;
	private double cijena;
	
	public ZahvatiPoDoktoruRowVM(){
		
	}
	
	public ZahvatiPoDoktoruRowVM(long id, String imePrezime, String nazivZahvata, Date vrijemePosjete, double cijena){
		this.setId(id);
		this.setCijena(cijena);
		this.setImePrezime(imePrezime);
		this.setNazivZahvata(nazivZahvata);
		this.setVrijemePosjete(vrijemePosjete);
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

	public void setImePrezime(String ime) {
		this.imePrezime = ime;
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
