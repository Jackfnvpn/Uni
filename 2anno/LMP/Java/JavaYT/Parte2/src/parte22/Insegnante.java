package parte22;

public class Insegnante extends Persona{
    String materia; 
    String[] classi;

    Insegnante(String nome, String cognome) {
        super(nome, cognome);
    }
    void Saluta(){
        System.out.println("Buongiorno ragazzi");
    }

    
}
