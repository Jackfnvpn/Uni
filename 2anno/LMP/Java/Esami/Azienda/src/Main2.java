import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Modelli.Dipartimento;
import Modelli.Dipendente;
import Modelli.Mansione;
import Servizi.GestioneDipendenti;

public class Main2 {
    public static void main(String[] args) throws Exception {

        ArrayList<Dipendente> lista = new ArrayList<>();

        GestioneDipendenti gestioneDipendenti = new GestioneDipendenti();

        Dipendente dipendente1=gestioneDipendenti.createDipendente("Giacomo", "Pace", LocalDate.of(2002, 2, 2), LocalDate.of(2022, 2, 3), Dipartimento.MARKETING, Mansione.ALGORITMISTA, 4, null);
        Dipendente dipendente2=gestioneDipendenti.createDipendente("Giacomo", "Rossi", LocalDate.of(2002, 3, 2), LocalDate.of(2022, 4, 3), Dipartimento.RISORSE_UMANE, Mansione.LETTERATURA, 4, dipendente1);
        Dipendente dipendente3=gestioneDipendenti.createDipendente("Giacomo", "Viola", LocalDate.of(2002, 3, 2), LocalDate.of(2022, 4, 3), Dipartimento.RISORSE_UMANE, Mansione.LETTERATURA, 4, dipendente2);
        Dipendente dipendente4=gestioneDipendenti.createDipendente("Giacomo", "Blu", LocalDate.of(2002, 3, 2), LocalDate.of(2022, 4, 3), Dipartimento.RISORSE_UMANE, Mansione.LETTERATURA, 4, dipendente2);


        
        //gestioneDipendenti.chainCommand(dipendente3, lista);  

    
        //System.out.println(gestioneDipendenti.subDipendente(dipendente3).getCognome());
        HashMap <Dipartimento,ArrayList<Dipendente>> boh = gestioneDipendenti.sameDepartmentYear();

        System.out.println(boh);
        

        
    }
}
