package it.uniroma2.pjdm.servletdb.entity;

import java.util.TreeMap;

import org.json.JSONArray;

public class ItemMap {

	private TreeMap<Integer, Item> items;

	public ItemMap() {
		this.items = new TreeMap<Integer, Item>();
	}

	public void add(Item e) throws Exception {
		if (isIDValid(e)) {
			this.items.put(e.getId(), e);
		}
	}

	public void clear() {
		this.items.clear();
	}

	public boolean containsById(int id) {
		if (this.items.containsKey(id))
			return true;
		return false;
	}

	public Item get(int id) {
		return this.items.get(id);
	}

	public int getLastID() {
		if (this.items.isEmpty())
			return 0;

		return this.items.lastEntry().getKey();
	}

	public boolean isIDValid(Item e) throws Exception {
		if (e.getId() == -1) {
			throw new Exception("Cannot Add Item with ID = -1");
		}

		if (containsById(e.getId())) {
			throw new Exception("Cannot Add Item as the ID " + e.getId() + " is already in the list");
		}

		return true;
	}

	public void remove(int id) {
		this.items.remove(id);
	}

	public String toJson() {
		return new JSONArray(items.values()).toString();

	}

}
