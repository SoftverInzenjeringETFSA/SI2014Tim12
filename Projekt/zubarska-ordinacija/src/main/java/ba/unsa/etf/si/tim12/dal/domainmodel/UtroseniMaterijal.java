package ba.unsa.etf.si.tim12.dal.domainmodel;

public class UtroseniMaterijal  implements java.io.Serializable {
	long materijalId,obavljeniZahvatId;
	double kolicina;
	
	public long getMaterijalId() {
		return materijalId;
	}
	public void setMaterijalId(long materijalId) {
		this.materijalId = materijalId;
	}
	public long getObavljeniZahvatId() {
		return obavljeniZahvatId;
	}
	public void setObavljeniZahvatId(long obavljeniZahvatId) {
		this.obavljeniZahvatId = obavljeniZahvatId;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}


}
