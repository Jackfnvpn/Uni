package com.example.send_go.Entity.Spedizione;

import com.example.send_go.Entity.Corriere.Corriere;
import com.example.send_go.Entity.Indirizzo.Indirizzo;
import com.example.send_go.Entity.Pacco.Pacco;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Spedizione {
    private Pacco       dimensioniPacco;
    private Corriere    corriere;
    private LocalDate   dataRitiro;
    private Indirizzo   partenza;
    private Indirizzo   destinazione;
    private int idOrdine;


    public Spedizione(Pacco dimensioni, Corriere corriere, LocalDate dataRitiro,
                      Indirizzo partenza, Indirizzo destinazione) {
        this.dimensioniPacco = dimensioni;
        this.dataRitiro = dataRitiro;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.corriere = corriere;
    }

    public Spedizione(Pacco dimensioni, Corriere corriere, LocalDate dataRitiro,
                        Indirizzo partenza, Indirizzo destinazione,int idOrdine) {
        this.dimensioniPacco = dimensioni;
        this.dataRitiro = dataRitiro;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.corriere = corriere;
        this.idOrdine = idOrdine;
    }

    public static Spedizione fromJSON(JSONObject json) throws JSONException {

        if(!json.has("dimensioni")|| !json.has("corriere") || !json.has("data_ritiro")
                || !json.has("partenza") || !json.has("destinazione") ){
            throw new JSONException("Mancano dei campi");
        }

        Pacco dimensioniPacco = Pacco.fromJSON(json.getJSONObject("dimensioni"));
        Corriere corriere = Corriere.fromJSON(json.getJSONObject("corriere"));
        LocalDate dataRitiro = LocalDate.parse(json.getString("data_ritiro"));

        if (!dataRitiro.isAfter(LocalDate.now())) {
            throw new JSONException("La data di ritiro deve essere successiva alla data odierna");
        }

        Indirizzo partenza = Indirizzo.fromJSON(json.getJSONObject("partenza"));
        Indirizzo destinazione = Indirizzo.fromJSON(json.getJSONObject("destinazione"));

        return new Spedizione(dimensioniPacco,corriere,dataRitiro,partenza,destinazione);
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Pacco getDimensioniPacco() {
        return dimensioniPacco;
    }

    public void setDimensioniPacco(Pacco dimensioniPacco) {
        this.dimensioniPacco = dimensioniPacco;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void setCorriere(Corriere corriere) {
        this.corriere = corriere;
    }

    public LocalDate getDataRitiro() {
        return dataRitiro;
    }

    public void setDataRitiro(LocalDate dataRitiro) {
        this.dataRitiro = dataRitiro;
    }

    public Indirizzo getPartenza() {
        return partenza;
    }

    public void setPartenza(Indirizzo partenza) {
        this.partenza = partenza;
    }

    public Indirizzo getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(Indirizzo destinazione) {
        this.destinazione = destinazione;
    }
}
