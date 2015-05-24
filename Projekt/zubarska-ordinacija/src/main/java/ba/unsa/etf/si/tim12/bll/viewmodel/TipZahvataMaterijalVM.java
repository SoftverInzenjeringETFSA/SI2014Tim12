package ba.unsa.etf.si.tim12.bll.viewmodel;

public class TipZahvataMaterijalVM {
	private long materijalId;
	private String materijalIme;
	private long tipZahvataId;
	private double kolicina;
	private String mjernaJedinica;
	
	public long getMaterijalId() {
		return materijalId;
	}

	public TipZahvataMaterijalVM(long materijalId, String materijalIme, String mjernaJedinica,
			long tipZahvataId, double kolicina) {
		
		this.materijalId = materijalId;
		this.materijalIme = materijalIme;
		this.mjernaJedinica = mjernaJedinica;
		this.tipZahvataId = tipZahvataId;
		this.kolicina = kolicina;
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

	public String getMaterijalIme() {
		return materijalIme;
	}

	public void setMaterijalIme(String materijalIme) {
		this.materijalIme = materijalIme;
	}

	public String getMjernaJedinica() {
		return mjernaJedinica;
	}

	public void setMjernaJedinica(String mjernaJedinica) {
		this.mjernaJedinica = mjernaJedinica;
	}
}
