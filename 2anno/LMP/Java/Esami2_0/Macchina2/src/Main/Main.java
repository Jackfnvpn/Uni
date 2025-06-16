package Main;
import Services.GestioneMacchina;
import Enum.Disponibilita;
import Models.DisponibilitaMacchina;

public class Main {

	public static void main(String[] args) {
		GestioneMacchina gs = new GestioneMacchina();

        gs.addMacchina("Audi", "Q8", 2000, 50, 100);
        gs.addMacchina("BMW", "M4", 2000, 50, 100);

        gs.setStatus("Audi", "Q8", 2000, 50, 100, Disponibilita.VENDUTA, "Paolo Mazzara");

        gs.conteggioAutonomia("Audi", "Q8", 2000, 50, 100);

        gs.cambioCilindrata("Audi", "Q8", 2000, 50, 100, 3000);

        DisponibilitaMacchina macchina=gs.getMacchina("Audi", "Q8", 2000, 50, 100);
        System.out.println(macchina.getProprietario());
	}

}
