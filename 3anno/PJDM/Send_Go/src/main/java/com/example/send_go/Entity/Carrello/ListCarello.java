package com.example.send_go.Entity.Carrello;

import org.json.JSONArray;

import java.util.ArrayList;

public class ListCarello extends ArrayList<VociCarrello> {
    public String toJSONString() {
        return new JSONArray(this).toString();
    }
}
