import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Modelli.Classe;
import Modelli.Studenti;
import Servizi.GestioneClassi;

public class App {
    public static void main(String[] args) throws Exception {
        GestioneClassi presidenza = new GestioneClassi();  

        presidenza.addClasse("5", "D", 2004);    

        presidenza.addStudenti("5D", "Giacomo", "Pace", LocalDate.of(2002, 9, 2), "Napoli", LocalDate.of(2019, 2, 20));
        presidenza.addStudenti("5D", "Paolo", "Pace", LocalDate.of(2004, 9, 2), "Napoli", LocalDate.of(2019, 2, 20));

        
        System.out.println(presidenza.numeroStudentiClasse("5D"));  

        System.out.println(presidenza.ripetenti("5D"));




    }  
}
