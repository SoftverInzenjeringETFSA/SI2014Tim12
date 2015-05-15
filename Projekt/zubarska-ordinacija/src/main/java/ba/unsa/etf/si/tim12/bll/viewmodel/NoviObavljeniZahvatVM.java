package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;

public class NoviObavljeniZahvatVM {
	private long posjetaId;
	private long zahvatId;
	private double cijena;
	private ArrayList<NoviOZahvatMaterijalVM> materijali;
	
	public long getPosjetaId() {
		return posjetaId;
	}
	public void setPosjetaId(long posjetaId) {
		this.posjetaId = posjetaId;
	}
	public long getZahvatId() {
		return zahvatId;
	}
	public void setZahvatId(long zahvatId) {
		this.zahvatId = zahvatId;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
	public ArrayList<NoviOZahvatMaterijalVM> getMaterijali() {
		return materijali;
	}
	public void setMaterijali(ArrayList<NoviOZahvatMaterijalVM> materijali) {
		this.materijali = materijali;
	}
}
