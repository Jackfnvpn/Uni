package parte22;

public class Studente extends Persona {

    String materiaPreferita;
    String classe;
    int[] voti;  

    Studente(String nome, String cognome) {
        super(nome, cognome); // eredita il costruttore della super classe
    }
    
    void Studia(){
        System.out.println("Sto studiando");
    }

    @Override
    void Saluta(){
        System.out.println("Buongiorno prof");
    }
    
}
