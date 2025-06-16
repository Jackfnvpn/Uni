package com.example.send_go.Entity.Ticket;

import org.json.JSONArray;

import java.util.ArrayList;

public class ListTicket extends ArrayList<Ticket> {
    public String toJSONString() {
        return new JSONArray(this).toString();
    }
}
