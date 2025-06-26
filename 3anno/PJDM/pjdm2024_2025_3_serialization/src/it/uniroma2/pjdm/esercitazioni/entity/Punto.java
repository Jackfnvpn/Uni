package it.uniroma2.pjdm.esercitazioni.entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Punto implements Serializable {

	
	
	private static final long serialVersionUID = -3347255828638113776L;


	/**
	 * 
	 */

	public Punto(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	private int x;
	
	// WHAT HAPPENS IF WE DECLARE y AS "transient"
	private int y;
	
	
	//public transient int stupidVariable = 0;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		String res = "(x:" + x + ",y:" + y + ")";
		return res;
	}

	/*
	 * Methods for Direct object serialization
	 */
	public void saveOnFile(String outputFilePath) throws IOException  {
		// Saving of object in a file
		FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
		ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

		// Method for serialization of object
		out.writeObject(this);

		out.close();
		fileOutputStream.close();
	}

	public static Punto loadFromFile(String inputFilePath) throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(inputFilePath);
		ObjectInputStream in = new ObjectInputStream(fileInputStream);

		// Method for deserialization of object
		Punto res = (Punto) in.readObject();

		in.close();
		fileInputStream.close();
		return res;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Punto other = (Punto) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	/*
	 * Methods to serialize on file via JSON
	 */

	public JSONObject toJsonObject() throws JSONException {
		JSONObject res = new JSONObject();
		res.put("x", x);
		res.put("y", y);
		return res;
	}

	public static Punto parse(JSONObject jsonObject) throws JSONException {
		int x = jsonObject.getInt("x");
		int y = jsonObject.getInt("y");
		return new Punto(x, y);
	}

}
