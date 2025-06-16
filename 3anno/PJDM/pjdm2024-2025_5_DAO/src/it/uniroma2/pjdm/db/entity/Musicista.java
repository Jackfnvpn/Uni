package it.uniroma2.pjdm.db.entity;

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
	
	public JSONObject toJSON() {
		return new JSONObject(this);
	}

}
