package Models;

import java.util.ArrayList;

public class Articolo {
	private int nArticolo;
	private String[] introduzione;
	private ArrayList<Commi> commi;
	public Articolo(int nArticolo, String[] introduzione, ArrayList<Commi> commi) {
		this.nArticolo = nArticolo;
		this.introduzione = introduzione;
		this.commi = commi;
	}
	public int getnArticolo() {
		return nArticolo;
	}
	public void setnArticolo(int nArticolo) {
		this.nArticolo = nArticolo;
	}
	public String[] getIntroduzione() {
		return introduzione;
	}
	public void setIntroduzione(String[] introduzione) {
		this.introduzione = introduzione;
	}
	public ArrayList<Commi> getCommi() {
		return commi;
	}
	public void setCommi(ArrayList<Commi> commi) {
		this.commi = commi;
	}
	
	
	
	
	

}
