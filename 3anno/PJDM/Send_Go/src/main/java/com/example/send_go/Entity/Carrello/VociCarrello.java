package com.example.send_go.Entity.Carrello;

import com.example.send_go.Entity.Corriere.Corriere;
import com.example.send_go.Entity.Indirizzo.Indirizzo;
import com.example.send_go.Entity.Pacco.Pacco;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class VociCarrello {
    private Pacco       dimensioniPacco;
    private Corriere    corriere;
    private LocalDate   dataRitiro;
    private Indirizzo   partenza;
    private Indirizzo   destinazione;

    public VociCarrello(Pacco dimensioni, Corriere corriere, LocalDate dataRitiro,
                        Indirizzo partenza, Indirizzo destinazione) {
        this.dimensioniPacco = dimensioni;
        this.dataRitiro = dataRitiro;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.corriere = corriere;
    }

    public static VociCarrello fromJSON(JSONObject json) throws JSONException {

        if (!json.has("data_ritiro") || !json.has("partenza") || !json.has("destinazione") ||
                !json.has("dimensioni") || !json.has("corriere")) {
            throw new JSONException("Campi mancanti");
        }

        Pacco dimensioniPacco;
        Corriere corriere;
        LocalDate dataRitiro;
        Indirizzo partenza;
        Indirizzo destinazione;

        try {
            dimensioniPacco = Pacco.fromJSON(json.getJSONObject("dimensioni"));
            corriere = Corriere.fromJSON(json.getJSONObject("corriere"));
            dataRitiro = LocalDate.parse(json.getString("data_ritiro"));
            partenza = Indirizzo.fromJSON(json.getJSONObject("partenza"));
            destinazione = Indirizzo.fromJSON(json.getJSONObject("destinazione"));
        }
        catch (JSONException e) {
            throw new JSONException(e.getMessage());
        }
        return new VociCarrello(dimensioniPacco,corriere,dataRitiro,partenza,destinazione);
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
