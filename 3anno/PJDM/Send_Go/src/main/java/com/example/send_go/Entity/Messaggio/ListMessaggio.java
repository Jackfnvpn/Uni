package com.example.send_go.Entity.Messaggio;

import com.example.send_go.Entity.Spedizione.Spedizione;
import org.json.JSONArray;

import java.util.ArrayList;

public class ListMessaggio extends ArrayList<Messaggio>{
    public String toJSONString() {
        return new JSONArray(this).toString();
    }
}
