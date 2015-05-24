package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;

public class FinancijskiUlazRowVM {
	private long id;
	private String imeIPrezime;
	private String imeZahvata;
	private Date vrijemePosjete;
	private double cijena;
	
	public FinancijskiUlazRowVM(){
		
	}
	
public FinancijskiUlazRowVM(long id, String imeIPrezime, String imeZahvata, Date vrijemePosjete, double cijena){
		this.setCijena(cijena);
		this.setId(id);
		this.setIme(imeIPrezime);
		this.setImeZahvata(imeZahvata);
		this.setVrijemePosjete(vrijemePosjete);
	}

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
		this.vrijemePosjete = new Date(vrijemePosjete.getTime());
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
}
