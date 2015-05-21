package ba.unsa.etf.si.tim12.dal.domainmodel;

public class MaterijalTipZahvata implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6994755061773120659L;
	
	private long id;
	private long materijalId;
	private long tipZahvataId;
	private double kolicina;
	
	
	public long getMaterijalId() {
		return materijalId;
	}
	public void setMaterijalId(long materijalId) {
		this.materijalId = materijalId;
	}
	public long getTipZahvataId() {
		return tipZahvataId;
	}
	public void setTipZahvataId(long tipZahvataId) {
		this.tipZahvataId = tipZahvataId;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
