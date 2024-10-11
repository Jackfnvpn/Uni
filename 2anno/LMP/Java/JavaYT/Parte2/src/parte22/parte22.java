package parte22;

public class parte22 {
    public static void main(String[] args) {
        Persona persona1=new Persona("Luca", "Rossi");  
        persona1.Saluta();

        Studente studente1=new Studente("Luca", "Bianchi");  
        studente1.Saluta();
        studente1.Studia();  

        Insegnante insegnante1=new Insegnante("Luca", "Bianchi");  
        insegnante1.Saluta();

    }
    
}
