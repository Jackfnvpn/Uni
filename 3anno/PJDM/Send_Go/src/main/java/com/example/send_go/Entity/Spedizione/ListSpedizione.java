package com.example.send_go.Entity.Spedizione;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListSpedizione extends ArrayList<Spedizione> {
    public String toJSON() {
        return new JSONArray(this).toString();
    }

    public static ListSpedizione fromJSONArray(JSONArray jsonArray) throws JSONException {
        ListSpedizione lista = new ListSpedizione();
        Spedizione s;

        if (jsonArray.length() == 0) {
            throw new JSONException("Non sono presenti elementi nel carrello");
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            try {
                s = Spedizione.fromJSON(obj);
            } catch (JSONException e) {
                throw new JSONException(e.getMessage());
            }
            lista.add(s);
        }

        return lista;
    }

}
