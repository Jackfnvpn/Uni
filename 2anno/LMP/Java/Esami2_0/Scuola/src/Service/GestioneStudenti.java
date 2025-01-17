package Service;
import java.time.LocalDate;

import Models.Classe;
import Models.Studente;
import java.util.*;

public class GestioneStudenti {
	
	HashMap<Classe,ArrayList<Studente>> classi = new HashMap<Classe,ArrayList<Studente>>();
	public void addStudente(Classe classe, String nome, String cognome, String luogo, LocalDate luogoNascita, int annoIscrizione) {
		Studente studente = new Studente(nome,cognome,luogo,luogoNascita,annoIscrizione);
		this.classi.get(classe).add(studente);
		
	}
	
	public void addClasse(int annoStandard, int anno, String sezione) {
		Classe classe=new Classe(annoStandard,anno,sezione);
		ArrayList<Studente> studenti = new ArrayList<Studente>();
		this.classi.put(classe, studenti);
	}
	
	public Classe getClasse(String nome) {
		for(Classe classe:this.classi.keySet()) {
			if (classe.getNome().equals(nome)){return classe;}
		}
		return null;
	}
	
	public HashMap<Studente,Integer> detBocc(Classe classe) {
		HashMap<Studente,Integer> bocciati = new HashMap<Studente,Integer>();
		int anniPersi=0;
		for(Studente studente:this.classi.get(classe)) {
			anniPersi=0;
			if (studente.getAnnoIscrizione()<classe.getAnnoStandard()) {
				anniPersi=classe.getAnnoStandard()-studente.getAnnoIscrizione();
				bocciati.put(studente, anniPersi);
			}
		}
		return bocciati;	
	}
	
	
	public int numStud(Classe classe) {
		return this.classi.get(classe).size();
	}
	

}
