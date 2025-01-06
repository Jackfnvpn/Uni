package Models;
import java.time.LocalDate;
import java.util.ArrayList;

public class Supporto {
	private String titolo;
	private String entePubblicante;
	private LocalDate annoPubblicazione;
	private int numeroGiorni;
	private ArrayList<Prestiti> listaPrestiti;
	
	public Supporto(String titolo, String entePubblicante, LocalDate annoPubblicazione,int numeroGiorni) {
		this.titolo=titolo;
		this.entePubblicante=entePubblicante;
		this.annoPubblicazione=annoPubblicazione;
		this.numeroGiorni=numeroGiorni;
		this.listaPrestiti=new ArrayList<>();
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getEntePubblicante() {
		return entePubblicante;
	}
	

	public int getNumeroGiorni() {
		return numeroGiorni;
	}

	public void setNumeroGiorni(int numeroGiorni) {
		this.numeroGiorni = numeroGiorni;
	}

	public ArrayList<Prestiti> getListaPrestiti() {
		return listaPrestiti;
	}

	public void setListaPrestiti(ArrayList<Prestiti> listaPrestiti) {
		this.listaPrestiti = listaPrestiti;
	}

	public void setEntePubblicante(String entePubblicante) {
		this.entePubblicante = entePubblicante;
	}

	public LocalDate getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione(LocalDate annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

}
