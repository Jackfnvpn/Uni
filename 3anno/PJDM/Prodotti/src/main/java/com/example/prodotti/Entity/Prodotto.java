package com.example.prodotti.Entity;
import org.json.JSONException;
import org.json.JSONObject;

public class Prodotto {

    int id;
    String nome;
    int quantita;
    double prezzo;

    public Prodotto(int id,String nome, int quantita, double prezzo) {
        this.id=id;
        this.nome=nome;
        this.quantita=quantita;
        this.prezzo=prezzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public static Prodotto fromJSON(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);

        if (!jsonObject.has("id") || !jsonObject.has("nome") || !jsonObject.has("quantita") || !jsonObject.has("prezzo"))
            throw new JSONException("Mancano dei campi");

        int id = jsonObject.getInt("id");
        String nome = jsonObject.getString("name");
        int quantita = jsonObject.getInt("quantita");
        double prezzo = jsonObject.getDouble("prezzo");

        return new Prodotto(id, nome,quantita,prezzo);
    }

    public String toJSONString() {
        return new JSONObject(this).toString();
    }
}
