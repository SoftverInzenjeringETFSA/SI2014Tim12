package ba.unsa.etf.si.tim12.bll.viewmodel;

public class ObavljeniZahvatVM {
	private long id, zahvatId, posjetaId;
	private double cijena;

	public ObavljeniZahvatVM() {

	}

	public ObavljeniZahvatVM(long id, long zahvatId, long posjetaId,
			double cijena) {
		setId(id);
		setZahvatId(zahvatId);
		setPosjetaId(posjetaId);
		setCijena(cijena);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getZahvatId() {
		return zahvatId;
	}

	public void setZahvatId(long zahvatId) {
		this.zahvatId = zahvatId;
	}

	public long getPosjetaId() {
		return posjetaId;
	}

	public void setPosjetaId(long posjetaId) {
		this.posjetaId = posjetaId;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

}
