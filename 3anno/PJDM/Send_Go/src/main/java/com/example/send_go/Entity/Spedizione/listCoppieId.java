package com.example.send_go.Entity.Spedizione;

import org.json.JSONArray;

import java.util.ArrayList;

public class listCoppieId extends ArrayList<ArrayList<Integer>> {
    public String toJson() {
        JSONArray outer = new JSONArray();
        for (ArrayList<Integer> coppia : this) {
            JSONArray inner = new JSONArray();
            inner.put(coppia.get(0));
            inner.put(coppia.get(1));
            outer.put(inner);
        }
        return outer.toString();
    }
}
