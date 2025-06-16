package Models;

public class Macchina {
    private String marca;
    private String nome;
    private int cilindrata;
    private int serbatoio;
    private int chilometri;
    private double autonomia;

    public Macchina(String marca, String nome, int cilindrata, int serbatoio, int chilometri) {
        this.marca = marca;
        this.nome = nome;
        this.cilindrata = cilindrata;
        this.serbatoio = serbatoio;
        this.chilometri = chilometri;
    }

    
    public String getMarca() {
        return marca;
    }
    public String getNome() {
        return nome;
    }
    public int getCilindrata() {
        return cilindrata;
    }
    public int getSerbatoio() {
        return serbatoio;
    }
    public int getChilometri() {
        return chilometri;
    }
    public double getAutonomia() {
        return autonomia;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCilindrata(int cilindrata) {
        this.cilindrata = cilindrata;
    }
    public void setSerbatoio(int serbatoio) {
        this.serbatoio = serbatoio;
    }
    public void setChilometri(int chilometri) {
        this.chilometri = chilometri;
    }
    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

}
