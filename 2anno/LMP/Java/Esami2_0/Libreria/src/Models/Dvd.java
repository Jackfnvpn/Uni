package Models;
import java.time.LocalDate;
import java.time.LocalTime;

public class Dvd extends Supporto{
	
	private LocalTime durata;

	public Dvd(String titolo, String entePubblicante, LocalDate annoPubblicazione,LocalTime durata,int numeroGiorni) {
		super(titolo, entePubblicante, annoPubblicazione, numeroGiorni);
		this.durata=durata;
	}

}
