package com.example.send_go.Entity.Messaggio;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Messaggio {
    private int idMessaggio;
    private String messaggio;
    private LocalDate dataMessaggio;
    private int flagMittente;

    public Messaggio(String messaggio, LocalDate dataMessaggio, int flagMittente) {
        this.messaggio = messaggio;
        this.dataMessaggio = dataMessaggio;
        this.flagMittente = flagMittente;
    }

    public Messaggio(int idMessaggio, String messaggio, LocalDate dataMessaggio, int flagMittente) {
        this.idMessaggio=idMessaggio;
        this.messaggio = messaggio;
        this.dataMessaggio = dataMessaggio;
        this.flagMittente = flagMittente;
    }

    public int getIdMessaggio() {
        return idMessaggio;
    }

    public void setIdMessaggio(int idMessaggio) {
        this.idMessaggio = idMessaggio;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public LocalDate getDataMessaggio() {
        return dataMessaggio;
    }

    public void setDataMessaggio(LocalDate dataMessaggio) {
        this.dataMessaggio = dataMessaggio;
    }

    public int getFlagMittente() {
        return flagMittente;
    }

    public void setFlagMittente(int flagMittente) {
        this.flagMittente = flagMittente;
    }

    public static Messaggio fromJson(JSONObject json){

        if(!json.has("messaggio")||!json.has("dataMessaggio")||!json.has("flagMittente")){
            throw new JSONException("Mancano dei campi");
        }

        String messaggio = json.getString("messaggio");
        LocalDate dataMessaggio = LocalDate.parse(json.getString("dataMessaggio"));
        int flagMittente = json.getInt("flagMittente");

        return new Messaggio(messaggio,dataMessaggio,flagMittente);
    }
}
