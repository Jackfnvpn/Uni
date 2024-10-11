package Servizi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

import Modelli.Dipartimento;
import Modelli.Dipendente;
import Modelli.Mansione;

public class GestioneDipendenti {
    ArrayList<Dipendente> dipendenti = new ArrayList<>();
    ArrayList<Dipendente> dipendentiSameMasionYear = new ArrayList<>();
    HashMap<Dipartimento,ArrayList<Dipendente>> sameMasionYear=new HashMap<>();
    
    public Dipendente createDipendente(String nome, String cognome, LocalDate dataDiNascita, LocalDate dataDiAssunzione,Dipartimento dipartimento, Mansione mansione, int livello, Dipendente capo){

        Dipendente dipendente = new Dipendente(nome,cognome,dataDiNascita,dataDiAssunzione,dipartimento,mansione,livello,capo);
        dipendenti.add(dipendente);
        return dipendente;
    }

    public void chainCommand(Dipendente dipendente,ArrayList<Dipendente> listaDipendenti){
        
        if(dipendente.getCapo()==null){
            return ;
        }

        listaDipendenti.add(dipendente.getCapo());

        this.chainCommand(dipendente.getCapo(), listaDipendenti);

        return ;
    }  


    public Dipendente subDipendente(Dipendente dipendenteSub){
        HashMap <Dipendente,Integer> possibileSub=new HashMap<>();
        ArrayList<Dipendente> listaCapi=new ArrayList<>();

        this.chainCommand(dipendenteSub,listaCapi);

        for(Dipendente dipendente: dipendenti){
            if (dipendente.getMansione().equals(dipendenteSub.getMansione())){
                this.subDipendenteSearch(dipendente,possibileSub,0,listaCapi);
            }
        }  

        if (possibileSub.isEmpty()){
            return null;
        }
        else{
            int maxKey=0;
            Dipendente finalDip=null;
            for (Dipendente key : possibileSub.keySet()) {
                if (possibileSub.get(key)>maxKey){
                    maxKey=possibileSub.get(key);
                    finalDip=key;
                }
            }  
            return finalDip;
        }

    }


    public void subDipendenteSearch(Dipendente dipendente, HashMap<Dipendente,Integer> possibileSub,int count,ArrayList<Dipendente> capi){

        if (dipendente.getCapo() == null){
            return ;
        }  

        for(Dipendente capo : capi){
            count++;
            if (capo.getMatricola().equals(dipendente.getCapo().getMatricola())){
                possibileSub.put(dipendente, count);
                return;
            }
        }
    }

    public HashMap<Dipartimento,ArrayList<Dipendente>> sameDepartmentYear(){

        

       
        return sameMasionYear;
    }
}
