package Services;
import Models.Dipendente;
import java.time.LocalDate;
import java.util.ArrayList;
import Enum.Dipartimento;
import Enum.Mansione;
public class ManageDipendente {

	private ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
	
	public void addDipendente(String nome, String cognome, LocalDate dataNascita, LocalDate dataAssunzione, int livello, Dipendente capo,Mansione mansione, Dipartimento dipartimento) {
		Dipendente dipendente=new Dipendente( nome,  cognome,  dataNascita,  dataAssunzione,  livello,  capo,  dipartimento,mansione);
		this.dipendenti.add(dipendente);
	} 
	
	public Dipendente getDipendente(String nome, String cognome) {
		for(Dipendente dipendente: dipendenti) {
			if(dipendente.getNome()==nome && dipendente.getCognome()==cognome) {
				return dipendente;
			}
		}
		return null;
	}
	
	public ArrayList<Dipendente> chainOfCommand(Dipendente dipendente){
		ArrayList<Dipendente> capi = new ArrayList<Dipendente>();
		cercaCapo(dipendente,capi);
		return capi;
	}	
	public void cercaCapo(Dipendente dipendente,ArrayList<Dipendente> capi) {
		if (dipendente==null) {
			return;
		} 
		
		else { 
			capi.add(dipendente.getCapo());
			this.cercaCapo(dipendente.getCapo(),capi);
		}
		
	}
	
	public String cercaSostituto(Dipendente dipendente) {
		
		ArrayList<Dipendente> capiDipendente = this.chainOfCommand(dipendente);
		int i=0;
		while(i<capiDipendente.size()-1) {
			for(Dipendente impiegato:this.dipendenti) {
				if (impiegato.getMansione()==dipendente.getMansione()) {
					
					if(impiegato.getCapo()==capiDipendente.get(i)) {
						return impiegato.getMatricola();
						
					}
				}
			}
			i++;
		}
		return "Non è possibile sostituire";
	}
	
	public ArrayList<Dipendente>sameYear(){
		ArrayList<Dipendente> listDipSY=new ArrayList<Dipendente>();
		
		for(Dipendente dipendente1: this.dipendenti) {
			for (Dipendente dipendente2:this.dipendenti) {
				if (!dipendente1.equals(dipendente2)) {
					if (dipendente1.getDataAssunzione().getYear()==dipendente2.getDataAssunzione().getYear()
					&& dipendente1.getDataAssunzione().getMonth()==dipendente2.getDataAssunzione().getMonth()
					&& dipendente1.getDipartimento().equals(dipendente2.getDipartimento())){
						listDipSY.add(dipendente1);
					}
				}
			}
		}
		return listDipSY;
	}
}	
	

