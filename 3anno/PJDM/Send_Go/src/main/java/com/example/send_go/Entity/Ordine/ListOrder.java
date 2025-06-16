package com.example.send_go.Entity.Ordine;

import com.example.send_go.Entity.Spedizione.Spedizione;
import org.json.JSONArray;

import java.util.ArrayList;

public class ListOrder extends ArrayList<Ordine> {
    public String toJSONString() {
        return new JSONArray(this).toString();
    }

}
