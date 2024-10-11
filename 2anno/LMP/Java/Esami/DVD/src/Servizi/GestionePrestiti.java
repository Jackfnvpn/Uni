package Servizi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;


import Modelli.DVD;
import Modelli.Libro;
import Modelli.Prestito;
import Modelli.Supporto;

public class GestionePrestiti { 

    private ArrayList<Prestito> inconsistenze = new ArrayList<>();

    public int numeroGiorni(Supporto supporto){
        int max=0;
        int numeriGiorni;
        if(!supporto.getPrestiti().isEmpty()){
            for(Prestito prestiti:supporto.getPrestiti()){  
                numeriGiorni=prestiti.getDataRiconsegnaEffettiva().getDayOfMonth()-prestiti.getInizioPrestito().getDayOfMonth();
                if(numeriGiorni>max){
                    max=numeriGiorni;
                }
            }
        }
        return max;
    }  

    public Libro addLibro(String titolo,String entePublicante, LocalDate anno,int numeroPagine){
        Libro libro = new Libro(titolo,entePublicante,anno, numeroPagine);
        return libro;
    }  


    public DVD addDVD (String titolo,String entePublicante, LocalDate anno, LocalTime durata){
        DVD dvd = new DVD(titolo,entePublicante,anno, durata);
        return dvd;
    }

    public void riconsegna(Prestito prestito){
        prestito.setDataRiconsegnaEffettiva();
        int numeroGiorniPrestito= prestito.getDataRiconsegnaEffettiva().getDayOfMonth()-prestito.getInizioPrestito().getDayOfYear();  
        if (numeroGiorniPrestito > prestito.getGiorniPrefissati()){
            prestito.setCosto();
        }
    }
    
    public void inconsistenze(Supporto supporto){
        for(Prestito prestito: supporto.getPrestiti()){
            if (prestito.getDataRiconsegnaEffettiva()==null){
                this.inconsistenze.add(prestito);
            }

        if (this.inconsistenze.size()>1){
            System.out.println("Vi sono delle inconsistenze, di seguito nomi e cognomi");
            for(Prestito prestitiInconsistenti: this.inconsistenze){
                System.out.println(prestitiInconsistenti.getNome() +" "+prestitiInconsistenti.getCognome());
            }
        }
        else{
            System.out.println("Non vi sono inconsistenze");
        }

        }
    }
    
}
