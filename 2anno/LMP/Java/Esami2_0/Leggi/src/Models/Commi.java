package Models;

public class Commi {
	private int numeroCommi;
	private String[] testo;
	public Commi(int numeroCommi, String[] testo) {
		this.numeroCommi = numeroCommi;
		this.testo = testo;
	}
	public int getNumeroCommi() {
		return numeroCommi;
	}
	public void setNumeroCommi(int numeroCommi) {
		this.numeroCommi = numeroCommi;
	}
	public String[] getTesto() {
		return testo;
	}
	public void setTesto(String[] testo) {
		this.testo = testo;
	}
	
	
	
}
