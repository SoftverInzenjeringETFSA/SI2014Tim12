package ba.unsa.etf.si.tim12.dal.domainmodel;

public class UtroseniMaterijal  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3035542043834501773L;
	
	private long materijalId,obavljeniZahvatId;
	private double kolicina;
	public UtroseniMaterijal(long materijalId,long obavljeniZahvatId,double kolicina)
	{
		this.materijalId=materijalId;
		this.obavljeniZahvatId=obavljeniZahvatId;
		this.kolicina=kolicina;

	}
	public UtroseniMaterijal()
	{
		
	}
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
