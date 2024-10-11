package parte28;

public class Studente extends Persona{

    Studente(String nome, String cognome) {
        super(nome, cognome);
    }

    @Override
    public void Saluta(){
        System.out.println("Salve prof");
    }

}
