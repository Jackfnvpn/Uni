package Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Models.Leggi;
import Enum.TipologiaLegge;
import Models.Articolo;
import Models.Commi;

public class GestioneLeggi {
	ArrayList<Leggi> insiemeLeggi= new ArrayList<Leggi>();
	
	public void addLegge(TipologiaLegge tipologia, LocalDate data, ArrayList<String> allegati, String[] intestazione, String[] conclusioni) {
		Leggi legge= new Leggi(tipologia,data,allegati,intestazione,conclusioni);
		insiemeLeggi.add(legge);	
	}
	
	public void addArticolo(Leggi legge, int nArticolo, String[] introduzione, ArrayList<Commi> commi) {
		Articolo articolo = new Articolo(nArticolo,introduzione,commi);
		ArrayList<Articolo> newArt=legge.getArticoli();
		newArt.add(articolo);
		legge.setArticoli(newArt);
	}
	
	public Leggi recuperaLegge(String identità) {
		for(Leggi legge: this.insiemeLeggi) {
			if (legge.getIdentità().equals(identità)) {
				return legge;
			}
		}
		return null;
	}
	
	public HashMap<String,ArrayList<Leggi>> mostraLeggi(){
		HashMap<String,ArrayList<Leggi>> listaTipi = new HashMap<String,ArrayList<Leggi>>();
		ArrayList<Leggi>listaLeggiSts;
		for(Leggi legge1:this.insiemeLeggi) {
			for(Leggi legge2:this.insiemeLeggi) {
				if(!legge1.equals(legge2)) {
					if((legge1.getTipologia().equals(legge2.getTipologia())) && (legge1.getData().getYear()==legge2.getData().getYear())){
						if(!listaTipi.containsKey("Legge "+legge1.getTipologia()+":"+legge1.getData().getYear()+legge1.getData().getMonth()+legge1.getData().getDayOfMonth())) {
							listaLeggiSts=new ArrayList<Leggi>();
							listaLeggiSts.add(legge1);
							listaTipi.put("Legge "+legge1.getTipologia()+":"+legge1.getData().getYear()+legge1.getData().getMonth()+legge1.getData().getDayOfMonth(), listaLeggiSts);
						}
						else {
							listaTipi.get("Legge "+legge1.getTipologia()+":"+legge1.getData().getYear()+legge1.getData().getMonth()+legge1.getData().getDayOfMonth()).add(legge1);
							
						}
						
					}
				}
			}
		}
		return listaTipi;
	}
	
	public ArrayList<Leggi> leggiArt(){
		ArrayList<Leggi>ventiArt=new ArrayList<>();
		for(Leggi legge: this.insiemeLeggi) {
			if (legge.getArticoli().size()>20) {
				ventiArt.add(legge);		
			}
		}
		return ventiArt;
	}
	

}
