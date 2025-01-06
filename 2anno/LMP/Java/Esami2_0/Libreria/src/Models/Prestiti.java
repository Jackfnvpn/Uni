package Models;
import java.time.LocalDate;

public class Prestiti {
	private LocalDate inizioPrestito;
	private LocalDate dataConsPrev;
	private LocalDate riconsegna;
	private String nome;
	private String cognome;
	private double costo;
	
	public Prestiti(LocalDate inizioPrestito,LocalDate dataConsPrev,String nome,String cognome) {
		this.inizioPrestito=inizioPrestito;
		this.dataConsPrev=dataConsPrev;
		this.riconsegna=null;
		this.nome=nome;
		this.cognome=cognome;
	}

	public LocalDate getInizioPrestito() {
		return inizioPrestito;
	}

	public void setInizioPrestito(LocalDate inizioPrestito) {
		this.inizioPrestito = inizioPrestito;
	}

	public LocalDate getDataConsPrev() {
		return dataConsPrev;
	}

	public void setDataConsPrev(LocalDate dataConsPrev) {
		this.dataConsPrev = dataConsPrev;
	}

	public LocalDate getRiconsegna() {
		return riconsegna;
	}

	public void setRiconsegna(LocalDate riconsegna) {
		this.riconsegna = riconsegna;
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

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Prestiti [inizioPrestito=" + inizioPrestito + ", dataConsPrev=" + dataConsPrev + ", riconsegna="
				+ riconsegna + ", nome=" + nome + ", cognome=" + cognome + ", costo=" + costo + "]";
	}

}
