package src;
import Services.ManageDipendente;
import Enum.Mansione;
import Enum.Dipartimento;
import Models.Dipendente;
import java.time.LocalDate;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
		ManageDipendente manageDip = new ManageDipendente();
		manageDip.addDipendente("Ionut", "Zbirciog", LocalDate.of(2002, 11, 9),LocalDate.of(2024, 11, 9), 8, null,Mansione.SEGRETARIO,Dipartimento.MARKETING);
				
		Dipendente dipendente1=manageDip.getDipendente("Ionut","Zbirciog");
		
		manageDip.addDipendente("Adriano", "Porzia", LocalDate.of(2002, 10, 4),LocalDate.of(2023, 11, 9),7,dipendente1,Mansione.SEGRETARIO,Dipartimento.RISORSE_UMANE);
		manageDip.addDipendente("Giacomo", "Pace", LocalDate.of(2002, 9, 9),LocalDate.of(2022, 11, 9),6,dipendente1, Mansione.MARKETING_SPECIALIST,Dipartimento.MARKETING);
		
		Dipendente dipendente2=manageDip.getDipendente("Giacomo","Pace");
		
		ArrayList<Dipendente> capi= manageDip.chainOfCommand(dipendente2);
		
		for(Dipendente capo:capi) {
			if (capo != null) {
				System.out.println(capo.getNome());
			}
		}
    }
}
