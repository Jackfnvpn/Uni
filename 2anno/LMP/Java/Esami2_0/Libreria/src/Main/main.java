package Main;
import Models.Prestiti;
import Models.Supporto;
import Models.Dvd;
import Models.Libro;
import Services.GestionePrestiti;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;

public class main {

	public static void main(String[] args) {
		
		GestionePrestiti gs = new GestionePrestiti();
		gs.addDvd("Cars", "Pixar", LocalDate.of(2006, 06, 06), LocalTime.of(2, 30), 3);
		gs.addDvd("Nemo", "Pixar", LocalDate.of(2007, 06, 06), LocalTime.of(2, 30), 4);
		gs.addLibro("Dracula", "Boh", LocalDate.of(1856, 06, 06), 300, 2);
		
		Dvd dvd1=gs.getDvd("Cars");
		Libro libro1=gs.getLibro("Dracula");
		
		
		gs.addPrestito(dvd1, LocalDate.of(2024, 5, 2),"Giacomo", "Pace");
		gs.addPrestito(libro1,LocalDate.of(2024, 5, 2),"Giacomo", "Pace");
		gs.addPrestito(libro1,LocalDate.of(2024, 4, 2),"Giacomo", "Pace");
		
		gs.Riconsegna(libro1, LocalDate.of(2024, 5, 8),"Giacomo", "Pace");
		gs.Riconsegna(libro1,LocalDate.of(2024, 5, 9),"Giacomo", "Pace");
		
		System.out.println(gs.lungoPrest(libro1));
		

	}

}
