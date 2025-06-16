package com.example.items.Entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Items {
    private int id=-1;
    private String desc;
    private String quant;

    public static Items fromJSON(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);

        if (!jsonObject.has("desc"))
            throw new JSONException("The field desc is required!");

        String desc = jsonObject.getString("desc");

        if (jsonObject.has("id") && jsonObject.has("quant")) {
            int id = jsonObject.getInt("id");
            String quant = jsonObject.getString("quant");
            return new Items(id, desc, quant);

        } else if (jsonObject.has("id")) {
            int id = jsonObject.getInt("id");
            return new Items(id, desc);
        } else
            return new Items(desc);
    }

    public Items(int id, String desc, String quant) {
        this.id = id;
        this.desc = desc;
        this.quant = quant;
    }

    public Items(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Items(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Item [id=" + id + ", desc=" + desc + ", quant=" + quant + "]";
    }

    public String toJson() {
        JSONObject itemJson = new JSONObject(this);
        return itemJson.toString();
    }
}
