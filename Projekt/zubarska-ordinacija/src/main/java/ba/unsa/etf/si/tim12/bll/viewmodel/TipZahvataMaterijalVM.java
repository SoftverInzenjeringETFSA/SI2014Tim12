package ba.unsa.etf.si.tim12.bll.viewmodel;

public class TipZahvataMaterijalVM {
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
}
