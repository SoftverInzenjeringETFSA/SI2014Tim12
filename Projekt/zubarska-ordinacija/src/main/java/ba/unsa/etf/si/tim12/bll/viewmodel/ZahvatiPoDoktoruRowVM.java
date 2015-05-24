package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class ZahvatiPoDoktoruRowVM {
	private long id; //id zahvata obavljenog
	private String imePrezime;
	private String nazivZahvata;
	private Date vrijemePosjete;
	private double cijena;
	
	public ZahvatiPoDoktoruRowVM()
	{
		
	}
	public ZahvatiPoDoktoruRowVM(double cijena, long id, String imePrezime, String naziv, Date vrijeme)
	{
		this.setCijena(cijena);
		this.setId(id);
		this.setIme(imePrezime);
		this.setNazivZahvata(naziv);
		this.setVrijemePosjete(vrijeme);
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
