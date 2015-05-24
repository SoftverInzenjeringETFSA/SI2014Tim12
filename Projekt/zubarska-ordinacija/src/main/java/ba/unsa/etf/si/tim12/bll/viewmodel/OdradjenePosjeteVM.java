package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;
import java.util.Date;

public class OdradjenePosjeteVM {
	private Date vrijeme;
	private int ukupnoPosjeta;
	private ArrayList<OdradjenePosjeteRowVM> odradjenePosjete;

	public OdradjenePosjeteVM() {
		odradjenePosjete = new ArrayList<OdradjenePosjeteRowVM>();
	}

	public OdradjenePosjeteVM(long ukupnoPosjeta) {
		this.ukupnoPosjeta = (int) ukupnoPosjeta;
	}

	public Date getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(Date vrijeme) {
		this.vrijeme = new Date(vrijeme.getTime());
	}

	public int getUkupnoPosjeta() {
		return ukupnoPosjeta;
	}

	public void setUkupnoPosjeta(int ukupnoPosjeta) {
		this.ukupnoPosjeta = ukupnoPosjeta;
	}

	public ArrayList<OdradjenePosjeteRowVM> getOdradjenePosjete() {
		return odradjenePosjete;
	}

	public void setOdradjenePosjete(
			ArrayList<OdradjenePosjeteRowVM> odradjenePosjete) {
		this.odradjenePosjete = odradjenePosjete;
	}
}
