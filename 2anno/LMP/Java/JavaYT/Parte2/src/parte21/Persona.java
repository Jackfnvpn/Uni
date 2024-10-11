package parte21;

public class Persona {
    String nome;
    String cognome;
    int eta;
    String colore;  

    static int numeroPersone;
    
    Persona(String nome, String cognome, int eta, String colore){
        this.nome=nome;
        this.cognome=cognome;
        this.eta=eta;
        this.colore=colore;
        numeroPersone++;  
        System.out.println(numeroPersone);

    }

    static void numeroPersone(){
        System.out.println(numeroPersone);
    } 

    void saluta(Persona personaDaSalutare){
        System.out.println("Ciao "+ personaDaSalutare.nome+ ", io sono "+  this.nome);
    }
    void addizione(int a,int b){
        int risultato=a+b;
        risultato(risultato);
    }

    void risultato(int dato){
        System.out.println("il risultato è " + dato);
    }

    public String toString(){
        String stringa=this.nome+" "+this.cognome+" "+this.eta+" "+this.colore;
        return stringa;
    }
}
