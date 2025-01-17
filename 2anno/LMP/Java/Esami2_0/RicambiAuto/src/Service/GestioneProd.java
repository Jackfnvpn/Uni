package Service;
import Models.Prodotto;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import Models.Componente;


public class GestioneProd {
	HashMap<Prodotto, ArrayList<Componente>> archivioProd = new HashMap<Prodotto, ArrayList<Componente>>();
	private int costoGiorn;
	private int Guad;
	
	
	public void addProdotto(int identificativo, String etichettaRif, String provenienza,int tempoReal,ArrayList<Componente> subComp) {
		System.out.println(this.costoGiorn);
		Prodotto prodotto = new Prodotto(identificativo,etichettaRif,provenienza,tempoReal,subComp,this.costoGiorn,this.Guad);
		archivioProd.put(prodotto, subComp);
	}
	
	public Prodotto getProdotto(int id) {
		for(Prodotto prodotto: this.archivioProd.keySet()) {
			if (prodotto.getIdentificativo()==id) {
				return prodotto;
			}
		}
		return null;
	}
	
	public ArrayList<Prodotto> Ranking() {
		ArrayList<Prodotto>prodottoRank = new ArrayList<>();
		for(Prodotto prodotto: this.archivioProd.keySet()) {
			prodottoRank.add(prodotto);
		}
		Collections.sort(prodottoRank,(p1,p2)->Double.compare((p1.getPrezzo()-p1.getCostoProd()), (p2.getPrezzo()-p2.getCostoProd())));
		return prodottoRank;
	}
	
	public void Comp() {
		for(Prodotto prodotto: this.archivioProd.keySet()) {
			System.out.println(prodotto);
		}
		
	}
	
	
	
	public void setFattoreGuadagno(int guad) {
		this.Guad=guad;
	}
	
	public void setcostoGiorn(int costoGiorn) {
		this.costoGiorn=costoGiorn;
	}
}
