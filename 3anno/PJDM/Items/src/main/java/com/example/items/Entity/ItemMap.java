package com.example.items.Entity;
import java.util.TreeMap;

import org.json.JSONArray;
public class ItemMap {
    private TreeMap<Integer,Items> items;
    public ItemMap() {
        this.items = new TreeMap<>();
    }

    public String toJson() {
        return new JSONArray(items.values()).toString();

    }

    public void add(Items e) throws Exception {
        if (isValidId(e)) {
            this.items.put(e.getId(), e);
        }
    }

    public void clear() {
        this.items.clear();
    }
    public boolean isValidId(Items e) throws Exception{
        if (e.getId()==-1){
            throw new Exception("Non è possibile aggiungere items con id -1");
        }

        if(containsById(e.getId())){
            throw new Exception("Non è possibile aggiungere l'item poiché già presente");
        }

        return true;
    }

    public boolean containsById(int id) {
        return this.items.containsKey(id);
    }

    public void remove(int id) {
        this.items.remove(id);
    }

    public Items get(int id) {
        return this.items.get(id);
    }

    public int getLastID() {
        if (this.items.isEmpty())
            return 0;

        return this.items.lastEntry().getKey();
    }
}
