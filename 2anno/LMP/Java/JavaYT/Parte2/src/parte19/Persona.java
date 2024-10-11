package parte19;

public class Persona {
    String nome;
    String cognome;
    int eta;
    String colore;
    
    Persona(String nome, String cognome, int eta, String colore){
        this.nome=nome;
        this.cognome=cognome;
        this.eta=eta;
        this.colore=colore;
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
