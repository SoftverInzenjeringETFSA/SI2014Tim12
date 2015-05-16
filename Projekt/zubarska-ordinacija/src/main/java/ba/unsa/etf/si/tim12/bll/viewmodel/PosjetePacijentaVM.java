package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;

public class PosjetePacijentaVM {
	private String imePrezimePacijenta;
	private long idPacijenta;
	private int ukupnoPosjeta;
	private ArrayList<PosjetePacijentaRowVM> posjete;
	
	public PosjetePacijentaVM() {
		posjete = new ArrayList<PosjetePacijentaRowVM>();
	}
	public ArrayList<PosjetePacijentaRowVM> getPosjete() {
		return posjete;
	}
	public void setPosjete(ArrayList<PosjetePacijentaRowVM> posjete) {
		this.posjete = posjete;
	}
	public String getImePrezimePacijenta() {
		return imePrezimePacijenta;
	}
	public void setImePrezimePacijenta(String imePrezimePacijenta) {
		this.imePrezimePacijenta = imePrezimePacijenta;
	}
	public long getIdPacijenta() {
		return idPacijenta;
	}
	public void setIdPacijenta(long idPacijenta) {
		this.idPacijenta = idPacijenta;
	}
	public int getUkupnoPosjeta() {
		return ukupnoPosjeta;
	}
	public void setUkupnoPosjeta(int ukupnoPosjeta) {
		this.ukupnoPosjeta = ukupnoPosjeta;
	}
}
