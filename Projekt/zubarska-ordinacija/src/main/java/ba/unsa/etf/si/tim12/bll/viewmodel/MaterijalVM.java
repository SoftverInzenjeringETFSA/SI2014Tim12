package ba.unsa.etf.si.tim12.bll.viewmodel;

public class MaterijalVM {
	private long id;
	private String naziv;
	private String mjernaJedinica;
	private double cijena;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id){
		this.id=id;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv (String naziv) {
		this.naziv=naziv;
	}
	
	public String getMjernaJedinica() {
		return mjernaJedinica;
	}
	
	public void setMjernaJedinica (String mjernaJedinica) {
		this.mjernaJedinica=mjernaJedinica;
	}
	
	public double getCijena () {
		return cijena;
	}
	
	public void setCijena (double cijena){
		this.cijena=cijena;
	}
}
