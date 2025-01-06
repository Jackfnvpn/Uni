package Models;
import Enum.TipologiaSportivo;
import java.time.LocalDate;
import java.util.HashMap;

public class Sportivo {
	private String nome;
	private String cognome;
	private LocalDate dataAssunzione;
	private HashMap<String,Integer> numeroReti;
	static  private int numeroProgressivo=0;
	private int livelloStipendiale;
	private String nIscrizione;
	private TipologiaSportivo tipologia;
	
	public Sportivo(String nome, String cognome, LocalDate dataAssunzione, int livelloStipendiale, TipologiaSportivo tipologia){
		this.nome=nome;
		this.cognome=cognome;
		this.dataAssunzione=dataAssunzione;
		this.numeroReti=new HashMap<>();
		numeroReti.put("Gennaio", 0);
		numeroReti.put("Febbraio", 0);
		numeroReti.put("Marzo", 0);
		numeroReti.put("Aprile", 0);
		numeroReti.put("Maggio", 0);
		numeroReti.put("Giugno", 0);
		numeroReti.put("Luglio", 0);
		numeroReti.put("Agosto", 0);
		numeroReti.put("Settembre", 0);
		numeroReti.put("Ottobre", 0);
		numeroReti.put("Novembre", 0);
		numeroReti.put("Dicembre", 0);
		this.setLivelloStipendiale(livelloStipendiale);
		this.nIscrizione="CLT_"+numeroProgressivo;
		numeroProgressivo++;
		this.tipologia=tipologia;
		
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(LocalDate dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public HashMap<String,Integer> getNumeroReti() {
		return numeroReti;
	}

	public void setRete(String mese, Integer numeroReti) {
		//this.numeroReti = numeroReti;
		this.numeroReti.replace(mese, numeroReti);
	}

	public int getLivelloStipendiale() {
		return livelloStipendiale;
	}

	public void setLivelloStipendiale(int livelloStipendiale) {
		if(livelloStipendiale < 1 ||livelloStipendiale>5) {
			throw new IllegalArgumentException("Il livello deve essere compreso tra 1 e 5");
		}
		this.livelloStipendiale = livelloStipendiale;
	}
	

}
