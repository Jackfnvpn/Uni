package Models;
import Enum.Disponibilita;

public class DisponibilitaMacchina extends Macchina{
    
    private Disponibilita disponibilita;
    private String proprietario;
    
    public DisponibilitaMacchina(String marca, String nome, int cilindrata, int serbatoio, int chilometri, Disponibilita disponibilita, String proprietario) {
        super(marca, nome, cilindrata, serbatoio, chilometri);
        this.disponibilita = disponibilita;
        this.proprietario = proprietario;
    }

    public Disponibilita getDisponibilita() {
        return disponibilita;
    }
    public String getProprietario() {
        return proprietario;
    }

    public void setDisponibilita(Disponibilita disponibilita) {
        this.disponibilita = disponibilita;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    

    


}
