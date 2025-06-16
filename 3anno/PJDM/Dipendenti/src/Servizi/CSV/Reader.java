package Dipendenti.src.Servizi.CSV;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Dipendenti.src.Modelli.EreditarietàDipendenti.Dirigente;
import Dipendenti.src.Modelli.EreditarietàDipendenti.Programmatore;


import java.io.*;
import java.util.*;

public class Reader {

    public List<Dipendente> read(String inputCSVFilePath) {
        ArrayList<Dipendente> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputCSVFilePath))) {
            String riga;
            boolean isFirstLine = true;
            while ((riga = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] valori = riga.split(";");

                int matricola = Integer.parseInt(valori[0]);
                String nome = valori[1];
                String cognome = valori[2];
                double salario = Double.parseDouble(valori[3]);
                String qualifica = valori[4];

                switch (qualifica) {
                    case "Dirigente":
                        String divisione = valori[5];
                        if (valori.length > 6) {
                            int numeroDipendenti = Integer.parseInt(valori[6]);
                            lista.add(new Dirigente(matricola, nome, cognome, salario, divisione, numeroDipendenti));
                        } else {
                            lista.add(new Dirigente(matricola, nome, cognome, salario, divisione));
                        }
                        break;

                    case "Programmatore":
                        String linguaggio = valori[5];
                        lista.add(new Programmatore(matricola, nome, cognome, salario, linguaggio));
                        break;

                    case "AgenteDiVendita":
                        double provvigione = Double.parseDouble(valori[5]);
                        int clienti = Integer.parseInt(valori[6]);
                        lista.add(new AgenteDiVendita(matricola, nome, cognome, salario, provvigione, clienti));
                        break;

                    default:
                        System.out.println("Qualifica sconosciuta: " + qualifica);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
