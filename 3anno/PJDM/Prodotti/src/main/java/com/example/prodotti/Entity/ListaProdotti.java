package com.example.prodotti.Entity;

import org.json.JSONArray;

import java.util.ArrayList;

public class ListaProdotti extends ArrayList<Prodotto>{
    public String toJSONString(){
        return new JSONArray(this).toString();
    }
}
