package Models;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Prodotto {
	private int identificativo;
	private String etichettaRif;
	private double costoProd;
	private double prezzo;
	private ArrayList<Componente> subComp;
	private String provenienza;
	private int tempoOrdineComp;
	private int tempoReal;
	private double costoReal;
	static private int costGiorn;
	
	public Prodotto (int identificativo, String etichettaRif, String provenienza,int tempoReal,ArrayList<Componente>subComp, int costoGiorn,int guad) {
		this.identificativo=identificativo;
		this.etichettaRif=etichettaRif;
		this.subComp=subComp;
		this.provenienza=provenienza;
		this.setTempoOrdineComp();
		this.tempoReal=tempoReal;
		this.setCostoReal(tempoReal,costoGiorn);
		this.setCostoProd();
		this.setPrezzo(guad);
		
	}
	
	
	public void setPrezzo(int guad) {
		
		this.prezzo=costoProd*guad;
			
	}


	public int getIdentificativo() {
		return identificativo;
	}


	public void setIdentificativo(int identificativo) {
		this.identificativo = identificativo;
	}


	public String getEtichettaRif() {
		return etichettaRif;
	}


	public void setEtichettaRif(String etichettaRif) {
		this.etichettaRif = etichettaRif;
	}


	public double getCostoProd() {
		return costoProd;
	}


	public void setCostoProd() {
		double somma=0;
		for(Componente componenteProd: this.getSubComp()) {
			somma+=(componenteProd.getCosto());
		}
		this.costoProd=somma+this.costoReal;
	}


	public double getPrezzo() {
		return this.prezzo;
	}

	


	public ArrayList<Componente> getSubComp() {
		return this.subComp;
	}


	public void setSubComp(ArrayList<Componente> subComp) {
		this.subComp = subComp;
	}


	public String getProvenienza() {
		return provenienza;
	}


	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}


	public int getTempoOrdineComp() {
		return tempoOrdineComp;
	}


	public void setTempoOrdineComp() {
		int max=0;
		//LocalDateTime date = LocalDateTime.of(0, 0, 0, 0, 0);
		for(Componente componente: this.subComp) {
			if (componente.getTempoOrd() >= max) {
				max=componente.getTempoOrd();
			}
			
		}
		this.tempoOrdineComp = max;
	}


	public int getTempoReal() {
		return tempoReal;
	}


	public void setTempoReal(int tempoReal) {
		this.tempoReal = tempoReal;
	}


	public double getCostoReal() {
		return this.costoReal;
	}

	public void setCostoReal(int tempoReal,  double costo) {
		this.costoReal = tempoReal*costo;
	}


	@Override
	public String toString() {
		return "Prodotto [identificativo=" + identificativo + ", etichettaRif=" + etichettaRif + ", costoProd="
				+ costoProd + ", prezzo=" + prezzo + ", subComp=" + subComp + ", provenienza=" + provenienza
				+ ", tempoOrdineComp=" + tempoOrdineComp + ", tempoReal=" + tempoReal + ", costoReal=" + costoReal
				+ "]";
	}
	
	

	
}
