package Main;
import Models.Componente;
import Models.Prodotto;
import Service.GestioneProd;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		GestioneProd gs = new GestioneProd();
		
		gs.setcostoGiorn(2);
		gs.setFattoreGuadagno(4);
		
		Componente componente1= new Componente("Bullone","Roma",4,5.5);
		Componente componente2= new Componente("Frizione","Roma",6,5.5);
		Componente componente3= new Componente("Cambio","Roma",5,5.5);
		
		ArrayList<Componente> listaComp = new ArrayList<>();
		listaComp.add(componente1);
		listaComp.add(componente2);
		listaComp.add(componente3);
		
		gs.addProdotto(1, "CL", "Roma", 5, listaComp);
		
		Prodotto prod1=gs.getProdotto(1);
		
		System.out.println(prod1);
	}

}
