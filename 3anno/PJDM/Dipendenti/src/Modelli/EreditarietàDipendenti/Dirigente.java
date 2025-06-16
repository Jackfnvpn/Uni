import Dipendenti.src.Modelli.Dipendenti;

public class Dirigente extends Dipendenti{
    private String divisione;
    private int sottoposti;

    public Dirigente(int matricola, String nome, String cognome, double salario, String divisione, int numeroDipendenti) {
        super(matricola, nome, cognome, salario, "Dirigente");
        this.divisione = divisione;
        this.numeroDipendenti = numeroDipendenti;
    }

    public Dirigente(int matricola, String nome, String cognome, double salario, String divisione) {
        this(matricola, nome, cognome, salario, divisione, 0);
    }

    

}
