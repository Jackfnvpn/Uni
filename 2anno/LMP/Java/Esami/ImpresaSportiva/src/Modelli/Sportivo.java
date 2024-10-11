package Modelli;

import java.util.HashMap;
import java.time.LocalDate;

public class Sportivo {
    private String nome;
    private String cognome;
    private HashMap<String, Integer> reti;  
    private LocalDate dataAssunzione;
    private Tipologia tipologiaSportivo;  
    private String numeroIscrizione="TEAM_";
    private static int  count=0;    
    private int livelloStipendiale;  

    public Sportivo(String nome, String cognome, LocalDate dataAssunzione, Tipologia tipologiaSportivo, int livelloStipendiale){
        this.nome=nome;
        this.cognome=cognome;  
        this.dataAssunzione=dataAssunzione;
        this.reti=new HashMap<>();
        this.numeroIscrizione=numeroIscrizione+(count++);
        setLivelloStipendiale(livelloStipendiale);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public HashMap<String, Integer> getReti() {
        return reti;
    }

    public void setReti(HashMap<String, Integer> reti) {
        this.reti = reti;
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(LocalDate dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public Tipologia getTipologiaSportivo() {
        return tipologiaSportivo;
    }

    public void setTipologiaSportivo(Tipologia tipologiaSportivo) {
        this.tipologiaSportivo = tipologiaSportivo;
    }

    public static String getNumeroIscrizione() {
        return numeroIscrizione;
    }

    public static void setNumeroIscrizione(String numeroIscrizione) {
        Sportivo.numeroIscrizione = numeroIscrizione;
    }

    public int getLivelloStipendiale() {
        return livelloStipendiale;
    }

    public void setLivelloStipendiale(int livelloStipendiale) {
        if (livelloStipendiale < 1 || livelloStipendiale > 5){
            throw new IllegalArgumentException("Non è possibile selezionare come livello stipendiale numeri minori di 1 e maggiori di 5");
        }
        this.livelloStipendiale = livelloStipendiale;
    }

    





}
