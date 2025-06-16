package Services;
import java.util.ArrayList;

import Enum.Disponibilita;
import Models.DisponibilitaMacchina;
public class GestioneMacchina {

    
    ArrayList<DisponibilitaMacchina> listaMacchina = new ArrayList<>();

    public void addMacchina(String marca, String nome, int cilindrata, int serbatoio, int chilometri){
        DisponibilitaMacchina macchina = new DisponibilitaMacchina(marca, nome, cilindrata, serbatoio, chilometri,Disponibilita.MAGAZZINO,"");
        this.listaMacchina.add(macchina);
    }

    public void conteggioAutonomia(String marca, String nome, int cilindrata, int serbatoio, int chilometri){
        for(DisponibilitaMacchina macchina: this.listaMacchina){
            if (macchina.getMarca().equals(marca)&&macchina.getNome().equals(nome) 
                    && macchina.getCilindrata()==cilindrata && macchina.getSerbatoio()==serbatoio && macchina.getChilometri()==chilometri) {
                        macchina.setAutonomia(serbatoio*chilometri);
                    }
        }
    }

    public DisponibilitaMacchina getMacchina(String marca, String nome, int cilindrata, int serbatoio, int chilometri){
        for(DisponibilitaMacchina macchina: this.listaMacchina){
            if (macchina.getMarca().equals(marca)&&macchina.getNome().equals(nome) 
                    && macchina.getSerbatoio()==serbatoio && macchina.getChilometri()==chilometri) {
                        return macchina;
                }
        }
        return null;
    }

    public void cambioCilindrata(String marca, String nome, int cilindrata, int serbatoio, int chilometri, int cilindrata2){
        for(DisponibilitaMacchina macchina : this.listaMacchina){
            if (macchina.getMarca().equals(marca)&& macchina.getNome().equals(nome) 
                    && macchina.getSerbatoio()==serbatoio && macchina.getChilometri()==chilometri) {
                        DisponibilitaMacchina macchina2 = new DisponibilitaMacchina(marca,nome,cilindrata2,serbatoio,chilometri,Disponibilita.MAGAZZINO,"");
                        this.listaMacchina.add(macchina2);
                        break;
                    }
        }
    }
    
    
    public void setStatus(String marca, String nome, int cilindrata, int serbatoio, int chilometri,Disponibilita disponibilita, String proprietario){
        for(DisponibilitaMacchina macchina: this.listaMacchina){
            if (macchina.getMarca().equals(marca)&&macchina.getNome().equals(nome) 
                    && macchina.getCilindrata()==cilindrata && macchina.getSerbatoio()==serbatoio && macchina.getChilometri()==chilometri) {
                        macchina.setDisponibilita(disponibilita);
                        macchina.setProprietario(proprietario);                
            }
        }
    }



}
