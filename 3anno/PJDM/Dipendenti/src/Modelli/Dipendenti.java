package Dipendenti.src.Modelli;

public class Dipendenti {
    private String  nome;
    private String  cognome;
    private int     salario;

    public Dipendenti(String nome, String cognome, String salario){
        this.nome=nome;
        this.cognome=cognome;
        this.salario=salario;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public int getSalario() {
        return this.salario;
    }
}
