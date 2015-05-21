package ba.unsa.etf.si.tim12.bll.viewmodel;

public class TipZahvataVM {
	private long id;
	private String naziv;
	private double cijena;
	
	public TipZahvataVM() {}
	public TipZahvataVM(long id, String naziv, double cijena) {
		this.id = id;
		this.naziv = naziv;
		this.cijena = cijena;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
}
