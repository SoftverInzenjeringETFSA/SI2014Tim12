package ba.unsa.etf.si.tim12.bll.viewmodel;

public class PotMaterijaliRowVM {
	private long idMaterijala;
	private String nazivMaterijala;
	private double jedinicnaCijena;
	private String mjernaJedinica;
	private double kolicina;
	private double ukupnaCijena;
	
	public long getIdMaterijala() {
		return idMaterijala;
	}
	public void setIdMaterijala(long idMaterijala) {
		this.idMaterijala = idMaterijala;
	}
	public String getNazivMaterijala() {
		return nazivMaterijala;
	}
	public void setNazivMaterijala(String nazivMaterijala) {
		this.nazivMaterijala = nazivMaterijala;
	}
	public double getJedinicnaCijena() {
		return jedinicnaCijena;
	}
	public void setJedinicnaCijena(double jedinicnaCijena) {
		this.jedinicnaCijena = jedinicnaCijena;
	}
	public String getMjernaJedinica() {
		return mjernaJedinica;
	}
	public void setMjernaJedinica(String mjernaJedinica) {
		this.mjernaJedinica = mjernaJedinica;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}
	public double getUkupnaCijena() {
		return ukupnaCijena;
	}
	public void setUkupnaCijena(double ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}
}