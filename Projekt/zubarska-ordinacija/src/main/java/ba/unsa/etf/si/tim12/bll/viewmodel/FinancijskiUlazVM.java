package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.Date;
import java.util.ArrayList;

public class FinancijskiUlazVM {
	private Date vrijemeOd;
	private Date vrijemeDo;
	private double ukupnaCijena;
	private int ukupnoPosjeta;
	private ArrayList<FinancijskiUlazRowVM> listaFinancijskiUlazRowVM;

	public FinancijskiUlazVM(Date vrijemeOd, Date vrijemeDo,
			double ukupnaCijena, int ukupnoPosjeta) {
		this.vrijemeOd = new Date(vrijemeOd.getTime());
		this.vrijemeDo = new Date(vrijemeDo.getTime());
		this.ukupnaCijena = ukupnaCijena;
		this.ukupnoPosjeta = ukupnoPosjeta;
		listaFinancijskiUlazRowVM = new ArrayList<FinancijskiUlazRowVM>();
	}

	public FinancijskiUlazVM() {
		listaFinancijskiUlazRowVM = new ArrayList<FinancijskiUlazRowVM>();
	}

	public Date getVrijemeOd() {
		return vrijemeOd;
	}

	public void setVrijemeOd(Date vrijemeOd) {
		this.vrijemeOd = new Date(vrijemeOd.getTime());
	}

	public Date getVrijemeDo() {
		return vrijemeDo;
	}

	public void setVrijemeDo(Date vrijemeDo) {
		this.vrijemeDo = new Date(vrijemeDo.getTime());
	}

	public double getUkupnaCijena() {
		return ukupnaCijena;
	}

	public void setUkupnaCijena(double ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}

	public int getUkupnoPosjeta() {
		return ukupnoPosjeta;
	}

	public void setUkupnoPosjeta(int ukupnoPosjeta) {
		this.ukupnoPosjeta = ukupnoPosjeta;
	}

	public ArrayList<FinancijskiUlazRowVM> geListaFinancijskiUlazRowVM() {
		return listaFinancijskiUlazRowVM;
	}

	public void setFinancijskiUlazRowVM(
			ArrayList<FinancijskiUlazRowVM> listaFinancijskiUlazRowVM) {
		this.listaFinancijskiUlazRowVM = listaFinancijskiUlazRowVM;
	}
}
