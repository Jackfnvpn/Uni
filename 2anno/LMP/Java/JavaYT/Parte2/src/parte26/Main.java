package parte26;

public class Main {
    public static void main(String[] args) {
        Persona persona1= new Persona("Luca","Rossi");
        Persona persona2= new Persona("Luca","Bianchi");

        System.out.println(persona1);
        System.out.println(persona2);  

        persona2.copy(persona1);  

        persona2.setNome("Marco");

        System.out.println();  


        System.out.println(persona1.getNome());
        System.out.println(persona1.getCognome());
        
        System.out.println();  
        
        System.out.println(persona2.getNome());
        System.out.println(persona2.getCognome());
        





    }
    
}
