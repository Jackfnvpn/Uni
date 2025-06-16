package com.example.send_go.Entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Customer {


    private int  id;
    private String nome;
    private String cognome;
    private String numeroTelefono;
    private String email;
    private String password;

    public Customer(int id, String nome, String cognome,String numeroTelefono, String email, String password){
        this.id = id;
        this.nome=nome;
        this.cognome=cognome;
        this.numeroTelefono=numeroTelefono;
        this.email=email;
        this.password=password;
    }

    public Customer(String nome, String cognome,String numeroTelefono, String email, String password){
        this.nome=nome;
        this.cognome=cognome;
        this.numeroTelefono=numeroTelefono;
        this.email=email;
        this.password=password;
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String toJson() {
        JSONObject itemJson = new JSONObject();
        itemJson.put("id",this.id);
        itemJson.put("nome", this.nome);
        itemJson.put("cognome", this.cognome);
        itemJson.put("telefono", this.numeroTelefono);
        itemJson.put("email", this.email);
        itemJson.put("password", this.password);
        return itemJson.toString();
    }

    public static Customer fromJson(JSONObject jsonObject) throws JSONException {

        if (!jsonObject.has("nome") || !jsonObject.has("cognome") || !jsonObject.has("telefono")
                || !jsonObject.has("email") || !jsonObject.has("password") ) {
            throw new JSONException("Mancano dei campi");
        }


        String email = jsonObject.getString("email");
        String telefono = jsonObject.getString("telefono");

        if (!email.contains("@")){
            throw new JSONException("Formato email non valido");
        }

        if (!telefono.matches("\\d+")) {
            throw new JSONException("Il numero di telefono deve contenere solo numeri");
        }

        return new Customer(
                jsonObject.getString("nome"),
                jsonObject.getString("cognome"),
                jsonObject.getString("telefono"),
                jsonObject.getString("email"),
                jsonObject.getString("password")
        );
    }


    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
