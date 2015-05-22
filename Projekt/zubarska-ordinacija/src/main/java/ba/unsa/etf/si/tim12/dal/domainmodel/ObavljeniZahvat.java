package ba.unsa.etf.si.tim12.dal.domainmodel;

public class ObavljeniZahvat implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1573668064870019191L;
	
	private long id,zahvatId,posjetaId;
	private double cijena;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getZahvatId() {
		return zahvatId;
	}
	public void setZahvatId(Long zahvatId) {
		this.zahvatId = zahvatId;
	}
	public Long getPosjetaId() {
		return posjetaId;
	}
	public void setPosjetaId(Long posjetaId) {
		this.posjetaId = posjetaId;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

}
