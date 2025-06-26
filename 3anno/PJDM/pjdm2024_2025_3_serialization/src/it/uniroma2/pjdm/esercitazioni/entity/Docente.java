package it.uniroma2.pjdm.esercitazioni.entity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.uniroma2.pjdm.esercitazioni._4_jsonAndFile.ParseException;

public class Docente {

	private String nome;
	private String corso;
	private int crediti;
	private ArrayList<Integer> array;

	public Docente(String nome, String corso, int crediti) {
		super();
		this.nome = nome;
		this.corso = corso;
		this.crediti = crediti;
		this.array = new ArrayList<Integer>();
	}

	// Maybe it is not the best solution to use a costructur with a json object.
	// I would suggest to use a static method.
	public Docente(JSONObject jo) throws JSONException {
		this.nome = jo.getString("nome");
		this.corso = jo.getString("corso");
		this.crediti = jo.getInt("crediti");

		// Alternatively, we can re-use the constructor, but it mush be at the first
		// line
		// this(jo.getString("nome"), jo.getString("corso"), jo.getInt("crediti"));
		this.array = new ArrayList<Integer>();

		try {
			JSONArray jsonArray = jo.getJSONArray("studenti");
			for (int i = 0; i < jsonArray.length(); i++) {
				this.array.add(jsonArray.getInt(i));
			}
		} catch (JSONException e) {
			System.out.println("Warning: the attribute arr is not defined.");
		}

	}

	// Maybe it is not the best solution to use a costructur with a string
	// I would suggest to use a static method.
	public Docente(String line) throws ParseException {
		super();
		String[] strarr = line.split(",");

		if (strarr.length != 3) {
			throw new ParseException("Numero campi diverso da 3");
		}

		this.nome = strarr[0];
		this.corso = strarr[1];
		this.crediti = Integer.parseInt(strarr[2]);
	}

	@Override
	public String toString() {
		return "Docente [nome=" + nome + ", corso=" + corso + ", crediti=" + crediti + ", array=" + array + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		result = prime * result + crediti;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Docente other = (Docente) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		if (crediti != other.crediti)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String toStringJSON() throws JSONException {
		JSONObject joutput = new JSONObject();
		joutput.put("nome", this.nome);
		joutput.put("corso", this.corso);
		joutput.put("crediti", this.crediti);
		return joutput.toString();
	}

}
