package it.uniroma2.pjdm.servletdb.entity;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {

	public static Item fromJSON(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);

		if (!jsonObject.has("desc"))
			throw new JSONException("The field desc is required!");

		String desc = jsonObject.getString("desc");

		if (jsonObject.has("id") && jsonObject.has("quant")) {
			int id = jsonObject.getInt("id");
			String quant = jsonObject.getString("quant");
			return new Item(id, desc, quant);

		} else if (jsonObject.has("id")) {
			int id = jsonObject.getInt("id");
			return new Item(id, desc);
		} else
			return new Item(desc);
	}

	private int id = -1;

	private String desc;

	private String quant;

	public Item(int id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}

	public Item(int id, String desc, String quant) {
		super();
		this.id = id;
		this.desc = desc;
		this.quant = quant;
	}

	public Item(String desc) {
		super();
		this.desc = desc;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(desc, other.desc) && id == other.id && Objects.equals(quant, other.quant);
	}

	public String getDesc() {
		return desc;
	}

	public int getId() {
		return id;
	}

	public String getQuant() {
		return quant;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc, id, quant);
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuant(String quant) {
		this.quant = quant;
	}

	public String toJson() {
		JSONObject itemJson = new JSONObject(this);
		return itemJson.toString();
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", desc=" + desc + ", quant=" + quant + "]";
	}

}
