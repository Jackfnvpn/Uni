package Services;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;


import Models.Dvd;
import Models.Libro;
import Models.Supporto;
import Models.Prestiti;

public class GestionePrestiti {
	ArrayList<Libro> listaLibro=new ArrayList<>();
	ArrayList<Dvd> listaDvd=new ArrayList<>();
	
	
	public Libro getLibro(String nome) {
		for(Libro libro: listaLibro) {
			if (libro.getTitolo()==nome) {
				return libro;
			}
		}
		return null;
	}
	
	public Dvd getDvd(String nome) {
		for(Dvd dvd: listaDvd) {
			if (dvd.getTitolo()==nome) {
				return dvd;
			}
		}
		return null;
	}
	
	
	
	public void addLibro(String titolo, String entePubblicante, LocalDate annoPubblicazione,int numeroPagine,int numeroGiorni) {
		Libro libro = new Libro(titolo,entePubblicante,annoPubblicazione,numeroPagine,numeroGiorni);
		listaLibro.add(libro);
	}
	
	public void addDvd(String titolo, String entePubblicante, LocalDate annoPubblicazione,LocalTime durata, int numeroGiorni) {
		Dvd dvd = new Dvd(titolo,entePubblicante,annoPubblicazione,durata,numeroGiorni);
		listaDvd.add(dvd);
	}
	
	public void addPrestito(Supporto supporto,LocalDate inizioPrestito, String nome, String cognome) {
		int numeroGiorniS=supporto.getNumeroGiorni();
		Prestiti prestito=new Prestiti(inizioPrestito,inizioPrestito.plusDays(numeroGiorniS),nome,cognome);
		supporto.getListaPrestiti().add(prestito);
		
	}
	
	public void Riconsegna(Supporto supporto,LocalDate tempoRic, String nome, String cognome) {
		for(Prestiti prestito: supporto.getListaPrestiti()) {
			if(prestito.getNome()==nome && prestito.getCognome()==cognome) {
				prestito.setRiconsegna(tempoRic);
				int offDay = (int) ChronoUnit.DAYS.between(prestito.getDataConsPrev(),tempoRic);
				prestito.setCosto(5*offDay);
			}
		}
		
	}
	
	public Prestiti lungoPrest(Supporto supporto) {
		int max=0;
		Prestiti prestitoF=null;
		for(Prestiti prestito:supporto.getListaPrestiti()){
			int day=(prestito.getRiconsegna().getDayOfMonth())-(prestito.getInizioPrestito().getDayOfMonth());
			if (day>=max) {
				prestitoF=prestito;
				max=day;
			}
		}
		return prestitoF;
	}
	
	
	public ArrayList<Prestiti> inconsistenze(Supporto supporto) {
		ArrayList<Prestiti> prestitiInc = new ArrayList<>();
		for(Prestiti prestito1:supporto.getListaPrestiti()) {
			for(Prestiti prestito2:supporto.getListaPrestiti()) {
				if (! prestito1.equals(prestito2)) {
					if (prestito1.getInizioPrestito().compareTo(prestito2.getRiconsegna())>0) {
						prestitiInc.add(prestito1);
					}
				}
			}
		}
		return prestitiInc;
	}
	
	public void configurazioneGiorniRiconsegna(Supporto supporto, int numeroGiorni){
		supporto.setNumeroGiorni(numeroGiorni);
		
	}
	

}
