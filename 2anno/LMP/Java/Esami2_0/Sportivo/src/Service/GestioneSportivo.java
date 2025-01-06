package Service;

import java.time.LocalDate;
import Enum.TipologiaSportivo;
import Models.Sportivo;
import java.util.ArrayList;
import java.util.HashMap;

public class GestioneSportivo {
	
	ArrayList<Sportivo> sportivi = new ArrayList<Sportivo>();
	
	public void addSportivo(String nome, String cognome, LocalDate dataAssunzione, int livelloStipendiale, TipologiaSportivo tipologia) {
		Sportivo sportivo = new Sportivo( nome,  cognome,  dataAssunzione,  livelloStipendiale,  tipologia);
		sportivi.add(sportivo);
	}
	
	
	public void updateNome(Sportivo sportivo,String nome) {
		sportivo.setNome(nome);
	}
	public void updateCognome(Sportivo sportivo,String cognome) {
		sportivo.setCognome(cognome);
	}
	public void updateDataAssunzione(Sportivo sportivo,LocalDate dataAssunzione) {
		sportivo.setDataAssunzione(dataAssunzione);
	}
	public void updateLvStipendiale(Sportivo sportivo,int livelloStipendiale) {
		sportivo.setLivelloStipendiale(livelloStipendiale);
	}
	
	public void updateRetiSportivo(Sportivo sportivo, String mese, Integer reti) {
		sportivo.setRete(mese, reti);
	}
	
	public HashMap<Sportivo,Integer> premio(int N) {
		Integer count = 0;
		HashMap<Sportivo,Integer> sportiviPremio = new HashMap<Sportivo,Integer>();
		
		for(Sportivo sportivo: sportivi) {
			if (sportivo.getLivelloStipendiale()>=3) {
				count=0;
				for(int goal:sportivo.getNumeroReti().values()) {
					if(goal>=N) {
						count++;
					}
				}
			}
			sportiviPremio.put(sportivo, count);
		}
		
		return sportiviPremio;
	}
	
	
	

}
