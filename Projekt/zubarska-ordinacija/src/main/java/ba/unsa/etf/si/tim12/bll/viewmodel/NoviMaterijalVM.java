package ba.unsa.etf.si.tim12.bll.viewmodel;

public class NoviMaterijalVM {
	private String naziv;
	private String mjernaJedinica;
	private double cijena;
	
	public NoviMaterijalVM( String naziv,String mjernaJedinica,double cijena) {	
	this.cijena=cijena;
	this.naziv=naziv;
	this.mjernaJedinica=mjernaJedinica;
}
public NoviMaterijalVM() {
	
}
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getMjernaJedinica() {
		return mjernaJedinica;
	}

	public void setMjernaJedinica(String mjernaJedinica) {
		this.mjernaJedinica = mjernaJedinica;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
}
