package com.example.send_go.Entity.Pacco;

import org.json.JSONException;
import org.json.JSONObject;

public class Pacco {
    private double peso;
    private double lunghezza;
    private double larghezza;
    private double altezza;

    public Pacco(double peso, double lunghezza, double larghezza, double altezza) {
        this.peso = peso;
        this.lunghezza = lunghezza;
        this.larghezza = larghezza;
        this.altezza = altezza;
    }

    public double getPeso() {
        return peso;
    }

    public double getLunghezza() {
        return lunghezza;
    }

    public double getLarghezza() {
        return larghezza;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setLunghezza(double lunghezza) {
        this.lunghezza = lunghezza;
    }

    public void setLarghezza(double larghezza) {
        this.larghezza = larghezza;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }


    public static Pacco fromJSON(JSONObject json) {

        if (!json.has("peso")||!json.has("lunghezza")||!json.has("larghezza")||!json.has("altezza")) {
            throw new JSONException("Mancano dei campi tra le dimensioni del collo");
        }

        double peso, lunghezza, larghezza, altezza;


        try {
            peso = json.getDouble("peso");
            lunghezza = json.getDouble("lunghezza");
            larghezza = json.getDouble("larghezza");
            altezza = json.getDouble("altezza");
        }
        catch (JSONException e) {
            e.printStackTrace();
            throw new JSONException("I campi devono contenere numeri");
        }



        return new Pacco(peso,lunghezza,larghezza,altezza);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("peso", peso);
        json.put("lunghezza", lunghezza);
        json.put("larghezza", larghezza);
        json.put("altezza", altezza);
        return json;
    }
}
