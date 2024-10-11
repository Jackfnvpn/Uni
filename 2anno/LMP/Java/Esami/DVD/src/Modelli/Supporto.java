package Modelli;

import java.time.LocalDate;
import java.util.ArrayList;


public class Supporto {

    private String titolo;
    private String entePubblicante;
    private LocalDate anno;    
    private ArrayList<Prestito> prestiti;
    private int giorniPrefissati;


    public Supporto(String titolo, String entePubblicante, LocalDate anno){
        this.titolo=titolo;
        this.entePubblicante=entePubblicante;
        this.anno=anno;  
        this.prestiti=new ArrayList<>();  

    }      

    public ArrayList<Prestito> getPrestiti(){
        return this.prestiti;
    }  


    public void addPrestito(LocalDate dataRiconsegnaPrevista,LocalDate dataRiconsegnaEffettiva,String nome,String cognome,int costo,int giorniPrefissati){
        Prestito nuovoPrestito=new Prestito(dataRiconsegnaPrevista, dataRiconsegnaEffettiva, nome, cognome, costo,giorniPrefissati);  
        this.prestiti.add(nuovoPrestito);
    }  

    



    
    
}
