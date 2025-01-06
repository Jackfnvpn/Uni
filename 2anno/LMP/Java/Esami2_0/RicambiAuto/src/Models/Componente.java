package Models;

public class Componente {
	private String nomeComp;
	private String  luogoProv;
	private int     tempoOrd;
	private double  costo;
	
	public Componente(String nomeComp, String luogoProv, int tempoOrd, double costo) {
		this.nomeComp=nomeComp;
		this.luogoProv=luogoProv;
		this.tempoOrd=tempoOrd;
		this.costo=costo;
	}

	public String getNomeComp() {
		return nomeComp;
	}

	public void setNomeComp(String nomeComp) {
		this.nomeComp = nomeComp;
	}

	public String getLuogoProv() {
		return luogoProv;
	}

	public void setLuogoProv(String luogoProv) {
		this.luogoProv = luogoProv;
	}

	public int getTempoOrd() {
		return tempoOrd;
	}

	public void setTempoOrd(int tempoOrd) {
		this.tempoOrd = tempoOrd;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	

}
