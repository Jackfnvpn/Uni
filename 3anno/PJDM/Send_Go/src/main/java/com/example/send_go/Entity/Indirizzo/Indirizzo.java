package com.example.send_go.Entity.Indirizzo;

import org.json.JSONException;
import org.json.JSONObject;

public class Indirizzo {
    private String indirizzo, citta, cap, provincia,nome,cognome;

    public Indirizzo(String nome, String cognome , String indirizzo, String citta, String cap, String provincia) {
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.cap = cap;
        this.provincia = provincia;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public static Indirizzo fromJSON(JSONObject json) {

        if (!json.has("nome") || !json.has("cognome") || !json.has("indirizzo")||
        !json.has("citta") || !json.has("cap") || !json.has("provincia")){
            throw new JSONException("mancano dei campi nei dati mittenza o destinazione");
        }

        String nome  = json.getString("nome");
        String cognome  = json.getString("cognome");
        String indirizzo = json.getString("indirizzo");
        String citta = json.getString("citta");
        String cap = json.getString("cap");
        String provincia = json.getString("provincia");

        String regexLettere = "^[A-Za-zÀ-ÖØ-öø-ÿ]+( [A-Za-zÀ-ÖØ-öø-ÿ]+)*$";

        if (!nome.matches(regexLettere)) {
            throw new JSONException("Il campo nome contiene caratteri non validi");
        }

        if (!cognome.matches(regexLettere)) {
            throw new JSONException("Il campo cognome contiene caratteri non validi");
        }

        if (!citta.matches(regexLettere)) {
            throw new JSONException("Il campo città contiene caratteri non validi");
        }

        if (!provincia.matches(regexLettere)) {
            throw new JSONException("Il campo provincia contiene caratteri non validi");
        }

        if (!cap.matches("\\d{5}")) {
            throw new JSONException("Il campo cap deve contenere esattamente 5 cifre numeriche");
        }

        return new Indirizzo(nome,cognome,indirizzo, citta, cap, provincia);
    }
}

