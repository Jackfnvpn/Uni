package com.example.send_go.Entity.Ticket;

import com.example.send_go.Entity.Messaggio.Messaggio;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ticket {
    private int id_ticket;
    private ArrayList<Messaggio> messaggio;
    private int spedizioneId;
    private int ordineId;

    public Ticket(int spedizioneId, int ordineId) {
        this.messaggio = new ArrayList<>();
        this.spedizioneId = spedizioneId;
        this.ordineId = ordineId;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public String toJSONString() {
        return new JSONObject(this).toString();
    }

    public void setId(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public ArrayList<Messaggio> getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(ArrayList<Messaggio> messaggio) {
        this.messaggio = messaggio;
    }

    public void addMessaggio(Messaggio messaggio) {
        this.messaggio.add(messaggio);
    }

    public int getSpedizioneId() {
        return spedizioneId;
    }

    public void setSpedizioneId(int spedizioneId) {
        this.spedizioneId = spedizioneId;
    }

    public int getOrdineId() {
        return ordineId;
    }

    public void setOrdineId(int ordineId) {
        this.ordineId = ordineId;
    }

    public static Ticket fromJSON(JSONObject json) throws JSONException {
        if(!json.has("idSpedizione")|| !json.has("idOrdine")) {
            throw new JSONException("Mancano dei campi");
        }
        int spedizioneId = json.getInt("idSpedizione");
        int ordineId = json.getInt("idOrdine");
        return new Ticket(spedizioneId, ordineId);
    }
}
