package it.uniroma2.pjdm.servletdb.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Musicista {

	@Override
	public String toString() {
		return "Musicista [name=" + name + ", ssn=" + ssn + "]";
	}

	public Musicista(String name, int ssn) {
		super();
		this.name = name;
		this.ssn = ssn;
	}

	// IT IS USELESS, BUT IT CAN BE USEFULL WHEN ADOPTING EXTERNAL LIBS FRO
	// SERIALIZATION
	// IF YOU ARE NOT USING SUCH LIBS, PLEASE REMOVE IT
	// public Musicista() {
	//
	// }

	private String name;

	private int ssn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public static Musicista fromJSON(String jsonString) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonString);

		if (!jsonObject.has("ssn") || !jsonObject.has("name"))
			throw new JSONException("Where are the SSN or NAME?");

		int ssn = jsonObject.getInt("ssn");
		String name = jsonObject.getString("name");

		return new Musicista(name, ssn);
	}

	public String toJSONString() {
		return new JSONObject(this).toString();
	}

}
