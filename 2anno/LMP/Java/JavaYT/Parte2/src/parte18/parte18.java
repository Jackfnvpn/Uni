package parte18;

import parte19.Persona;

public class parte18 {
    public static void main(String[] args) {
        Persona persona1=new Persona("Luca", "Rossi", 18, "Blu");
        Persona persona2=new Persona("Mario","Bianchi", 20, "Giallo");

        System.out.println(persona1.nome1);
        System.out.println(persona2.nome1); 

        persona1.addizione(5, 5);
        persona2.addizione(2, 2);
    }
}
