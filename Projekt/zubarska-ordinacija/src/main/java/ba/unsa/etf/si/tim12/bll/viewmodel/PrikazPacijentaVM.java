package ba.unsa.etf.si.tim12.bll.viewmodel;

import java.util.ArrayList;
import java.util.Date;

public class PrikazPacijentaVM {
	private long id;
	private String imeIPrezime;
	private Date datumRodjenja;
	private String brojTelefona;
	private String opis;
	private ArrayList<TerminVM> termini;
	private ArrayList<PosjetaVM> posjete;
	
	public PrikazPacijentaVM() {
		termini = new ArrayList<TerminVM>();
		posjete = new ArrayList<PosjetaVM>();
	}
	
	public PrikazPacijentaVM(long id, String imeIPrezime, 
			Date datumRodjenja, String brojTelefona, String opis) {
		
		this.id = id;
		this.imeIPrezime = imeIPrezime;
		this.datumRodjenja = datumRodjenja;
		this.brojTelefona = brojTelefona;
		this.opis = opis;
		
		termini = new ArrayList<TerminVM>();
		posjete = new ArrayList<PosjetaVM>();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public ArrayList<TerminVM> getTermini() {
		return termini;
	}
	public void setTermini(ArrayList<TerminVM> termini) {
		this.termini = termini;
	}
	public ArrayList<PosjetaVM> getPosjete() {
		return posjete;
	}
	public void setPosjete(ArrayList<PosjetaVM> posjete) {
		this.posjete = posjete;
	}

	public String getImeIPrezime() {
		return imeIPrezime;
	}

	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}
	
}
