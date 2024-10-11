package parte21;

// final per le costanti , static accomuna gli attributi e i metodi per le varie istanze , non riguarda l'oggetto ma proprio la classe

public class parte21 {
    public static void main(String[] args) {
        Persona persona1=new Persona("Luca", "Rossi", 20, "Blu");
        Persona persona2=new Persona("Mario","Verdi", 30, "Rosso");  

        persona1.saluta(persona2);  
        //System.out.println(Persona.numeroPersone);
        Persona.numeroPersone();

    }
    
}
