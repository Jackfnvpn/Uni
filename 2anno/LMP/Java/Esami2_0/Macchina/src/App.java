import Models;
import Services;
import Enum.Disponibilita;
import Enum;
import Services.GestioneMacchina;
public class App {
    public static void main(String[] args) throws Exception {
        GestioneMacchina gs = new GestioneMacchina();

        gs.addMacchina("Audi", "Q8", 2000, 50, 100);
        gs.addMacchina("BMW", "M4", 2000, 50, 100);

        gs.setStatus("Audi", "Q8", 2000, 50, 100, Disponibilita.VENDUTA, "Paolo Mazzara");

        gs.conteggioAutonomia("Audi", "Q8", 2000, 50, 100);

        gs.cambioCilindrata("Audi", "Q8", 2000, 50, 100, 3000);

        gs.getMacchina("Audi", "Q8", 2000, 50, 100);

    }
}
