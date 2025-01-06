package Models;

import java.time.LocalDate;

public class Studente {
	private String nome;
	private String cognome;
	private String luogo;
	private LocalDate luogoNascita;
	private int annoIscrizione;
	
	public Studente(String nome, String cognome, String luogo, LocalDate luogoNascita, int annoIscrizione) {
		this.nome = nome;
		this.cognome = cognome;
		this.luogo = luogo;
		this.luogoNascita = luogoNascita;
		this.annoIscrizione = annoIscrizione;
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

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public LocalDate getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(LocalDate luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public int getAnnoIscrizione() {
		return annoIscrizione;
	}

	public void setAnnoIscrizione(int annoIscrizione) {
		this.annoIscrizione = annoIscrizione;
	}
	
	
	
	

}
