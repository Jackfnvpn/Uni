package parte20;

public class parte20 {
    public static void main(String[] args) {
        Persona[] persone=new Persona[3];  
        Persona persona1=new Persona("Luca","Rossi", 20, "Blu");
        Persona persona2=new Persona("Mario","Rossi", 20, "Blu");
        Persona persona3=new Persona("Ana","Neri", 22, "Giallo")  

        persone[0]=persona1;
        persone[1]=persona2;
        persone[2]=persone3;

        Persona[] persone2={persona1,persona2,persona3};

        
    }
    
}
