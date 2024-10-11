import Servizi.GestionePrestiti;
import java.time.LocalDate;
import java.time.LocalTime;
import Modelli.Libro;
import Modelli.DVD;


public class Main {
    public static void main(String[] args) throws Exception {

        GestionePrestiti gestionePrestiti = new GestionePrestiti();

        Libro libro1=gestionePrestiti.addLibro("Il piccolo principe", "BOH",LocalDate.of(1980, 2, 3),50);
        DVD dvd1=gestionePrestiti.addDVD("Cars","Pixar",LocalDate.of(2006, 9, 10),LocalTime.of(2, 30, 0));  


        libro1.addPrestito(LocalDate.of(2024, 2, 10), null ,"Giacomo", "Pace",10,5);  

        gestionePrestiti.riconsegna(libro1.getPrestiti().get(0));
        
        libro1.addPrestito(LocalDate.of(2024, 2, 10), null,"Mario", "Pace",10,5);  

        gestionePrestiti.inconsistenze(libro1);

    
    }
}
