package Modelli;

import java.time.LocalDate;
import java.time.LocalTime;

public class DVD extends Supporto{  
    private LocalTime durata;  

    
    public DVD(String titolo, String entePubblicante, LocalDate anno,LocalTime durata){
        super(titolo,entePubblicante,anno);
        this.durata=durata;
    }
}
