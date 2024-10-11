package parte28;  

//polimorfismo capacità di un oggetto di collegarsi a più tipi di dato

public class Main {  
    public static void main(String[] args) {
        Studente studente= new Studente("Luca","Rossi");
        Studente studente2= new Studente("Anna","Rossi");

        Insegnante insegnante=new Insegnante("Marco", "Marrone");

        Persona[] classe={studente,studente2,insegnante};  

        for(Persona persona:classe){
            persona.Saluta();
        }
        
    }
    
}
