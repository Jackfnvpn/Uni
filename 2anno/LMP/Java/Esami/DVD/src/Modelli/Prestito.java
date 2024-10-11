package Modelli;

import java.time.LocalDate;

public class Prestito {  
    private LocalDate inizioPrestito;
    private LocalDate dataRiconsegnaPrevista;
    private LocalDate dataRiconsegnaEffettiva;  
    private String nome;  
    private String cognome;
    private int costo;    
    private int giorniPrefissati;


    public Prestito(LocalDate dataRiconsegnaPrevista,LocalDate dataRiconsegnaEffettiva,String nome,String cognome,int costo,int giorniPrefissati){
        this.inizioPrestito=LocalDate.now();
        this.dataRiconsegnaEffettiva=dataRiconsegnaPrevista;
        this.dataRiconsegnaEffettiva=dataRiconsegnaEffettiva;  
        this.nome=nome;
        this.cognome=cognome;
        this.costo=costo;  
        this.giorniPrefissati=giorniPrefissati;  

    }     
    public LocalDate getInizioPrestito(){
        return this.inizioPrestito;
    }  

    public int getGiorniPrefissati(){
        return this.giorniPrefissati;
    }  

    public LocalDate getDataRiconsegnaPrevista() {
        return dataRiconsegnaPrevista;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCosto(){
        this.costo=this.costo+1;
    }

    public LocalDate getDataRiconsegnaEffettiva(){
        return this.dataRiconsegnaEffettiva;
    }

    public void setDataRiconsegnaEffettiva(){
        this.dataRiconsegnaEffettiva=LocalDate.now();
    }


   
    
}
