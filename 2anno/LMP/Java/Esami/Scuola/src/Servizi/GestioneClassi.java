package Servizi;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Modelli.Classe;
import Modelli.Studenti;

public class GestioneClassi {
    HashMap <String,Classe> classi =new HashMap<>();  

    public void addStudenti(String nomeClasse,String nome, String cognome, LocalDate dataNascita, String luogoNascita, LocalDate dataIscrizione){  
        Classe classe = classi.get(nomeClasse);  
        Studenti studente = new Studenti(nome,cognome,dataNascita,luogoNascita,dataIscrizione);  
        classe.getStudenti().add(studente);  
    }

    public void addClasse(String sezione, String corso, int anno){
        Classe nuovaClasse= new Classe(sezione,corso,anno);    

        classi.put(nuovaClasse.getNome(), nuovaClasse);
    }  

    public int numeroStudentiClasse(String nomeClasse){  
        Classe classe=classi.get(nomeClasse);
        System.out.println(classe.getStudenti());

        return classe.getStudenti().size();  

    }  
     

    public HashMap<String,Integer> ripetenti(String nomeClasse){
        Classe classe=classi.get(nomeClasse); 
        int numeroBocciature=0; 
        HashMap <String,Integer> bocciature= new HashMap<>();
        for(Studenti studente:classe.getStudenti()){
            System.out.println(studente.getDataNascita().getYear());
            System.out.println(classe.getAnno());

            if (studente.getDataNascita().getYear() < classe.getAnno()){  
                numeroBocciature=classe.getAnno()-studente.getDataNascita().getYear();   
                bocciature.put(studente.getNome(), numeroBocciature);
            }
        }  

        return bocciature;
    }



    
}
