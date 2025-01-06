package Models;

import java.time.LocalDate;

public class Libro extends Supporto{
	
	private int numeroPagine;
	
	public Libro(String titolo, String entePubblicante, LocalDate annoPubblicazione, int numeroPagine, int numeroGiorni) {
		super(titolo, entePubblicante, annoPubblicazione,numeroGiorni);
		this.numeroPagine=numeroPagine;
	}

}
