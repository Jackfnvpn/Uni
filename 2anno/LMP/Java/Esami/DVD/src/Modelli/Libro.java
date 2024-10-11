package Modelli;

import java.time.LocalDate;

public class Libro extends Supporto{
    private int numeroPagine;

    public Libro(String titolo, String entePubblicante, LocalDate anno,int numeroPagine){
        super(titolo,entePubblicante,anno);
        this.numeroPagine=numeroPagine;
    }    
}
