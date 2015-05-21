package ba.unsa.etf.si.tim12.dal.domainmodel;

public class Materijal implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4028936004155348882L;
	
	private long id;
	private String naziv,mjernaJedinica;
	private double cijena;
	public Materijal(long id, String naziv, String mjernaJedinica,double cijena) {
		this.id = id;
		this.naziv = naziv;
		this.mjernaJedinica = mjernaJedinica;
		this.cijena = cijena;
	
	}
	public Materijal(){}
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
