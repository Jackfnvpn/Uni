package com.example.send_go.Entity.Corriere;

import org.json.JSONArray;

import java.util.ArrayList;

public class ListCorriere extends ArrayList<Corriere> {
    public String toJSONString() {

        return new JSONArray(this).toString();
    }
}
