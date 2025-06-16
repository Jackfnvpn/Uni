import Dipendenti.src.Modelli.Dipendenti;

public class AgentiVendita extends Dipendenti{

    private int provvigione;
    private int numero_clienti;

    public AgentiVendita(int provvigione, int numero_clienti){
        this.provvigione=setProvvigione(provvigione);
        this.numero_clienti=numero_clienti;
    }

    public int getProvvigione() {
        return provvigione;
    }

    public int getNumero_clienti() {
        return numero_clienti;
    }

    private void setProvvigione(int provvigione) {

        if (provvigione<0.05 || provvigione>0.3){
            throw new IllegalArgumentException("Il valore della provvigione deve essere un numero compreso tra 0.05 e 0.3")
        }
        this.provvigione = provvigione;
    }

    


}
