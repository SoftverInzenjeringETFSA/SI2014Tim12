package dal.domainmodel;

public class MaterijalTipZahvata implements java.io.Serializable {
	long materijalId,tipZahvataId;
	double kolicina;
	
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

}
