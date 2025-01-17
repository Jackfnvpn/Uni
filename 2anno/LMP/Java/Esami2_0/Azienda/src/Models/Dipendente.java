package Models;
import java.time.LocalDate;
import Enum.Dipartimento;
import Enum.Mansione;


public class Dipendente {
		
	
	private String 		nome;
	private String 		cognome;
	private LocalDate	dataNascita;
	private LocalDate	dataAssunzione;
	
	private Dipartimento dipartimento;
	private Mansione	 mansione;
	
	private String		matricola;
	static private int 	numeroProgressivo=0;
	
	private int 		livello;
	private Dipendente 	capo;
	
	public Dipendente(String nome, String cognome, LocalDate dataNascita, LocalDate dataAssunzione, int livello, Dipendente capo,Dipartimento dipartimento, Mansione mansione) {
		
		this.nome=nome;
		this.cognome=cognome;
		this.dataNascita=dataNascita;
		this.dataAssunzione=dataAssunzione;
		this.dipartimento=dipartimento;
		this.mansione=mansione;
		setLivello(livello);
		
		this.capo=capo;
		this.matricola="CLT_"+numeroProgressivo;
		numeroProgressivo++;
	}

	public String getNome() {
		return nome;
	}


	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public LocalDate getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(LocalDate dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public String getMatricola() {
		return matricola;
	}
	
	public Mansione getMansione() {
		return this.mansione;
	}
	
	public Dipartimento getDipartimento() {
		return this.dipartimento;
	}


	public int getLivello() {
		return livello;
	}

	public void setLivello(int livello) {
		if (livello < 1 || livello > 8) {
			throw new IllegalArgumentException("Il livello deve essere compreso tra 1 e 8");
		}
		this.livello = livello;
	}

	public Dipendente getCapo() {
		return capo;
	}

}
