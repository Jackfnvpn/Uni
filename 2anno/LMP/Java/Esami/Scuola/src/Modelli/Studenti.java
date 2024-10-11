package Modelli;

import java.time.LocalDate;

public class Studenti {
    private String nome;
    private String cognome;
    private String luogoNascita;  
    private LocalDate dataNascita;  
    private LocalDate dataIscrizione;  
    
    public Studenti(String nome, String cognome, LocalDate dataNascita, String luogoNascita, LocalDate dataIscrizione) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.dataIscrizione=dataIscrizione;
    }  



    public LocalDate getDataNascita(){
        return this.dataNascita;
    }

    public String getNome(){
        return this.nome;
    }

    
    
}
