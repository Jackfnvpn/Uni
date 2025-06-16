package it.uniroma2.pjdm.db.entity;

import java.util.ArrayList;

import org.json.JSONArray;

public class ListaMusicisti extends ArrayList<Musicista> {

	private static final long serialVersionUID = 5013166673637496567L;

	public JSONArray toJSONArray() {
		return new JSONArray(this);
	}
	
	public String toJSONString() {
		return toJSONArray().toString();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Musicista musicista : this) {
			sb.append("\t" + musicista + "\n");
		}
		return sb.toString();
	}
}
