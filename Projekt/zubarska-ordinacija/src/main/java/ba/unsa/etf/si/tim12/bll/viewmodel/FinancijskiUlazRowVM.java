package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class FinancijskiUlazRowVM {
	private long id;
	private String imeIPrezime;
	private String imeZahvata;
	private Date vrijemePosjete;
	private double cijena;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIme() {
		return imeIPrezime;
	}
	public void setIme(String ime) {
		this.imeIPrezime = ime;
	}
	public String getImeZahvata() {
		return imeZahvata;
	}
	public void setImeZahvata(String imeZahvata) {
		this.imeZahvata = imeZahvata;
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
