package Modelli;
import java.time.LocalDate;

public class Dipendente {
    
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private LocalDate dataDiAssunzione;
    private String matricola="CLT_";
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

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public LocalDate getDataDiAssunzione() {
        return dataDiAssunzione;
    }

    public void setDataDiAssunzione(LocalDate dataDiAssunzione) {
        this.dataDiAssunzione = dataDiAssunzione;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public static int getNumeroProgressivo() {
        return numeroProgressivo;
    }

    public static void setNumeroProgressivo(int numeroProgressivo) {
        Dipendente.numeroProgressivo = numeroProgressivo;
    }

    public Dipartimento getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(Dipartimento dipartimento) {
        this.dipartimento = dipartimento;
    }

    public Mansione getMansione() {
        return mansione;
    }

    public void setMansione(Mansione mansione) {
        this.mansione = mansione;
    }

    public int getLivello() {
        return livello;
    }

    public Dipendente getCapo() {
        return capo;
    }

    public void setCapo(Dipendente capo) {
        this.capo = capo;
    }

    private static int numeroProgressivo=0;
    private Dipartimento dipartimento;
    private Mansione mansione;
    private int livello;    
    private Dipendente capo;
    
    
    public Dipendente(String nome, String cognome, LocalDate dataDiNascita, LocalDate dataDiAssunzione,Dipartimento dipartimento, Mansione mansione, int livello, Dipendente capo) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.dataDiAssunzione = dataDiAssunzione;
        this.matricola=matricola+(numeroProgressivo++);
        this.dipartimento = dipartimento;
        this.mansione=mansione;
        setLivello(livello);
        this.capo = capo;
    }

    public void setLivello(int livello) {
        if(livello < 1 || livello > 8){
            throw new IllegalArgumentException("Lìvello invalido");
        }
        this.livello = livello;
    }
 
}
