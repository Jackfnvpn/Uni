package com.example.send_go.Entity.Corriere;

import org.json.JSONException;
import org.json.JSONObject;

public class Corriere {
    private String corriere_nome;
    private double corriere_tariffa;
    private int idCorriere;

    public Corriere(String nome, double tariffa) {
        this.corriere_nome = nome;
        this.corriere_tariffa = tariffa;
    }

    public Corriere(int idCorriere, String nome, double tariffa) {
        this.idCorriere = idCorriere;
        this.corriere_nome = nome;
        this.corriere_tariffa = tariffa;
    }

    public static Corriere fromJSON(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.has("idCorriere") || !jsonObject.has("corriere_nome") || !jsonObject.has("corriere_tariffa"))
        {
            throw new JSONException("Mancano dei campi per il corriere");
        }

        String nome = jsonObject.getString("corriere_nome");
        double tariffa = jsonObject.getDouble("corriere_tariffa");
        int idCorriere = jsonObject.getInt("idCorriere");

        return new Corriere(idCorriere,nome, tariffa);
    }

    public int getIdCorriere() {
        return idCorriere;
    }

    public void setIdCorriere(int id) {
        this.idCorriere = id;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("nome", corriere_nome);
        json.put("tariffa", corriere_tariffa);
        return json;
    }

    public String getCorriere_nome() {
        return corriere_nome;
    }

    public void setNome(String nome) {
        this.corriere_nome = nome;
    }

    public double getCorriere_tariffa() {
        return corriere_tariffa;
    }

    public void setTariffa(double tariffa) {
        this.corriere_tariffa = tariffa;
    }
}
