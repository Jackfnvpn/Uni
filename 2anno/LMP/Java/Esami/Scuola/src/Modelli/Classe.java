package Modelli;

import java.util.ArrayList;

public class Classe {  
    private String sezione;
    private String corso;
    private int anno;  
    private String nome;  
    private ArrayList<Studenti> studenti; 
    
    public Classe(String sezione, String corso, int anno){
        this.anno=anno;
        setCorso(corso);
        setSezione(sezione);
        this.nome=this.sezione+this.corso; 
        this.studenti=new ArrayList<>(); 
    }   

    public int getAnno(){
        return this.anno;
    }  

    public String getNome() {
        return this.nome;
    }

    public  ArrayList<Studenti> getStudenti(){
        return this.studenti;
    }

    public void setSezione(String sezione) {  
        if (sezione.length() != 1) {
            throw new IllegalArgumentException("Il sezione deve essere un singolo carattere.");
        }
        
        char courseChar = sezione.charAt(0);
        if (courseChar < '1' || courseChar > '5') {
            throw new IllegalArgumentException("Errore, il sezione deve essere compreso tra 1 e 5.");
        }
        
        this.sezione = sezione;  
    }

    public void setCorso(String corso){  
        if(corso.length()>1){
            throw new IllegalArgumentException("Devi inserire un solo carattere");
        }
        this.corso=corso.toUpperCase();
          
    }




    
}
