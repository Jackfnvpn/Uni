package parte28;

public class Insegnante extends Persona{

    Insegnante(String nome, String cognome) {
        super(nome, cognome);
    }  

    @Override  
    public void Saluta(){
        System.out.println("Buongiorno ragazzi");
    }


}
