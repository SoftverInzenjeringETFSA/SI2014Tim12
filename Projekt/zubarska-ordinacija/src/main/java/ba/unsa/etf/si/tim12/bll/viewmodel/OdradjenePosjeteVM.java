package ba.unsa.etf.si.tim12.bll.viewmodel;
import java.util.Date;

public class OdradjenePosjeteVM {
	private Date vrijeme;
	private int ukupnoPosjeta;
	
	public Date getVrijeme() {
		return vrijeme;
	}
	public void setVrijeme(Date vrijeme) {
		this.vrijeme = vrijeme;
	}
	public int getUkupnoPosjeta() {
		return ukupnoPosjeta;
	}
	public void setUkupnoPosjeta(int ukupnoPosjeta) {
		this.ukupnoPosjeta = ukupnoPosjeta;
	}
}
